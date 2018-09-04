import sympy
from prettytable import PrettyTable
sympy.init_printing(use_unicode=True)

def puntoFijo(tolerancia,xa,niter):
    table = PrettyTable(['Iteracion', 'Xn', 'f(x)', 'Error'])
    x = sympy.symbols('x')
    f = x**3 + 4*(x**2) - 10 
    g = (10/(4+x))**0.5
    fx = f.evalf(subs={x:xa})
    contador = 0
    error = tolerancia + 1 
    table.add_row([contador, xa, fx, 'no existe'])
    while fx != 0 and error > tolerancia and contador < niter:
        xn = g.evalf(subs={x:xa})
        fx = f.evalf(subs={x:xn})
        error = abs(xn-xa)
        xa = xn 
        contador = contador  + 1
        table.add_row([contador, xn, fx, error])
    
    if fx == 0:
        print(xa," Es raiz")
    elif error < tolerancia:
        print(xa,"Es una aproximacion con tolerancia menor a ",tolerancia)
    else:
        print("El metodo fracaso con ",niter," iteraciones")
    print(table)

puntoFijo(0.5e-6,1.5,100)
