(ns alfa.view
  (:require
    [re-frame.core :as re]
    [cljs-react-material-ui.core :as ui]
    [cljs-react-material-ui.icons :as ic]
    [cljs-react-material-ui.reagent :as rui]))

(defn panel-one
  []
  (fn []
    (let [username (re/subscribe [:username])]
      [:center
       [:h1 (str "Hi " @username)]
       [:input {:type      "text"
                :placeholder "username"
                :on-change #(re/dispatch
                              [:set-user
                               (-> % .-target .-value)])}]])))

(defn panel-two
  []
  (fn []
    (let [username (re/subscribe [:username])]
      [:center
       [:h3 (str "Ini " @username " di 2")]
       [:input {:type      "text"
                :placeholder "score"
                :on-change #(re/dispatch
                              [:set-score
                               (-> % .-target .-value)])}]])))

(defn panel-three
  []
  (fn []
    (let [username (re/subscribe [:username])
          score (re/subscribe [:score])]
      [:center
       [:h2
        [:ol
         [:li @username]
         [:li @score]]]])))

(defn header
  []
  (fn []
    (let [username (re/subscribe [:username])]
      [:center
       [rui/app-bar
        {:title              (str "Hi " @username)
         :icon-element-left  (ui/icon-button
                               (ic/action-home))
         :icon-element-right (ui/icon-button
                               (ic/action-autorenew))}]])))

(defn body
  []
  (fn []
    [:center
     [rui/text-field
      {:hint-text "username"
       :on-change #(re/dispatch
                     [:set-user
                      (-> % .-target .-value)])}]]))


(defn main-panel
  []
  [rui/mui-theme-provider
   {:mui-theme (ui/get-mui-theme)}
   [:div
    [header]
    [body]]])


