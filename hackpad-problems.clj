;; find largest num
(defn find-largest-2 [xs]
  (defn find-lgst-inner [largest xs]
    (let [[head & tail :as all] xs]
      (if (empty? tail)
        (if (> head largest)
          head
          largest)
        (find-lgst-inner (if (> head largest)
                           head
                           tail)
                         tail))))
  (if (empty? xs)
    nil
    (find-lgst-inner (Integer/MIN_VALUE) xs)))

(find-largest-2 [1 2 3 7 9 0])
(find-largest-2 nil)
(find-largest-2 [])

;; sum list
(defn sum-list [xs]
  (loop [[head & tail :as all] xs
         sum 0]
    (if (empty? all)
      sum
      (recur tail (+ sum head)))))

(sum-list [1 2 3 7 9 0])
(sum-list [])

;; compute sum of binary tree numbers
;; print in order

;; word permutations
;; "abc"
;; "cab"
;; a + perm(tail)
(use '[clojure.string :only (join split)])

(defn permutations [xs]
  (defn perm-inner [[head & tail :as all]
                    origHead
                    results]
    (println "last tail")
    (println tail)
    (println "")

    (if (not= (last tail) origHead)
      (do
        (println "not")
        (println (conj tail head))
        (perm-inner (conj) origHead (conj results (join all)))
      results)))
  (perm-inner xs (first xs) nil))

(permutations ["a" "b" "c"])
