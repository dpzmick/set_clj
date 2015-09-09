(ns set-clj.core
  (:require [set-clj.set :refer :all])
  (:require [clojure.math.combinatorics :refer [combinations]]))

;; TODO (first (filter )) lazy? does it save extra evaluation?
(defn find-set
  [deck set-size]
  (let
    [combs (combinations deck set-size)]
    (first (filter is-set? combs))))

(defn play-game [game]
  (let
    [s (find-set (game-hand game) (set-size game))]
    s))

(defn -main
  [& args]
  (println (play-game (make-game {:color   [:red :green :blue]
                         :shape   [:squiggle :diamond :lawrence]
                         :shading [:solid :open :shaded]
                         :number  [1 2 3]}))))
