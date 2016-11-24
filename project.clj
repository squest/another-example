(defproject alfa "0.1.0-SNAPSHOT"
  :dependencies
  [[org.clojure/clojure "1.8.0"]
   [org.clojure/clojurescript "1.9.293"]
   [com.stuartsierra/component "0.3.1"]

   ;; cljs
   [reagent "0.6.0" :exclusions [org.clojure/tools.reader cljsjs/react]]
   [re-frame "0.8.0" :exclusions [cljsjs/react]]
   [cljs-ajax "0.5.8"]
   [cljs-react-material-ui "0.2.30"]

   ;; basic web setup
   [compojure "1.5.1" :exclusions [commons-codec]]
   [org.immutant/web "2.1.5"
    :exclusions [commons-codec org.clojure/java.classpath]]
   [lib-noir "0.9.9" :exclusions [commons-codec org.clojure/java.classpath]]
   [ring/ring-defaults "0.2.1" :exclusions [commons-codec]]
   [ring "1.5.0" :exclusions [commons-codec org.clojure/java.classpath]]

   ;; standard web utilities
   [com.taoensso/timbre "4.7.4"]

   ;; file/formatting and development utilities
   [org.clojure/tools.namespace "0.2.11"]
   [danlentz/clj-uuid "0.1.6"]
   [me.raynes/fs "1.4.6"]
   [pjstadig/humane-test-output "0.8.1"]
   [ring/ring-mock "0.3.0"]]

  :injections [(require 'pjstadig.humane-test-output)
               (pjstadig.humane-test-output/activate!)]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "dev"]

  :resource-paths ["resources"]

  :main alfa.core

  :repl-options {:init-ns user}

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-cloverage "1.0.9"]
            [lein-ancient "0.6.10"]]

  :cljsbuild
  {:test-commands
   {"desktop-test" ["phantomjs"
                    "resources/public/js/app-desktop-test.js"
                    "http://localhost:4000"]}
   :builds
   [{:id           "app"
     :source-paths ["src/cljs"]
     :compiler     {:main            alfa.core
                    :output-dir      "resources/public/js/compiled/out-desktop"
                    :output-to       "resources/public/js/app.js"
                    :closure-defines {"goog.DEBUG" false}
                    :optimizations   :whitespace
                    :pretty-print    false}}
    {:id           "mobile"
     :source-paths ["src/cljs-mobile"]
     :compiler     {:main            alfa.core
                    :output-dir      "resources/public/js/compiled/out-mobile"
                    :output-to       "resources/public/js/app-mobile.js"
                    :closure-defines {"goog.DEBUG" false}
                    :optimizations   :whitespace
                    :pretty-print    false}}
    {:id           "desktop-test"
     :source-paths ["src/cljs" "test/cljs/alfa"]
     :compiler     {:main          beta.runner
                    :output-to     "resources/public/js/app-desktop-test.js"
                    :optimizations :whitespace}}]}

  :profiles {}
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  :figwheel {:css-dirs ["resources/public/vendors"]})