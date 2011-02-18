(ns got-eggs.data
  (:import (java.util Date))
  (:require [appengine.datastore :as ds]
            [appengine.datastore.protocols :as dsp])
  (:use inflections))

(defmacro make-entity [entity-name args]
  `(~(symbol (hyphenize entity-name)) ~args))

;; Reservation
(ds/defentity Reservation () ((date) (quantity) (user)))

;; Inventory
(ds/defentity InventoryEntry () ((count) (timestamp)))
(extend InventoryEntry
  dsp/LifecycleProtocol
  :after-save (fn [entity] (ds/update-entity entity {:timestamp (Date.)})))

(defn make-inventory-entry [count]
  ;;(ds/save! (InventoryEntry. count (Date.)))
  )

(defn all-inventory-entries []
  ;;(ds/query :kind InventoryEntry)
  )

(defn inventory-on-hand []
  (reduce
   (fn [n y] (+ n (:count y)))
   0
   (all-inventory-entries)))

(defn clear-inventory []
  (let [all (all-inventory-entries)]
    ;;(ds/delete! all)
    ))

