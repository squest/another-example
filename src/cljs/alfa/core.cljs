(ns alfa.core
  (:require
    [reagent.core :as rc]
    [re-frame.core :as re]
    [alfa.view :as view]))

(defn mount-root
  []
  (rc/render [view/main-panel] (.getElementById js/document "app")))

(defn init []
  (re/dispatch [:initialize-db])
  (mount-root))

(init)
