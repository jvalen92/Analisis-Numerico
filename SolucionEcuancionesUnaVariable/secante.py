import sympy
from prettytable import PrettyTable
sympy.init_printing(use_unicode=True)

def secante(tol, x0, x1, niter):
    table = PrettyTable(['Iteracion', 'Xn', 'f(xn)', 'Error', 'Error rel'])
    x = sympy.symbols('x')
    #f = sympy.exp((3*x) - 12) + x * sympy.cos(3 * x) - (x ** 2) + 4 
    #f = sympy.sin(x+3) - sympy.ln(x+1) + x**2 - 3
    #f = sympy.exp(-x**2 +1) -4*x**3 + 25
    f = sympy.exp(x) - (5*x) + 2
    fx0 = f.evalf(subs={x:x0})
    if (fx0 == 0): 
        print(x0, "Es raiz")
        table.add_row([0, x0, fx0, '', ''])
    else: 
        fx1 = f.evalf(subs={x:x1})
        contador = 0
        error = tol + 1
        error_rel = tol + 1
        den = fx1 - fx0
        table.add_row([contador, x0, fx0, '',''])
        while error > tol and fx1 != 0 and den != 0 and contador < niter:
            x2 = x1 - fx1 * (x1 - x0) / den
            error = abs(x2 - x1)
            error_rel = error / abs(x2)
            x0 = x1
            fx0 = fx1
            x1 = x2
            fx1 = f.evalf(subs={x:x1})
            den = fx1 - fx0
            contador = contador + 1
            if (contador <= 1): 
                table.add_row([contador, x0, fx0, "", ""])
            else:
                table.add_row([contador, x0, fx0, error, error_rel])

        if fx1 == 0:
            print(x1, " Es raiz")
        elif error < tol:
            print(x1, " Es una aproximacion  con una tolerancia =", tol)
        elif den == 0:
            print("Hay una posible raiz multiple")
        else:
            print("El metodo fracaso en ", niter, " iteraciones")

        print(table)

secante(0.0005, 0.5, 1, 30) 