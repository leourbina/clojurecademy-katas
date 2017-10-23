(ns katas.maze-test
  (:require [katas.maze :refer :all]
            [midje.sweet :refer :all]))

(facts "About solving Mazes"
  (facts "About figuring out where to go"
    (fact "Applies directions appropriately"
      (apply-direction 0 0 :north) => (just [-1 0])
      (apply-direction 0 0 :south) => (just [1 0])
      (apply-direction 0 0 :east) => (just [0 1])
      (apply-direction 0 0 :west) => (just [0 -1]))
    
    (fact "Cannot move outside the board"
      (let [maze [[:S 0 1]
                  [1 0 1]
                  [1 0 :E]]
            directions (allowed-directions 0 0 maze)]
        directions => [:east])))

  (fact "Can solve 3x3 maze"
    (let [maze [[:S 0 1]
                [1 0 1]
                [1 0 :E]]
          sol  [[:x :x 1]
                [1 :x 1]
                [1 :x :x]]]
      (solve-maze maze) => sol))

  (fact "Can solve 4x4 maze"
    (let [maze [[:S 0 0 1]
                [1 1 0 0]
                [1 0 0 1]
                [1 1 0 :E]]
          sol  [[:x :x :x 1]
                [1 1 :x 0]
                [1 0 :x 1]
                [1 1 :x :x]]]
      (solve-maze maze) => sol)))
