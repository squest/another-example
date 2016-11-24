(ns alfa.events
  (:require
    [alfa.db :refer [default-db]]
    [re-frame.core :as re]))

(re/reg-event-db
  :initialize-db
  (fn [db whatever]
    default-db))

(re/reg-event-db
  :set-user
  (fn [db [_ username]]
    (assoc db :username username)))

(re/reg-event-db
  :set-score
  (fn [db [_ score]]
    (assoc db :score score)))
