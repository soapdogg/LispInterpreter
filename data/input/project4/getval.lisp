
(DEFUN GETVAL (X Z)
    (COND
        ((EQ X (CAR (CAR Z)))
            (CDR (CAR Z))
        )
        (T
            (GETVAL X (CDR Z))
        )
    )
)

(GETVAL 3 (CONS (CONS 3 5) NIL))
(GETVAL 45 (CONS (CONS 3 34) (CONS (CONS 45 (QUOTE (3 4 5))) (CONS (CONS 3 23) NIL))))
(GETVAL 1 (CONS (CONS 1 23) (CONS (CONS 1 48) NIL)))
(GETVAL 564 (CONS (CONS 34 4) (CONS (CONS 23 4) (CONS (CONS 564 34) NIL))))

