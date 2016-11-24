(ns alfa.subscriptions
  (:require
    [re-frame.core :as re]))

(re/reg-sub
  :username
  (fn [db _] (:username db)))

(re/reg-sub
  :score
  (fn [db _] (:score db)))
