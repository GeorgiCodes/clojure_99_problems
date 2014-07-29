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
(defn flatten-me [xs]
  (loop [[head & tail :as all] xs
         result []]

    (if (empty? all)
      result
      (if (seq? head)
        (recur tail (concat result (flatten-me head)))
        (recur tail (conj result head))))))

(flatten-me '(a (b (c d) e) d))
(flatten-me '(a (b c) d))
;; (a b c d e d)
;; need to add to end of list

;; add each value to new list, if head is list, then recursively add .values

;; problem 8
;; Eliminate consecutive duplicates of list elements.
;; If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
;;
;; Example:
;; * (compress '(a a a a b c c a a d e e e e))
;; (A B C A D E)

(defn remove-duplicates [xs]
  (loop [[head & tail :as all] xs
         result []]
    (if (empty? all)
      result
      (do
        (if (not= head (first tail))
          (do
            (println result)
            (recur tail (conj result head)))
          (recur tail result))))))

(remove-duplicates '("a" "a" "a" "a" "b" "c" "c" "a" "a" "d" "e" "e" "e" "e"))


;; need head and first of tail if not same add head.
  ;; if same, keep looping, only add when not same


;; problem 9
;; Pack consecutive duplicates of list elements into sublists.
;; If a list contains repeated elements they should be placed in separate sublists.
;; Example:
;; * (pack '(a a a a b c c a a d e e e e))
;; ((A A A A) (B) (C C) (A A) (D) (E E E E))

;; add element to sublist,
;; if head is not same as (last tail) add it to sub-list and conj it to result
;;
(defn pack-consecutives [xs]
  (loop [[head & tail :as all] xs
         result []
         sublist []]
    (if (empty? all)
      result
      (do
        (if (= head (first tail))
          (recur tail result (conj sublist head))
          (recur tail (conj result (conj sublist head)) []))
        ))))

(pack-consecutives '(a a a a b c c a a d e e e e f))

;; problem 10
;; (*) Run-length encoding of a list.
;; Use the result of problem P09 to implement the so-called run-length encoding data compression method.
;; Consecutive duplicates of elements are encoded as lists (N E) where N is the number of duplicates of the element E.
;; Example:
;; * (encode '(a a a a b c c a a d e e e e))
;; ((4 A) (1 B) (2 C) (2 A) (1 D)(4 E))


;; approach
;; for each element in consec, perform count and add to new result head, count

(defn run-length-encoding [xs]
  (let [consec (pack-consecutives xs)]
    (loop [[head & tail :as all] consec
           result []]
      (if (empty? all)
        result
        (recur tail (conj result (conj (conj [] (count head)) (first head))))))))

(run-length-encoding '(a a a a b c c a a d e e e e))

;; problem 11
;; (*) Modified run-length encoding.
;; Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list.
;; Only elements with duplicates are transferred as (N E) lists.

;; Example:
;; * (encode-modified '(a a a a b c c a a d e e e e))
;; ((4 A) B (2 C) (2 A) D (4 E))

;; approach
;;

(defn run-length-encoding-no-dups [xs]
  (let [consec (run-length-encoding xs)]
    (loop [[head & tail :as all] consec
           result []]
      (if (empty? all)
        result
        (do (if (< (first head) 2)
              (recur tail (conj result (last head)))
              (recur tail (conj result (conj (conj [] (first head)) (last head))))))))))

(run-length-encoding-no-dups '(a a a a b c c a a d e e e e))


;; problem 12
;; (**) Decode a run-length encoded list.
;; Given a run-length code list generated as specified in problem P11. Construct its uncompressed version.

;; approach
;; if head is list, then for loop for (first head) adding (tail head) to result
;; else add non-list item to result

(defn uncompress-run-length [xs]
  (loop [[head & tail :as all] xs
         result []]
    (if (empty? all)
      result
      (do (if (seq? head)
            (do
              (println (first head))
              (recur tail (repeatedly (first head) (conj result (last head)))))
            (recur tail (conj result head)))))))

(uncompress-run-length ['(5 "a") "b" '(2 "c") '(2 "a") "d" '(4 "e")])

(defn repeat-chars [counter chr]
  (loop [result []
         counter counter]
    (if (= 0 counter)
      result
      (recur (conj result chr) (dec counter)))))

;; P13 (**) Run-length encoding of a list (direct solution).
;; Implement the so-called run-length encoding data compression method directly. I.e. don't explicitly create the sublists containing the duplicates, as in problem P09, but only count them. As in problem P11, simplify the result list by replacing the singleton lists (1 X) by X.

;; Example:
;; * (encode-direct '(a a a a b c c a a d e e e e))
;; ((4 A) B (2 C) (2 A) D (4 E))



;; P14 (*) Duplicate the elements of a list.
;; Example:
;; * (dupli  '(a b c c d))
;; (A A B B C C C C D D)

;; approach
;; add in element to result

(conj (conj [] 'a) 'a)

(defn duplicate-elements [xs]
  (loop [[head & tail :as all] xs
         result []]
    (if (empty? all)
      result
      (recur tail (conj (conj result head) head)))))

(duplicate-elements '(a b c c d))
