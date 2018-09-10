import sympy
from prettytable import PrettyTable
sympy.init_printing(use_unicode=True)

def newton(tol,x0,niter):
    table = PrettyTable(['Iteracion', 'Xn', 'f(x)', 'Error','Error rel'])
    x = sympy.symbols('x')
    #f = sympy.exp((3*x) - 12) + x * sympy.cos(3 * x) - (x ** 2) + 4 
    #f = sympy.sin(x+3) - sympy.ln(x+1) + x**2 - 3
    #f = sympy.exp(-x**2 +1) -4*x**3 + 25
    f = x**3 + 4*x**2 - 10
    df = f.diff(x)
    fx = f.evalf(subs={x:x0})
    dfx = df.evalf(subs={x:x0})
    contador = 0
    error = tol + 1
    table.add_row([contador, x0, fx, 'no existe','no existe'])
    while fx != 0 and dfx != 0 and error > tol and contador < niter:
        x1 = x0- (fx/dfx)
        fx = f.evalf(subs={x:x1})
        dfx = df.evalf(subs={x:x1})
        error = abs(x1 -x0)
        error_rel=abs((x0-x1)/x1)
        x0 = x1
        contador = contador + 1
        table.add_row([contador, x0, fx, error,error_rel])
    
    if fx == 0:
        print(x0," Es raiz")
    elif error < tol:
        print(x0," Es una aproximacion  con una tolerancia menor a ",tol)
    elif dfx == 0:
        print(x0," Es una posible raiz multiple")
    else:
        print("El metodo fracaso en ",niter," iteraciones")
    print(table)
    
#newton(0.0005,-4,30)
#newton(10e-4,2,30)
newton(10e-8,1.5,30 )
