(ns casino.core
  (:require [clojure.set :as set]))

(def card->score {:2 2
                  :3 3
                  :4 4
                  :5 5
                  :6 6
                  :7 7
                  :8 8
                  :9 9
                  :10 10
                  :B 10
                  :D 10
                  :K 10
                  :A 11})

;; Herz Hearts
;; Pik Spades
;; Karo Diamonds
;; Kreuz Clubs

(defn init-deck []
  (let [single-deck (set (keys card->score))]
    {:C single-deck
     :S single-deck
     :H single-deck
     :D single-deck}))

(defn draw-card [deck]
   (let [color (rand-nth (keys deck))
         card (rand-nth (seq (get deck color)))
         new-deck (set/difference (get deck color)
                                  #{card})]
     {:card [color card]
      :new-deck (assoc deck color new-deck)}))



(defn take-hand [deck]
  (let [{deck :new-deck
         fst :card} (draw-card deck)
        {deck :new-deck
         snd :card} (draw-card deck)
        {deck :new-deck
         trd :card} (draw-card deck)]
    {:hand [fst snd trd]
     :new-deck deck}))


(defn score-hand [hand]
  (->> hand
       (map second)
       (map card->score)
       (reduce +)))

(score-hand (get (take-hand (init-deck)) :hand))


(def casino {:playerz {"Mr. Dealer" []
                       "Ana" []
                       "Jules" []}})
