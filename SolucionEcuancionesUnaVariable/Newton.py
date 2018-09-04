import sympy
from prettytable import PrettyTable
sympy.init_printing(use_unicode=True)

def newton(tol,x0,niter):
    table = PrettyTable(['Iteracion', 'Xn', 'f(x)', 'Error'])
    x = sympy.symbols('x')
    f = sympy.exp((3*x) - 12) + x * sympy.cos(3 * x) - (x ** 2) + 4 
    df = f.diff(x)
    fx = f.evalf(subs={x:x0})
    dfx = df.evalf(subs={x:x0})
    contador = 0
    error = tol + 1
    table.add_row([contador, x0, fx, 'no existe'])
    while fx != 0 and dfx != 0 and error > tol and contador < niter:
        x1 = x0- (fx/dfx)
        fx = f.evalf(subs={x:x1})
        dfx = df.evalf(subs={x:x1})
        error = abs(x1 -x0)
        x0 = x1
        contador = contador + 1
        table.add_row([contador, x0, fx, error])
    
    if fx == 0:
        print(x0," Es raiz")
    elif error < tol:
        print(x0," Es una aproximacion  con una tolerancia menor a ",tol)
    elif dfx == 0:
        print(x0," Es una posible raiz multiple")
    else:
        print("El metodo fracaso en ",niter," iteraciones")
    print(table)
    
newton(0.0005,-4,30)