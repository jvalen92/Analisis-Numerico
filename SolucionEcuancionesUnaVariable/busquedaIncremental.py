import sympy
sympy.init_printing(use_unicode=True)

def busquedaIncremental(x0, delta, niter):
    x = sympy.symbols('x')
    #fx = sympy.exp((3*x) - 12) + x * sympy.cos(3 * x) - (x ** 2) + 4 
    fx = x**3 + 4*x**2 - 10
    fx = sympy.exp((3*x) - 12) + x * sympy.cos(3 * x) - (x ** 2) + 4
    ##fx = sympy.exp(-1*x) - sympy.sen(4*x)
    fx = (3*sympy.sqrt(10)/4)*((x+4)**(-5/2))
    fx0 = fx.evalf(subs={x:x0})
    if(fx0 == 0): 
        print("Raiz en", x0)
    else:
        x1 = x0 + delta
        contador = 1
        fx1 = fx.evalf(subs={x:x1})
        while((fx0 * fx1 > 0) and (contador < niter)): 
            x0 = x1
            fx0 = fx1
            x1 = x0 + delta
            fx1 = fx.evalf(subs={x:x1})
            contador += 1
        
        if(fx1 == 0): 
            print("Raiz en", x0)
        elif fx0 * fx1 < 0:
            print("Hay una raiz entre (", x0, ',',  x1, ')')
        else: 
            print("Fracaso en ", niter, 'Iteraciones')

busquedaIncremental(-6, 1, 30)
