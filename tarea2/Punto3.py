import math
def taylorCos0(x):
    f = open("Punto3_cos.txt","w")
    f.write("Funcion cos(x) para x = ")
    f.write(str(x))
    f.write('\n')
    resultado = 0
    error = 1
    n  = 0
    while error > 0.5 * 10 ** -16 and n <= 1000:
        error = ((-1)**(n)* x**(2*n) )/ math.factorial(2*n)
        resultado += error
        f.write("En la iteracion: %d, resultado:" %n)
        f.write(str(resultado))
        error = abs(error)
        f.write(" Error %s" %error)
        f.write('\n')
        n += 1
    f.write("Resultado final: ")
    f.write(str(resultado))
    f.close()


def taylorSen0(x):
    f = open("Punto3_sen.txt","w")
    f.write("Funcion sen(x) para x = ")
    f.write(str(x))
    f.write('\n')
    resultado = 0
    error = 1
    n  = 0
    while error > 0.5 * 10 ** -16 and n <= 1000:
        error = (((-1)**n)*x**(2*n+1))/math.factorial(2*n+1)
        resultado += error
        f.write("En la iteracion: %d, resultado: " % n)
        f.write(str(resultado))
        error = abs(error)
        f.write("Error %s"%error)
        f.write('\n')
        n += 1
    f.write("Resultado final: ")
    f.write(str(resultado))
    f.close()

def taylorExp0(x):
    f = open("Punto3_exp.txt","w")
    f.write("Funcion exp(x) para x = ")
    f.write(str(x))
    f.write('\n')
    resultado = 0
    error = 1
    n  = 0
    while error > 0.5 * 10 ** -16 and n <= 1000:
        error = x**n/math.factorial(n)
        resultado += error
        f.write("En la iteracion: %d, resultado: " % n)
        f.write(str(resultado))
        error = abs(error)
        f.write("Error %s"%error)
        f.write('\n')
        n += 1
    f.write("Resultado final: ")
    f.write(str(resultado))
    f.close()

if __name__ == "__main__":
    taylorCos0(math.pi/3)
    taylorSen0(math.pi/3)
    taylorExp0(math.pi/3)