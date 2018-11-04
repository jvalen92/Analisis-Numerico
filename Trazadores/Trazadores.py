import numpy as np
import sympy as sp
from sympy.utilities.lambdify import lambdify
import matplotlib.pyplot as plt

def trazadoresLineales(xi,y):
    file = open("trazadoresLinealesP.txt","w")
    n = len(xi)-1
    A = np.zeros((n*2,n*2))
    b = np.zeros(n*2)
    x = sp.symbols("x")
    polinomios = []
    for i in range(0,n):
        j           = 2*i
        A[j][j]     = xi[i]
        A[j][j+1]   = 1
        b[j]        = y[i]
        A[j+1][j]   = xi[i+1]
        A[j+1][j+1] = 1
        b[j+1]      = y[i+1]
    ab = np.linalg.solve(A,b)
    j = 0
    for i in range(0,len(ab),2):
        polinomio = []
        polinomio.append(ab[i]*x + ab[i+1])
        polinomio.append((xi[j],xi[j+1]))
        polinomios.append(polinomio)
        a = np.arange(polinomio[1][0],polinomio[1][1]+0.1,0.1)
        f = lambdify(x,polinomio[0],"numpy")
        plt.plot(a,f(a))
        j+=1
    plt.show()
    file.write("Matriz A \n")
    file.write(str(A))
    file.write("\n Vector b \n")
    file.write(str(b))
    file.write("\n vector de ab \n")
    file.write(str(ab))
    file.write("\n polinomio \n")
    file.write(str(polinomios))
    file.close()
    return polinomios

def trazadoresCuadraticos(xi,y):
    n = len(xi)-1
    A = np.zeros((n*3,n*3))
    b = np.zeros(n*3)
    x = sp.symbols("x")
    polinomios = []
    file = open("TrazadoresCuadraticosp.txt","w")
    for i in range(n):
        j = 2*i
        l = 3*i
        A[j][l]     = xi[i]**2
        A[j][l+1]   = xi[i]
        A[j][l+2]   = 1
        b[j]        = y[i]
        A[j+1][l]   = xi[i+1]**2
        A[j+1][l+1] = xi[i+1]
        A[j+1][l+2] = 1
        b[j+1]      = y[i+1]
    j=0
    l=1
    for i in range(n*2,(n*3)-1):
        A[i][j]   = 2*xi[l]
        A[i][j+1] = 1
        A[i][j+3] = -2*xi[l]
        A[i][j+4] = -1
        j+=3
        l+=1
    A[(3*n)-1][0] = 1
    abc = np.linalg.solve(A,b)
    j=0
    for i in range(0,len(abc),3):
        polinomio = []
        polinomio.append(abc[i]*x**2 + abc[i+1]*x+abc[i+2])
        polinomio.append((xi[j],xi[j+1]))
        polinomios.append(polinomio)
        a = np.arange(polinomio[1][0],polinomio[1][1]+0.001,0.00001)
        f = lambdify(x,polinomio[0],"numpy")
        plt.plot(a,f(a))
        j+=1
    plt.show()
    file.write("Matriz A \n")
    file.write(str(A))
    file.write("\n Vector b \n")
    file.write(str(b))
    file.write("\n vector de abc \n")
    file.write(str(abc))
    file.write("\n polinomio \n")
    file.write(str(polinomios))
    file.close()
    return polinomios

def trazadoresCubicos(x,y):
    n = len(x)-1
    A = np.zeros((n*4,n*4))
    b = np.zeros(n*4)
    xi = sp.symbols("xi")
    file = open("trazadoresCubicosp.txt","w")
    polinomios = []
    for i in range(n):
        j = 2*i
        l = 4*i
        A[j][l]     = x[i]**3
        A[j][l+1]   = x[i]**2
        A[j][l+2]   = x[i]
        A[j][l+3]   = 1
        b[j]        = y[i]
        A[j+1][l]   = x[i+1]**3
        A[j+1][l+1] = x[i+1]**2
        A[j+1][l+2] = x[i+1]
        A[j+1][l+3] = 1
        b[j+1]      = y[i+1]
    j=0
    l=1
    for i in range(n*2,(n*3)-1):
        A[i][j]   = 3*x[l]**2
        A[i][j+1] = 2*x[l]
        A[i][j+2] = 1
        A[i][j+4] = -3*x[l]**2
        A[i][j+5] = -2*x[l]
        A[i][j+6] = -1
        j+=4
        l+=1
    j=0
    l=1
    for i in range((n*3)-1,(n*4)-2):
        A[i][j]   = 6*x[l]
        A[i][j+1] = 2
        A[i][j+4] = -6*x[l]
        A[i][j+5] = -2
        j+=4
        l+=1
    A[(4*n)-2][0] = 6*x[0]
    A[(4*n)-2][1] = 2
    A[4*n-1][4*n-4] = 6*x[n]
    A[4*n-1][4*n-3] = 2 
    abcd = np.linalg.solve(A,b)
    j=0
    for i in range(0,len(abcd),4):
        polinomio = []
        polinomio.append(abcd[i]*xi**3 + abcd[i+1]*xi**2 +abcd[i+2]*xi +abcd[i+3])
        polinomio.append((x[j],x[j+1]))
        polinomios.append(polinomio)
        a = np.arange(polinomio[1][0],polinomio[1][1]+0.1,0.1)
        f = lambdify(xi,polinomio[0],"numpy")
        plt.plot(a,f(a))
        j+=1
    plt.show()
    file.write("Matriz A \n")
    file.write(str(A))
    file.write("\n Vector b \n")
    file.write(str(b))
    file.write("\n vector de abcd \n")
    file.write(str(abcd))
    file.write("\n polinomio \n")
    file.write(str(polinomios))
    file.close()
    return polinomios

x1 =[
    1.0000,    
    2.0000,    
    3.0000,    
    4.0000,    
    5.0000,    
    6.0000,    
    7.0000,    
    8.0000,    
    9.0000,    
   10.0000 ]
y1 = [
    0.5949,
    0.2622,
    0.6028,
    0.7112,
    0.2217,
    0.1174,
    0.2967,
    0.3188,
    0.4242,
    0.5079]
x = [1.0,3.0,4.0,5.0,7.0]
y = [4.31,1.5,3.2,2.6,1.8]
polinomios1 = trazadoresLineales(x1,y1)
polinomios2 = trazadoresCuadraticos(x1,y1)
polinomios3 = trazadoresCubicos(x1,y1)
print(polinomios1)
print("-----------------")
print(polinomios2)
print("-----------------")
print(polinomios3)
