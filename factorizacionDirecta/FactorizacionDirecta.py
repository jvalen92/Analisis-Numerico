import numpy as np
class factoriacionDirecta:
    def factoriacionLu(self,A,b):
        L,U = self.factorizacion(A)

    def factorizacion(self,A):
        n = len(A)
        L = np.array((n,n))
        U = np.array((n,n))
        for i in range (n):
            for j in range(i,n):
                print(i)
        return L,U