def det(A, n):
    if n == 1:
        return A[0][0]
    resultado = 0
    for x in range(0, n):
        resultado += power(-1, x) * A[0][x] * det(nueva_matriz(A, x), n - 1)
    return resultado


def nueva_matriz(A, i):
    Ap = []
    for x in range(1, len(A)):
        v = []
        for y in range(0, len(A)):
            if y != i:
                v.append(A[x][y])
        Ap.append(v)
    return Ap


def power(a, b):
    res = 1
    while(b > 0):
        if b & 1 == 1:
            res = res * a
        a = a * a
        b >>= 1
    return res


"""
A = [[4, 2, 3, -4], [3, -2, 1, 5], [-2, 0, 1, -3], [8, -2, 6, 4]]
print(det(A, len(A)))
"""