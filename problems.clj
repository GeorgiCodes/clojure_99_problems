;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.

;; problem 1
;; find last element of a list
(defn last-elem [coll]
  (let [[head & tail :as all] coll]
    (if (empty? tail)
      head
      (recur tail))))

(last-elem [1 2 3 4])

;; problem 2
;; find the last but one
;; * (my-but-last '(a b c d))
;; (C D)
(defn last-but-one [xs]
  (loop [coll xs]
    (if (= (count (rest coll))
           1)
      coll
      (recur (rest coll)))))

(last-but-one [1 2 3 4])

;; problem 3
;; Find the K'th element of a list.
;;Example:
;; * (element-at '(a b c d e) 3)
(defn kth [coll k]
  (loop [k k
         coll coll
         idx 0]
    (if (= idx k)
      (first coll)
      (recur k (rest coll) (inc idx)))))
(kth [1 2 3 4 5] 2)

;; problem 4
;; Find the number of elements of a list.
(defn count-elements [coll]
  (loop [[head & tail :as all] coll
         counter 0]
    (if (empty? all)
      counter
      (recur tail (inc counter)))))

(count-elements [1 2 3 4 5])



;; problem 5
;; Reverse a list.
(defn reverse-a-list [xs]
  (loop [[head & tail :as all] xs
         result nil]
    (if (empty? all)
      result
      (recur tail (conj result head)))))

(reverse-a-list ["a" "b" "c"])

;; problem 6



