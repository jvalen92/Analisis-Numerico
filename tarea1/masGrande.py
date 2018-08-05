import math

B=1.7
#mientras B sea diferente al infinito definido por Python
while B != math.inf:

    #salir del ciclo con el ultimo valor numerico antes de ser infinito que tiene un valor de 1.699999999999994e+308
    if B/0.0001 == math.inf : 
        #print (B)  
        break
    B= B/0.0001 
"""
Acercarnos mas al infinito multiplicando por 1.xxxxxxx1
entre mas ceros, mas exacto será el resultado pero esto causa mayor tiempo de ejecucion.
"""

while B != math.inf:
    if B*1.00000001== math.inf : #salir del ciclo con el ultimo valor antes de ser infinito
        print(B)
        break
    B= B*1.00000001