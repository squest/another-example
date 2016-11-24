(ns alfa.server
  (:require
    [com.stuartsierra.component :as component]
    [immutant.web :as web]
    [immutant.util :as log]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defrecord Server [port handler path host]
  component/Lifecycle
  (start [this]
    (let [site-config
          (assoc-in site-defaults [:security :anti-forgery] false)
          site
          (wrap-defaults (:routes handler) site-config)]
      (log/set-log-level! :OFF)
      (assoc this
        :stop-fn (web/run site {:port port :path path :host host}))))
  (stop [this]
    (web/stop (:stop-fn this))
    (dissoc this :stop-fn)))

(defn make [port path host]
  (map->Server {:port port
                :path path
                :host host}))