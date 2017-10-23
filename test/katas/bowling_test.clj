(ns katas.bowling-test
  (:require [katas.bowling :refer :all]
            [midje.sweet :refer :all]))


(facts "About bowling scores"

  (fact "All zeroes should score 0"
    (score [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]) => 0)

  (fact "All 1's yields 20 points"
    (score [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]) => 20)

  (fact "Scores one spare"
    (score [5 5 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]) => 16)

  (fact "Start with a strike"
    (score [10 3 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]) => 24)

  (fact "Perfect game"
    (score [10 10 10 10 10 10 10 10 10 10 10 10]) => 300)

  (fact "All spares game"
    (score [5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 10]) => 155)

  (fact "One strike game"
    (score [0 0 10 3 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0]) => 24))
