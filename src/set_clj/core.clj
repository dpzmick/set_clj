(ns set-clj.core
  (:require  [clojure.math.combinatorics :refer [combinations]]))

(defn is-valid-attrs?
  "checks if every attribute has the same number of values"
  [attrs]
  (apply = (map #(count (% attrs)) (keys attrs))))

;;; all values the same
;;; all values different
;;; no values nil
(defn check-single-attribute
  "Checks if a single attribute satisfies the set property for a sequence of cards"
  [attr cards]
  (let
    [card-attr-values (map attr cards)]
    (and
      (not (some nil? card-attr-values))
      (or
          (apply distinct? card-attr-values)
          (apply =         card-attr-values)))))

(defn is-set?
  "checks if a sequence of cards is a set"
  [cards]
  (and
    (apply = (map keys cards))
    (every? true?
      (map
        #(check-single-attribute % cards)
        (keys (first cards)))))) ; all cards have same keys, so use keys of first

;; at least its tail recursive :(
(defn gen-deck
  "generates an ordered deck from the attribute map given"

  ([attrs]
   (gen-deck
     (rest attrs)
     (map #(hash-map (first (first attrs)) %) (second (first attrs)))))

  ([attrs cards]
   (if (empty? attrs)
     (apply vector cards)
     (let
       [pair   (first attrs)
        attr   (first pair)
        values (second pair)]
       (recur
         (rest attrs)
         (flatten (map (fn [card] (map (fn [value] (assoc card attr value)) values)) cards)))))))

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
