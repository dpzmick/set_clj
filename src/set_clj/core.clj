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

;; TODO this seems really messy
(defn all-have-same-attributes
  ([cards] (all-have-same-attributes (rest cards) (set (keys (first cards)))))
  ([cards attrs]
   (if (empty? cards)
     true
     (and
       (= (set (keys (first cards))) attrs)
       (recur (rest cards) attrs))) ))

;; check that all attributes are valid for a set
(defn is-set?
  [cards]
  (let
    [attrs-validated (map (fn [attr] check-single-attribute attr cards))]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
