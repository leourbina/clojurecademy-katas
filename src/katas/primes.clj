(ns katas.primes)

(defn prime-sieve [n]
  "Compute prime sieve up until sqrt(n)"
  (let [root (int (java.lang.Math/sqrt 10))]
    (loop [prime-index 0
           sieve (vec (range 2 (+ n 1)))]
      (let [[known-primes rest] (split-at (+ 1 prime-index) sieve)
            current-prime (last known-primes)]
        (if (> current-prime root)
          sieve
          (recur
            (+ 1 prime-index)
            (concat
              known-primes
              (filter
                #(not= 0 (mod % current-prime))
                rest))))))))

;; Prime kata
(defn prime-factors [n]
  (let [primes (prime-sieve n)]
    (loop [n n
           prime-factors [] 
           prime-candidates primes]
      (if (= n 1)
        prime-factors
        (let [current-prime (first prime-candidates)]
          (if (->> current-prime
                (mod n)
                (= 0))
            (recur
              (quot n current-prime)
              (conj prime-factors current-prime)
              prime-candidates)
            (recur
              n
              prime-factors
              (drop 1 prime-candidates))))))))

