(ns set-clj.core
  (:require [set-clj.set :refer :all])
  (:require [clojure.math.combinatorics :refer [combinations]]))

;; TODO (first (filter )) lazy? does it save extra evaluation?
(defn find-set
  [deck set-size]
  (let
    [combs (combinations deck set-size)]
    (first (filter is-set? combs))))

(defn play-game
  ([game] (play-game game []))
  ([game sets]
   (let [s (find-set (game-hand game) (set-size game))]
     (if (nil? s)
       ; there are no sets on the board
       (let [new-game (draw game)]
         (if (= :empty new-game)
           ; there are no more cards to draw and no sets on the board
           sets
           ; there may be more sets to find
           (recur (remove-set s new-game) (cons s sets))))
       (let
         [new-game (draw (remove-set s game))]
         (if (= :empty new-game)
           ; no more cards to draw, but there might be more sets
           (recur (remove-set s game) (cons s sets))
           ; normal case
           (recur new-game (cons s sets))))))))

(defn -main
  [& args]
  (println "found this many sets: "
           (count (play-game (make-game {:color   [:red :green :blue]
                                         :shape   [:squiggle :diamond :lawrence]
                                         :shading [:solid :open :shaded]
                                         :number  [1 2 3]})))))
