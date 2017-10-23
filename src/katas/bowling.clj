(ns katas.bowling)

;; Bowling scoring kata
(defn score-frame [points]
  (let [first-throw (nth points 0 0)
        second-throw (nth points 1 0)
        third-throw (nth points 2 0)]
    (cond
      (= 10 first-throw)
      [1 (+ first-throw second-throw third-throw)]

      (= 10 (+ first-throw second-throw))
      [2 (+ first-throw second-throw third-throw)]

      :else
      [2 (+ first-throw second-throw)])))

(defn score [points]
  (loop [points points
         frame 1
         total 0]
    (let [[advance-by frame-points] (score-frame points)]
      (if (= frame 10)
        (+ frame-points total)
        (recur
          (drop advance-by points)
          (+ frame 1)
          (+ total frame-points))))))



