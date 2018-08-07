import math
# Punto 4(Primera expresion)
def cosPlusExp0(x):
    resultado1 = 1
    resultado2 = 1
    error1 = 1
    error2 = 1
    n = 1
    f = open("punto4_expr1.txt", "w")
    f.write("Funcion cos(x) + exp(x) para x = ")
    f.write(str(x))
    f.write('\n')
    while error1 > 0.5 * 10 ** -18 and error2 > 0.5 * 10 ** -18 and n <= 1000:
        error1 = (((-1) ** n) * x ** (2 * n)) / math.factorial(2 * n)
        error2 = (x ** n) / math.factorial(n)
        resultado1 += error1
        resultado2 += error2
        f.write("En la iteracion: %d, resultado: " % n)
        f.write(str(resultado1 + resultado2))
        f.write('\n')
        error1 = abs(error1)
        error2 = abs(error2)
        n = n + 1
    f.write("Resultado final: ")
    f.write(str(resultado1 + resultado2))
    f.close()


# Punto 4(Segunda expresion)
def cosPlusSen0(x):
    resultado1 = 1
    resultado2 = x
    error1 = 1
    error2 = 1
    n = 1
    f = open("punto4_expr2.txt", "w")
    f.write("Funcion cos(x) + sen(x) para x = ")
    f.write(str(x))
    f.write('\n')
    while error1 > 0.5 * 10 ** -18 and error2 > 0.5 * 10 ** -18 and n <= 1000:
        error1 = (((-1) ** n) * x ** (2 * n)) / math.factorial(2 * n)
        error2 = (((-1) ** n) * x ** (2 * n + 1)) / math.factorial(2 * n + 1)
        resultado1 += error1
        resultado2 += error2
        f.write("En la iteracion: %d, resultado: " % n)
        f.write(str(resultado1 + resultado2))
        f.write('\n')
        error1 = abs(error1)
        error2 = abs(error2)
        n = n + 1
    f.write("Resultado final: ")
    f.write(str(resultado1 + resultado2))
    f.close()

if __name__ == "__main__":
    # Punto 4
    cosPlusExp0(0.4)
    cosPlusSen0(0.4)
