(ns got-eggs.app_servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use got-eggs.core)
  (:use [appengine-magic.servlet :only [make-servlet-service-method]]))

(defn -service [this request response]
  ((make-servlet-service-method got-eggs-app) this request response))
