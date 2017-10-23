(ns katas.war)

;; War Kata
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn get-rank [coll rank]
  (.indexOf coll rank))

(defn play-round [[suit-1 rank-1 :as player1-card] 
                  [suit-2 rank-2 :as player2-card]]
  (cond
    (> (get-rank ranks rank-1) (get-rank ranks rank-2))
    [[player1-card player2-card] []]

    (< (get-rank ranks rank-1) (get-rank ranks rank-2))
    [[] [player2-card player1-card ]]

    (> (get-rank suits suit-1) (get-rank suits suit-2))
    [[player1-card player2-card] []]

    :else
    [[] [player2-card player1-card ]]))

(defn play-game 
  [player1-cards player2-cards]
  (loop [player1-cards player1-cards
         player2-cards player2-cards]
    (cond
      (= 0 (count player1-cards))
      :player2
      
      (= 0 (count player2-cards))
      :player1

      :else
      (let [[[player1-card] & [player1-rest]] (split-at 1 player1-cards)
            [[player2-card] & [player2-rest]] (split-at 1 player2-cards)
            [player1-result player2-result] (play-round player1-card player2-card)
            new-player1-cards (vec (concat player1-rest player1-result))
            new-player2-cards (vec (concat player2-rest player2-result))]
        (recur new-player1-cards new-player2-cards)))))

