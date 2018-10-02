import prueba_gauss_parcial as ep
import prueba_gauss_total as et
import EliminacionGaussianaSimple as es
import multiplicacion as mul
import numpy as np

def sb(A, B):
    res = []
    for x in range(0, len(A)):
        prev = []
        for y in range(0, len(A)):
            prev.append(A[x][y] - B[x][y])
        res.append(prev)
    return res
    
def sc(A, k):
    res = []
    for x in range(0, len(A)):
        prev = []
        for y in range(0, len(A)):
            prev.append(A[x][y]*k)
        res.append(prev)
    return res
A = [[0, 2, 3], [7, -1, 20], [1, -14, 8]]
B = [[1, -4, 9], [7, 5, 0], [1, 8, 120]]
b = [1, 1, 1]
c = [-1, 0, 1]
d = [4, -2, 8]

Bt = [[1, 7, 1], [-4, 5, 8], [9, 0, 120]]


Ap = A
Bp = B
bp = b
cp = c
dp = d

print(sb(mul.multiplicacion(A, Bt), sc(A, 2)))


print(np.linalg.solve(np.array(A), np.array(b)))
print(es.despeje(Ap, bp))
Ap = A
print(np.linalg.solve(np.array(A), np.array(c)))
print(ep.despejar_parcial(Ap, c))
marcas = []
for i in range(0, len(A)):
    marcas.append(i)
Ap = A
print(np.linalg.solve(np.array(A), np.array(d)))
print(et.despejar_total(Ap, d, marcas))