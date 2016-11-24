(ns alfa.handler
  (:require
    [compojure.core :refer [GET POST routes] :as compo]
    [compojure.route :as croute]
    [com.stuartsierra.component :as component]
    [alfa.pages :as page]))

(declare main-routes)

(defrecord Handler []
  component/Lifecycle
  (start [this]
    (assoc this
      :routes (main-routes)))
  (stop [this]
    (dissoc this :routes)))

(defn make []
  (map->Handler {}))

(defn main-routes
  []
  (routes
    (GET "/" req (page/home))
    (GET "/sayhi/:username" [username]
         (page/sayhi username))
    (GET "/math/:number" [number]
         (page/math (bigint number)))
    (croute/not-found (page/pagemaker "Error not found"))))
