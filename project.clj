(defproject set_clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/math.combinatorics "0.1.0"]
                 [clojure-csv/clojure-csv "2.0.1"]]
  :main ^:skip-aot set-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
