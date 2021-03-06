(defproject name-bazaar "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cljs-react-material-ui "0.2.43"]
                 [cljs-web3 "0.19.0-0-4"]
                 [cljsjs/solidity-sha3 "0.4.1-0"]
                 [day8.re-frame/async-flow-fx "0.0.6"]
                 ;[district0x "0.1.10"]
                 [lein-doo "0.1.7"]
                 [madvas/reagent-patched "0.6.1" :exclusions [cljsjs/react cljsjs/react-dom]]
                 [medley "0.8.3"]
                 [org.clojars.akiel/async-error "0.1"]
                 [org.clojure/clojurescript "1.9.671"]
                 [print-foo-cljs "2.0.3"]
                 [re-frame "0.9.4" :exclusions [reagent]]
                 [honeysql "0.9.0"]
                 [cljs-http "0.1.43"]

                 ;; d0xINFRA temporary here
                 [akiroz.re-frame/storage "0.1.2"]
                 [bidi "2.1.1"]
                 [cljs-ajax "0.5.8"]
                 [cljsjs/bignumber "2.1.4-1"]
                 [cljsjs/react-flexbox-grid "1.0.0-0" :exclusions [cljsjs/react cljsjs/react-dom]]
                 [cljsjs/react-highlight "1.0.5-0" :exclusions [cljsjs/react cljsjs/react-dom]]
                 [cljsjs/react-truncate "2.0.3-0"]
                 [cljsjs/react-ultimate-pagination "0.8.0-0" :exclusions [cljsjs/react cljsjs/react-dom]]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [com.cemerick/url "0.1.1"]
                 [day8.re-frame/http-fx "0.1.3"]
                 [kibu/pushy "0.3.6"]
                 [madvas.re-frame/google-analytics-fx "0.1.0"]
                 [madvas.re-frame/web3-fx "0.1.11"]]

  :plugins [[lein-auto "0.1.2"]
            [lein-cljsbuild "1.1.4"]
            [lein-shell "0.5.0"]
            [deraen/lein-less4j "0.5.0"]
            [lein-doo "0.1.7"]
            [lein-npm "0.6.2"]]

  :npm {:dependencies [[eth-ens-namehash "2.0.0"]
                       [ethereumjs-testrpc "3.0.3"]
                       [express "4.15.3"]
                       [solc "0.4.13"]
                       [source-map-support "0.4.0"]
                       [sqlite3 "3.1.8"]
                       [web3 "0.19.0"]
                       [ws "2.0.1"]
                       [xhr2 "0.1.4"]]}

  :min-lein-version "2.5.3"

  :source-paths []

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:server-port 4544}

  :auto {"compile-solidity" {:file-pattern #"\.(sol)$"
                             :paths ["resources/public/contracts/src"]}}

  :aliases {"compile-solidity" ["shell" "./compile-solidity.sh"]}

  :less {:source-paths ["resources/public/less"]
         :target-path "resources/public/css"
         :target-dir "resources/public/css"
         :source-map true
         :compression true}

  :profiles {:dev
             {:dependencies [[org.clojure/clojure "1.8.0"]
                             [binaryage/devtools "0.9.4"]
                             [com.cemerick/piggieback "0.2.1"]
                             [figwheel-sidecar "0.5.11"]
                             [org.clojure/tools.nrepl "0.2.13"]]
              :plugins [[lein-figwheel "0.5.11"]]
              :source-paths ["dev"]
              :resource-paths ["resources"]
              :cljsbuild {:builds [{:id "dev"
                                    :source-paths ["src/frontend" "src/shared"]
                                    :figwheel {:on-jsload "name-bazaar.core/mount-root"}
                                    :compiler {:main "name-bazaar.core"
                                               :output-to "resources/public/js/compiled/app.js"
                                               :output-dir "resources/public/js/compiled/out"
                                               :asset-path "js/compiled/out"
                                               :source-map-timestamp true
                                               :preloads [print.foo.preloads.devtools]
                                               :closure-defines {goog.DEBUG true}
                                               :external-config {:devtools/config {:features-to-install :all}}}}
                                   {:id "dev-backend"
                                    :source-paths ["src/backend" "src/shared"]
                                    :figwheel {:on-jsload "name-bazaar.server.dev/on-jsload"}
                                    :compiler {:main "name-bazaar.server.dev"
                                               :output-to "dev-backend/name-bazaar.js",
                                               :output-dir "dev-backend",
                                               :target :nodejs,
                                               :optimizations :none,
                                               :source-map true}}
                                   {:id "min"
                                    :source-paths ["src/cljs"]
                                    :compiler {:main "name-bazaar.core"
                                               :output-to "resources/public/js/compiled/app.js"
                                               :optimizations :advanced
                                               :closure-defines {goog.DEBUG false}
                                               :pretty-print false
                                               :pseudo-names false}}
                                   {:id "dev-tests"
                                    :source-paths ["src/cljs" "test"]
                                    :figwheel true
                                    :compiler {:main "name-bazaar.cmd"
                                               :output-to "dev-tests/name-bazaar-tests.js",
                                               :output-dir "dev-tests",
                                               :target :nodejs,
                                               :optimizations :none,
                                               :verbose false
                                               :source-map true}}]}}}

  )