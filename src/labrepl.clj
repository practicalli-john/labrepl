(ns labrepl
  (:use compojure clojure.contrib.logging)
  (:require [labrepl.lab :as lab]))

(defn with-logging [handler]
  (fn [request]
    (log :info (str (:uri request) " [" (:request-method request) "]"
                    "\n\tParameters " (:params request)
                    "\n\tSession " (:session request)))
    (handler request)))

(defn reloading [handler]
  (fn [request]
    (require :reload-all '[labrepl lab])
    (handler request)))

(defn layout [title & body]
  (html
    [:head
      [:title title]
      (include-css "/stylesheets/application.css")]
    [:body
      [:h2 title]
      body]))

(defroutes lab-routes
  (GET "/"
       (html
        [:h2 "Labs"]
        [:ul
         (map
          (fn [lab] [:li (lab/url lab)])
          (lab/all))]))
  (GET "/labs/:name"
       (html
        (layout
         (params :name)
         (lab/instructions (params :name)))))
  (GET "/*" (or (serve-file (params :*)) :next)))

(decorate lab-routes reloading with-logging)

(defroutes app
  (routes lab-routes))

(defn -main [& args]
  (run-server
   {:port 8080}
   "/*"
   (servlet app))
  (println "Welcome to the labrepl. Browse to localhost:8080 to get started!"))

