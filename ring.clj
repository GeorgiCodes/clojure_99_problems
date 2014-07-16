(ns chapter13-ring
  (:use ring.adapter.jetty))

(defn hello [req]
  {:body "hello world!"})

(run-jetty hello {:port 8080})
