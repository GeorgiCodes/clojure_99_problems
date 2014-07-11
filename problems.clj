;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.

;; problem 1
;; find last element of a list
(defn last-elem [coll]
  (loop [[head & tail :as all] coll]
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
;; Find out whether a list is a palindrome.
(defn is-palindrome [xs]
  (= xs (reverse-a-list xs)))

(is-palindrome ["x" "a" "m" "a" "x"])
(is-palindrome nil)
(is-palindrome ["x" "a"])

;; problem 7
;; Flatten a nested list structure.
;; Transform a list, possibly holding lists as elements into a `flat' list by replacing each list with its elements (recursively).
;; HELP!
(defn is-list [coll]
  (= (type '(1 2 3)) clojure.lang.PersistentList))

(is-list '(1 2 3))

(defn flatten-me [xs]
  (loop [[head & tail :as all] xs
         result nil]
    (if (empty? all)
      result
      (if (is-list head)
        (recur tail (conj result head)))))

(flatten-me '(a (b (c d) e)))

;; add each value to new list, if head is list, then recursively add .values

;; problem 8
;; Eliminate consecutive duplicates of list elements.
;; If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
;;
;; Example:
;; * (compress '(a a a a b c c a a d e e e e))
;; (A B C A D E)

(defn remove-duplicates [xs]
  )

(remove-duplicates '(a a a a b c c a a d e e e e))


