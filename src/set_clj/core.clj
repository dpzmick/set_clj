(ns set-clj.core)

;; all values the same
;; all values different
;; no values nil
(defn check-single-attribute
  "Checks if a single attribute satisfies the set property for a collection of cards"
  [attr cards]
  (let
    [card-attr-values (map attr cards)]
    (and
      (empty? (filter nil? card-attr-values))
      (or
          (apply distinct? card-attr-values)
          (= (count (set card-attr-values)) 1)))))

(defn all-have-same-attributes
  "checks if all the cards have the same attributes defined"
  [cards]
  (let
    [target-keys (set (keys (first cards)))]
    (every? true?
      (map
        #(= (set (keys %)) target-keys)
        cards))))

(defn is-set?
  "checks if a collection of cards is a set"
  [cards]
  (and
    (all-have-same-attributes cards)
    (every? true?
      (map
        #(check-single-attribute % cards)
        (keys (first cards))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
