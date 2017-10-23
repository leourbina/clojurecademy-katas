(ns katas.alphabet)

;; Alphabet cypher (ascii assumed)
;; lowercase ascii range is 97-122

(def ascii-base 97)
(def ascii-length 26)

(defn- translate-char [operator [keychar c]]
  (-> (int c)
    (operator (- (int keychar) ascii-base))
    (- ascii-base)
    (mod ascii-length)
    (+ ascii-base)
    char))

(defn translate [keyword message operator]
  (let [keyword-seq (cycle keyword)]
    (->> message
      (map vector keyword-seq)
      (map (partial translate-char operator))
      clojure.string/join)))

(defn encode [keyword message]
  (translate keyword message +))

(defn decode [keyword message]
  (translate keyword message -))

(defn decipher [cipher message]
  (let [repeating-keyword (translate message cipher -)]
    (loop [period 1]
      (let [candidate (take period repeating-keyword)
            full-word (clojure.string/join (take (count cipher) (cycle candidate)))]
        (if (= full-word repeating-keyword)
          (clojure.string/join candidate)
          (recur (+ 1 period)))))))
