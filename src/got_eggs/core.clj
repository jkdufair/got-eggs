(ns got-eggs.core
  (:use compojure.core got-eggs.data)
  (:require [appengine-magic.core :as ae]
            [clojure.contrib.json :as json]
            [appengine.datastore :as ds]
            [appengine.datastore.service :as svc]
            [appengine.datastore.keys :as keys]))

(defn text-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "text/plain"}
   :body data})

(defroutes got-eggs-app-handler
  (GET "/test"
       [req]
       (text-response
        (str (ds/deserialize-entity (inventory-entry {:count 3})))))
  (GET "/:key"
       [key]
       (text-response (str (svc/get-entity (keys/string->key key)))))

  (POST "*"
        {form-params :form-params}
        (text-response
         (str (ds/deserialize-entity (str (form-params "entity"))))))

;; ;; Inventory
;; (GET "/make-inventory-entry/:count" [count]
;;      {:status 200
;;       :headers {"Content-Type" "text/plain"}
;;       :body (do
;;               (make-inventory-entry (Integer. count))
;;               (str (inventory-on-hand)))})
;; (GET "/inventory-on-hand" req
;;      {:status 200
;;       :headers {"Content-Type" "text/plain"}
;;       :body (str (inventory-on-hand))})
;; (GET "/clear-inventory" req
;;      {:status 200
;;       :headers {"Content-Type" "text/plain"}
;;       :body (clear-inventory)})
)

(ae/def-appengine-app got-eggs-app #'got-eggs-app-handler)
