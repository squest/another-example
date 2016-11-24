(ns alfa.system
  (:require
    [com.stuartsierra.component :as component]
    [alfa.server :as server]
    [alfa.handler :as handler]))

(def config
  {:server {:port 5000 :path "/" :host "localhost"}})

(defn create-system
  []
  (let [{:keys [port path host]} (:server config)]
    (component/system-map
      :server (component/using
                (server/make port path host)
                [:handler])
      :handler (handler/make))))
