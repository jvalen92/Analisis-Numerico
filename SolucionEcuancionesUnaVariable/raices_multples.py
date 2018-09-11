from prettytable import PrettyTable
import sympy as sp

sp.init_printing(use_unicode=True)

def raices_multiples(x0, tolerancia, niter):
    x = sp.symbols('x')
    f = x**4 - 18*x**2+81
    tabla = PrettyTable(['i', 'xn', 'f(xn)', 'df(xn)', 'ddf(xn)', 'ErrorAbs', 'ErrorRel'])
    df = f.diff(x) # Primera derivada
    ddf = df.diff(x) # Segunda derivada
    contador = 0
    xn = x0
    fx = f.evalf(subs={x:xn})
    dfx = df.evalf(subs={x:xn})
    ddfx = ddf.evalf(subs={x:xn})
    tabla.add_row([contador, xn, fx, dfx, ddfx, "No hay error", "No hay error"])
    error = tolerancia + 1.0
    while contador < niter and error > tolerancia:
        xnn = xn - (fx * dfx) / (dfx**2 - fx*ddfx)
        error = abs(xnn-xn)
        xn = xnn
        fx = f.evalf(subs={x:xn})
        dfx = df.evalf(subs={x: xn})
        ddfx = ddf.evalf(subs={x: xn})
        contador = contador + 1
        tabla.add_row([contador, xn, fx, dfx, ddfx, error, abs(error/xn)])
    print(tabla)
raices_multiples(-2.5, 1e-7, 100)