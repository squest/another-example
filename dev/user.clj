(ns user
  (:require
    [com.stuartsierra.component :as component]
    [alfa.system :as system]))

(defonce devsystem (atom nil))

(defn start []
  (->> (system/create-system)
       (component/start-system)
       (reset! devsystem)))

(defn stop []
  (swap! devsystem component/stop-system))

(defn restart []
  (stop)
  (start))