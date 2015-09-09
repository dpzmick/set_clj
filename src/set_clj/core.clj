(ns set-clj.core
  (:require [set-clj.set :refer :all])
  (:require [clojure.math.combinatorics :refer [combinations]]))

;; TODO (first (filter )) lazy? does it save extra evaluation?
(defn find-set
  [deck set-size]
  (let
    [combs (combinations deck set-size)]
    (first (filter is-set? combs))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println  "Hello world"))
