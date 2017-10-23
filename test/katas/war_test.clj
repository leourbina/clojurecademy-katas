(ns katas.war-test
  (:require [katas.war :refer :all]
            [midje.sweet :refer :all]))

(facts "About War"
  (fact "The highest rank wins the cards in the round"
    (play-round [:spade 4] [:spade 2]) => [[[:spade 4] [:spade 2]] []]
    
    (play-round [:spade 2] [:spade 4]) => [[] [[:spade 4] [:spade 2]]])

  (fact "Queens are higher rank than jacks"
    (play-round [:spade :queen] [:spade :jack]) => [[[:spade :queen] [:spade :jack]] []])

  (fact "Kings are higher rank than queens"
    (play-round [:spade :king] [:spade :queen]) => [[[:spade :king] [:spade :queen]] []])

  (fact "Aces are higher rank than kings"
    (play-round [:spade :ace] [:spade :king]) => [[[:spade :ace] [:spade :king]] []])

  (fact "If the ranks are equal, clubs beat spades"
    (play-round [:club 2] [:spade 2]) => [[[:club 2] [:spade 2]] []])

  (fact "If the ranks are equal, diamond beats clubs"
    (play-round [:diamond 2] [:club 2]) => [[[:diamond 2] [:club 2]] []])
  
  (fact "The player loses when they run out of cards"
    (let [win-start  [[:heart 10]]
          lose-start [[:heart 9]]]
      (play-game win-start lose-start) => :player1

      (play-game lose-start win-start) => :player2)))

