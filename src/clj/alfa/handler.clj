(ns alfa.handler
  (:require
    [compojure.core :refer [GET POST routes] :as compo]
    [compojure.route :as croute]
    [com.stuartsierra.component :as component]
    [alfa.pages :as page]))

(declare main-routes spa-routes)

(defrecord Handler []
  component/Lifecycle
  (start [this]
    (assoc this
      :routes (spa-routes)))
  (stop [this]
    (dissoc this :routes)))

(defn make []
  (map->Handler {}))

;;  IMPLEMENTATIONS

(defn spa-routes
  []
  (routes
    (GET "/" req (page/spa))
    (croute/resources "/")
    (croute/not-found "Not found dudes")))



























(defn main-routes
  []
  (routes
    (GET "/" req (page/home))
    (GET "/sayhi/:username" [username]
         (page/sayhi username))
    (GET "/math/:number" [number]
         (page/math (bigint number)))
    (croute/not-found (page/pagemaker "Error not found"))))
