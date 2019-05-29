;;;;;;;;;;;;;;;;;;;;;;;
                                ;;Creating 2D array
                                (comment
                                (defn array-creation [final-data count_row count_col]
                                  (doseq [x (range count_row) y (range count_col)]
                                      (def each_row (nth final-data x))
                                      (print (nth each_row y))
                                      (def temp_count (- count_col 1))
                                      (if (== y temp_count)
                                        (println))

                                  )
                                )
                                )


;;////////////Comment//////////////////
                  (comment 
                      (def final-data (make-array Character/TYPE count_row count_col))
                      (def temp_count_row 0)
                  )


;;////////////Comment//////////////////
                    (comment
                        ;;Assigning value to 2D array
                        (doseq [x (range (count temp_list))]
                          (aset-char final-data temp_count_row x (nth temp_list x)))
                        (def temp_count_row (+ temp_count_row 1))
                    )
                    ;;(output-fn (printing-function line))

;;Vector creation function
(defn vector-creation [final-data-vec]
   (def te final-data-vec)
   (def pos 0)
   (def te (assoc-in te [pos pos] 5))
   (vector te) ;;Returing

)

   ;;(def final-data-vec (nth (vector-creation final-data-vec) 0))
   ;;(println final-data-vec)
   ;;(array-creation final-data count_row count_col)



   (if (empty? next-moves)
      (do ))