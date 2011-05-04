(defproject labrepl "0.0.2-SNAPSHOT"
  :description "Clojure exercises, with integrated repl and webapp"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/tools.logging "0.1.2"]
                 [org.clojure/data.json "0.1.0"]
                 [ring/ring-jetty-adapter "0.3.7" :exclusions [org.clojure/clojure
                                                               org.clojure/clojure-contrib]]
                 [compojure "0.6.2"]
                 [hiccup "0.3.4"]
                 [log4j "1.2.16" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [swank-clojure "1.3.0-SNAPSHOT" :exclusions [org.clojure/clojure]]
                 [jline "0.9.94"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT" :exclusions [org.clojure/clojure]]])
