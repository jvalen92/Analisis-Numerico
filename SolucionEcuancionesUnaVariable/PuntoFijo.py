import sympy
from prettytable import PrettyTable
sympy.init_printing(use_unicode=True)

def puntoFijo(tolerancia,xa,niter):
    table = PrettyTable(['Iteracion', 'Xn', 'f(x)', 'Error','Error rel'])
    x = sympy.symbols('x')
    #f = sympy.exp((3*x) - 12) + x * sympy.cos(3 * x) - (x ** 2) + 4 
    #g = -sympy.sqrt(x*sympy.exp(x)-5*x-3)
    f = x**3 + 4*x**2 - 10
    g = sympy.sqrt(10/(4+x))
    fx = f.evalf(subs={x:xa})
    contador = 0
    error = tolerancia + 1 
    table.add_row([contador, xa, fx, 'no existe','no existe'])
    while fx != 0 and error > tolerancia and contador < niter:
        xn = g.evalf(subs={x:xa})
        fx = f.evalf(subs={x:xn})
        error = abs(xn-xa)
        error_rel = abs((xn-xa) / xn)
        xa = xn 
        contador = contador  + 1
        table.add_row([contador, xn, fx, error,error_rel])
    
    if fx == 0:
        print(xa," Es raiz")
    elif error < tolerancia:
        print(xa,"Es una aproximacion con tolerancia menor a ",tolerancia)
    else:
        print("El metodo fracaso con ",niter," iteraciones")
    print(table)

#puntoFijo(0.0005,-4,30)
puntoFijo(0.5*10e-8,1.5,30)