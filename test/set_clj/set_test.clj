(ns set-clj.set-test
  (:require [clojure.test :refer :all]
            [set-clj.set  :refer :all]))

(deftest is-valid-attrs
  (testing "does it recognize valid attribute map"
    (is (is-valid-attrs? {:color [:red :blue]
                          :shape [:oval :sqiggle]}))))

(deftest is-not-valid-attrs
  (testing "does it recognize invalid attribute map"
    (is (not (is-valid-attrs? {:color [:red :blue]
                               :shape [:sqiggle]})))))

(deftest test-check-single-attr-same
  (testing "does it recognize all same attribute"
    (is (check-single-attribute :color [{:color :red}
                                        {:color :red}
                                        {:color :red}]))))

(deftest test-check-single-attr-diff
  (testing "does it recognize all different attribute"
    (is (check-single-attribute :color [{:color :red}
                                        {:color :green}
                                        {:color :bleu}]))))

(deftest test-check-single-attr-almost-diff
  (testing "does it recognize almost all different attribute"
    (is (not (check-single-attribute :color [{:color :red}
                                             {:color :red}
                                             {:color :bleu}])))))

(deftest test-check-single-attr-not-def
  (testing "does it return false when one of the cards doesn't have the right elements?"
    (is (not (check-single-attribute :color [{:color :red}
                                             {:shape :squiggle}
                                             {:color :bleu}])))))

(deftest is-set
  (testing "does it recognize a valid set"
        (is (is-set? [{:color :red, :shape :squiggle, :number 1}
                      {:color :red, :shape :squiggle, :number 2}
                      {:color :red, :shape :squiggle, :number 3}]))))

(deftest is-not-set
  (testing "does it recognize a valid set"
        (is (not (is-set? [{:color :red, :shape :oval,     :number 2}
                           {:color :red, :shape :squiggle, :number 2}
                           {:color :red, :shape :squiggle, :number 3}])))))

;; TODO test gen-deck

(deftest test-remove-all
  (testing "does it remove them"
    (is (empty? (remove-all [1 2 3] [1 2 3])))))

(deftest test-remove-all2
  (testing "does it remove them"
    (is (= [2] (remove-all [1 3] [1 2 3])))))
