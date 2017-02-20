(DEFUN BOUND (X Y)
    (COND
        ((NULL X)
            NIL
        )
        ((NULL Y)
            NIL
        )
        ((ATOM Y)
            NIL
        )
        ((EQ X (CAR (CAR Y)))
            T
        )
        (T
            (BOUND X (CDR Y))
        )
    )
)

(BOUND () ())
(BOUND 3 ())
(BOUND 3 4)
(BOUND 3 (QUOTE ((3 5))))
(BOUND 3 (QUOTE ((23 34)(45 3)(56 23))))
(BOUND 3 (QUOTE ((23 34)(45 3)(3 23))))
