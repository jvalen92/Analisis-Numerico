from prettytable import PrettyTable
import sympy
sympy.init_printing(use_unicode=True)

def biseccion(xi, xs, tolerancia, niter):
    table = PrettyTable(['i', 'Xinf', 'Xsup', 'Xmi', 'f(Xmi)', 'Error'])
    x = sympy.symbols('x')
    ##fx = sympy.exp((3*x) - 12) + x * sympy.cos(3 * x) - (x ** 2) + 4
    fx = x**3 + 4*(x**2) - 10
    ##fx = ((x + 4)**(-5/2))
    fxi = fx.evalf(subs={x:xi})
    fxs = fx.evalf(subs={x:xs})
    print(fxi, fxs)
    if(fxi == 0): 
        print("Raiz en", xi)
    elif(fxs == 0): 
        print("Raiz en", xs)
    elif(fxi * fxs < 0): 
        xm = (xi + xs) / 2
        fxm = fx.evalf(subs={x:xm})
        contador = 1
        table.add_row([contador, xi, xs, xm, fxm, 'no existe'])
        error = tolerancia + 1
        while((error > tolerancia) and (fxm != 0) and (contador < niter)): 
            if(fxi * fxm < 0): 
                xs = xm
                fxs = fxm
            else: 
                xi = xm
                fxi = fxm
            xaux = xm
            xm = (xi + xs) / 2
            fxm = fx.evalf(subs={x:xm})
            error = abs(xm - xaux)
            contador += 1
            table.add_row([contador, xi, xs, xm, fxm, error])
        if(fxm == 0):
            print("Raiz en", xm)
        elif(error < tolerancia): 
            print(xs, "Es una aproximacion a una raiz con una tolerancia =", tolerancia)
        else: 
            print("Fracaso en", niter, "iteraciones")
    else: 
        print("El intervalo es inadecuado")
    print(table)

biseccion(1, 2, 0.5e-6, 100)
