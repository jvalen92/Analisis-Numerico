# Implementacion del Metodo de Muller
# para la ubicacion de raices de una funcion
# La descripcion detallada de la estructura
# de este algoritmo se encuentra en el documento
#Informe tarea5.pdf en este directorio

import sys
from prettytable import PrettyTable
import sympy
import math

sympy.init_printing(use_unicode=True)

def muller(x0, x1, x2, tolerancia, niter):
    x = sympy.symbols('x')
    f = sympy.cos(x)
    table = PrettyTable(['i', 'Pn', 'f(Pn)', 'Error'])
    h1 = x1 - x0
    h2 = x2 - x1
    y1 = (f.evalf(subs={x:x1}) - f.evalf(subs={x:x0})) / h1
    y2 = (f.evalf(subs={x:x2}) - f.evalf(subs={x:x1})) / h2
    d = ((y2 - y1) / (h2 + h1))
    contador = 1
    error = tolerancia + 1
    p = sys.maxsize * -1
    table.add_row([contador, p, f.evalf(subs={x:p}), ''])
    while(contador < niter and error > tolerancia): 
        b = y2 + (h2 * d)
        D = math.sqrt(b**2 - 4*(f.evalf(subs={x:x2}) * d))
        E = sys.maxsize * -1
        if(abs(b - D) < abs(b + d)): 
            E = b + d 
        else: 
            E = b - D
        
        h = (-1 * 2) * (f.evalf(subs={x:x2})) / E
        p = x2 + h
        x0 = x1
        x1 = x2
        x2 = p
        h1 = x1 - x0
        h2 = x2 - x1
        y1 = (f.evalf(subs={x:x1}) - f.evalf(subs={x:x0})) / h1
        y2 = (f.evalf(subs={x:x2}) - f.evalf(subs={x:x1})) / h2
        d = ((y2 - y1) / (h2 + h1))
        error = abs(h)
        contador += 1
        table.add_row([contador, p, f.evalf(subs={x:p}), error])
    
    if(error <= tolerancia): 
        print("Hay una raiz en ", p)
    else: 
        print("Fracaso en ", niter, "Iteraciones")

    print(table)

muller(0, 1, 2, 0.5e-7, 100)
