(ns got-eggs.test.data
  (:use [got-eggs.data] :reload)
  (:use [clojure.test])
  (:import (java.util Date))
  (:require [appengine-magic.services.datastore :as ds])
  (:require [appengine-magic.testing :as ae-testing]))

(use-fixtures :each (ae-testing/local-services :all))

(deftest can-make-person
  (let [person (make-person "Jason Dufair" "jase@dufair.org" "7654919990")]
    (is (= "Jason Dufair" (:name person)))))

(deftest can-clear-inventory
  (clear-inventory)
  (is (= (inventory-on-hand) 0)))

(deftest can-make-inventory-entry
  (clear-inventory)
  (make-inventory-entry 1)
  (is (= (inventory-on-hand) 1)))

(deftest can-remove-from-inventory
  (clear-inventory)
  (make-inventory-entry 2)
  (make-inventory-entry -1)
  (is (= (inventory-on-hand) 1)))

(deftest can-make-reservation
  (let [person (make-person  "Jason Dufair" "jase@dufair.org" "7654919990")
        reservation (make-reservation (Date.) 6 person)]
    (is (= (:person reservation) person))
    (is (= 6 (:quantity reservation)))))

(deftest can-get-person
  (let [person (make-person "Jason Dufair" "jase@dufair.org" "7654919990")]
    (is (=
         (get-entity got-eggs.data.Person (get-id person))
         person))))
