(ns katas.maze)

;; Backtracking to solve mazes
(defn find-maze-start [maze]
  "Takes maze and returns it replacing :S with :x along with [x y] indicating the indices replaced"
  ;; Assume [0 0] start for now
  [[0 0] (assoc-in maze [0 0] :x)])

(def directions [:north :east :south :west])

(defn opposite-direction [direction]
  (case direction
    :north :south
    :south :north
    :east :west
    :west :east
    direction))

(defn apply-direction [x y direction]
  (case direction
    :east [x (+ y 1)]
    :south [(+ x 1) y]
    :west [x (- y 1)]
    :north [(- x 1) y]))

(defn in-range? [x min max]
  (and
    (>= x min)
    (< x max)))

(defn within-limits? [x y maze]
  (let [x-width (count maze)
        y-width (count (get maze 0))]
    (and
      (in-range? x 0 x-width)
      (in-range? y 0 y-width))))

(defn position-unblocked? [x y maze]
  (not= 1 (get-in maze [x y])))

(defn direction-allowed? [x y maze direction]
  (let [[new-x new-y] (apply-direction x y direction)]
    (and
      (within-limits? new-x new-y maze)
      (position-unblocked? new-x new-y maze))))

(defn allowed-directions [x y maze]
  (filter (partial direction-allowed? x y maze) directions))

(defn solve-maze-from-coordinates [x y maze direction]
  (let [current-slot (get-in maze [x y])
        new-maze (assoc-in maze [x y] :x)]
    (case current-slot
      :E new-maze

      1 nil
      
      (->
        (for [candidate-direction (->> (allowed-directions x y new-maze)
                                    (filter #(not= % (opposite-direction direction))))
              :let [[new-x new-y] (apply-direction x y candidate-direction)
                    solution (solve-maze-from-coordinates new-x new-y new-maze candidate-direction)]
              :when (some? solution)]
          solution)
        first))))

(defn solve-maze [maze]
  (solve-maze-from-coordinates 0 0 maze :none))



