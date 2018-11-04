import numpy as np
#puntos = [(x1,y1),(x2,y2)]
def diferenciasDivididas(puntos):
    n = len(puntos)
    md = np.zeros((n,n))
    #Cero 
    for i in range(n):
        md[i][0] =  puntos[i][1]
    #Primera
    for i in range(1,n):
        for j in range(i,n):
            md[j][i] = (md[j][i-1]-md[j-1][i-1])/(puntos[j][0]-puntos[j-i][0])
    return md
puntos = [(1.0000  ,  0.5949),
    (2.0000   , 0.2622),
    (3.0000   , 0.6028),
    (4.0000   , 0.7112),
    (5.0000   , 0.2217),
    (6.0000   , 0.1174),
    (7.0000   , 0.2967),
    (8.0000   , 0.3188),
    (9.0000  ,  0.4242),
   (10.0000  ,  0.5079)]
print(diferenciasDivididas(puntos))