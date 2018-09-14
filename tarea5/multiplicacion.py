def multiplicacion(A, B):
    if len(A[0]) != len(B):
        print("Asegurese de que el numero de columnas en A sea igual al numero de filas en B")
    else:
        res = []
        for x in range(0, len(A)):
            v = []
            for y in range(0, len(B[0])):
                sol = 0
                for z in range(len(A[0])):
                    sol += A[x][z] * B[z][y]
                v.append(sol)
            res.append(v)
        print(res)


"""
multiplicacion([[6, -4, 3], [1, 8, -7]], [[4, 5], [4, 6], [1, 4]])
multiplicacion([[-1, 0], [0, 2]], [[2, 1, 1, 4], [-1, 0, 1, 2]])
"""