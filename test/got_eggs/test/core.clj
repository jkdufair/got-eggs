(ns got-eggs.test.core
  (:use [got-eggs.core] :reload)
  (:use [clojure.test])
  (:require [appengine-magic.testing :as ae-testing]))

(use-fixtures :each (ae-testing/local-services :all))
