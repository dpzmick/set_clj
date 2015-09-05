(ns set-clj.core)

;; all values the same
;; all values different
;; no values nil
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
        (keys (first cards))))))

(defn generate-ordered-deck
  "generates a deck of cards given a list of attributes"
  [attr-values-map]
  (if (empty? attr-values-map)
    []
    (let
      [curr-pair      (first attr-values-map)
       curr-attr      (first curr-pair)
       curr-attr-vals (second curr-pair)]
      (if (= 1 (count attr-values-map))
        (map #(hash-map curr-attr %) curr-attr-vals)
        (let
          [the-rest (generate-ordered-deck (rest attr-values-map))] ; kill the stack
          (reduce
            (fn [acc value]
              (concat acc (map #(conj % (hash-map curr-attr value)) the-rest)))
            []
            curr-attr-vals))))))

(defn generate-deck [attrs] (shuffle (generate-ordered-deck attrs)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
