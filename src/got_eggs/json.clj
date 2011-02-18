(ns clojure.contrib.json
  (:import (com.google.appengine.api.datastore KeyFactory Key))
  (:import (java.io PrintWriter PushbackReader StringWriter
                    StringReader Reader EOFException)))

;; JSON
(defn- write-json-appengine-key [m ^PrintWriter out]
  (write-json-bignum (KeyFactory/keyToString m) out))
(extend Key Write-JSON
        {:write-json write-json-appengine-key})
(defn json-str-with-meta [obj]
  (json-str (vector obj (meta obj))))

