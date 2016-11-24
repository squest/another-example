(ns alfa.pages
  (:require
    [hiccup.core :as hc]
    [hiccup.page :as hp]))

(defn spa []
  (hp/html5
    [:head
     (hp/include-css "/css/normalize.css")
     (hp/include-css "/css/skeleton.css")]
    [:body
     [:div {:id "app"}]
     (hp/include-js "/js/app.js")]))





































(defn links []
  [{:link "/" :text "Home"}
   {:link "/sayhi/dodol" :text "Say hi to dodol"}
   {:link "/sayhi/Someone" :text "Say hi to someone"}
   (let [n (rand-nth (range 100))]
     {:link (str "/math/" n) :text (str "Math yg ke" n)})
   {:link "/ngawur" :text "ngawur"}])

(defn header
  []
  [:div
   [:center
    [:h3 "This is the title of the page"]
    [:h4 "Additional tagline"]
    [:hr]
    (into
      [:ul]
      (for [link (links)]
        [:li [:a {:href (:link link)} (:text link)]]))]])

(defn footer
  []
  [:div
   [:hr]
   [:p "Copyright Zenius Education 2016"]])


(defn pagemaker
  [isibody]
  (hp/html5
    [:head]
    (header)
    isibody
    (footer)))

(defn math
  [n]
  (pagemaker
    [:center
     [:h1 (if (even? n)
            [:h2 "Ini genap nih"]
            [:h2 "Ini ganjil"])]]))

(defn home
  []
  (pagemaker
    [:center
     [:h2 "This is the home"]]))

(defn sayhi
  [username]
  (pagemaker
    [:center
     [:h2 (str "Hi...." username "!!!")]]))