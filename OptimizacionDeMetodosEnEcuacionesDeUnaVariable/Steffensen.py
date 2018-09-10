import sympy
from prettytable import PrettyTable
sympy.init_printing(use_unicode=True)

def stefenssen(x0,niter,tol):
    table = PrettyTable(['i', 'x0', 'x1','x2','x','f(x)','Error'])
    x = sympy.symbols('x')
    f = x**3 + 4*x**2 - 10
    g = sympy.sqrt(10/(4+x))
    contador = 0
    error = tol+1
    x1 = g.evalf(subs={x:x0})
    x2 = g.evalf(subs={x:x1})
    p = x0 - ((x1-x0)**2/( x2 - 2*x1 + x0))
    table.add_row([contador, x0, x1, x2,p,f.evalf(subs={x:p}), "no existe"])
    x0=p
    while contador < niter and error > tol:
        x1 = g.evalf(subs={x:x0})
        x2 = g.evalf(subs={x:x1})
        p = x0 - ((x1-x0)**2/( x2 - 2*x1 + x0))
        error=abs(p-x0)
        contador = contador + 1
        table.add_row([contador, x0, x1, x2,p,f.evalf(subs={x:p}), error])
        x0=p
    if (error <= tol):
        print("hay una raiz en: ",p)
    else:
        print("el metodo falló despues de ",niter, " iteraciones" )
    print (table)

stefenssen(1.5,30,0.5*10e-8)