(def global-vector-variable (atom nil))
(def check (atom 0))
(def final-x (atom -1))
(def final-y (atom -1))



(def possible-movements [[1 0] [0 1] [-1 0] [0 -1]])

(defn is-movement-possible [final-data-vec pos]
  ((complement contains?)
    #{\+ \# \! nil}
    (get-in final-data-vec pos)))

(defn possible-movement [final-data-vec pos]
  (->> possible-movements
       (map (partial map + pos))
       (filter (partial is-movement-possible final-data-vec))))

(defn positive-path [final-data-vec pos]
  (assoc-in final-data-vec pos \+))

(defn blocked-path [final-data-vec pos]
  (assoc-in final-data-vec pos \!))

(defn final-treasure [final-data-vec pos]
  (assoc-in final-data-vec pos \@))

(defn treasure-found [final-data-vec [x y]]  
  (def found (= ((final-data-vec x) y) \@))
  (if (= found true)
    (do
      (def final-x x)
      (def final-y y)
      (def check 1)
      (= 1 1)
    )
    (do (= 1 2)
    )  
  )  
)

(def recursive
  (partial some identity))


(defn moving-path [final-data-vec pos]
  (let [new-vector (positive-path final-data-vec pos)
        next-moves (possible-movement final-data-vec pos)]
    (if (= check 0) 
      (do (def global-vector-variable (positive-path global-vector-variable pos))))
    (cond (treasure-found final-data-vec pos) new-vector
          (empty? next-moves) nil
          :else (recursive (map (partial moving-path new-vector) next-moves)))
    (if (or (= check 0) (and (empty? next-moves) (not= check 1)))
      (do (def global-vector-variable (blocked-path global-vector-variable pos))))
  )  
)


(defn start-traversal [final-data-vec]
    (def global-vector-variable final-data-vec) 
    (def check 0)
    (def final-x -1)
    (def final-y -1) 
    (moving-path final-data-vec '(0 0))
    (if (or (not= final-x -1) (not= final-y -1))
      (do (def global-vector-variable (final-treasure global-vector-variable [final-x final-y]))))
    (if (= check 1)
      (do (println "Woo hoo, I found the treasure :-)\n")
      )
      (do (println "Uh oh, I could not find the treasure :-(\n")
      )
    )


)

(defn final-output [final-vec row col] 
  (doseq [x (range row)]
      (println (clojure.string/join (nth final-vec x)))
  )


)

;;Reading File
(defn reading-file
  ([file]
    (reading-file file identity))
  
  ([file printing-function]
    (reading-file file printing-function println))
  
  ([file printing-function output-fn]
      (def count_row 0)
      (def count_col 0)
      (println "This is a challenge:")
      (println "")
      ;;PENDING make a check if each row has same number of column 
      (def flag 0)
      (def test_col_length 0)
      (with-open [rdr (clojure.java.io/reader file)]       
        (doseq [line (line-seq rdr)]
          (println line)
          (def count_row (+ count_row 1))
          (def count_col (count line))
          (if (= count_row 1)
              (do (def test_col_length count_col))
              (do (if (not= test_col_length count_col)
                      (do (def flag 1))
                  )
              )
          )
      ))
      (if (= flag 1)
        (do 
        (println "\nMap is not valid")
        (System/exit 0) 
        )
      )
      (println "")      
      ;;Reading file and converting it into vector
      (def final-data-vec [])
      (with-open [rdr (clojure.java.io/reader file)]       
        (doseq [line (line-seq rdr)]
          (def temp_list (seq line))
          ;;Assiging value to vector
          (def temp_vec (vec temp_list))
          (def final-data-vec (conj final-data-vec temp_vec))
                        
        )
      )
     
      (start-traversal final-data-vec)         
      (final-output global-vector-variable count_row count_col)    
  )
)
   
   
(reading-file "map.txt")

