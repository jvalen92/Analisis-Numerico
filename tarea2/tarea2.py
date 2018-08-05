import math
def taylorCos0(x):
    result = 0
    for n in range(0,40):
        result += (pow(-1,n)*pow(x,2*n))/ math.factorial(2*n)
    print(result)

def taylorSen0(x):
    result = 0
    for n in range(0,40):
        result += (pow(-1,n)*pow(x,2*n+1))/math.factorial(2*n+1)
    print(result)

def taylorExp0(x):
    result = 0
    for n in range(0,40):
        result += pow(x,n)/math.factorial(n)
    print(result)

def expDiferentesCentors(x):
    
    centro1 = 0
    centro2 = math.pi/2
    centro3 = 5
    
    exp1 = 1
    exp2 = math.exp(centro2)
    exp3 = math.exp(centro3)

    respuestacentro1 = 0
    respuestacentro2 = 0
    respuestacentro3 = 0
    
    for n in range(0,5):
        factorialn = math.factorial(n)
        respuestacentro1 += (exp1*pow(x-centro1,n))/factorialn
        respuestacentro2 += (exp2*pow(x-centro2,n))/factorialn
        respuestacentro3 += (exp3*pow(x-centro3,n))/factorialn
    print("---------")
    print("La solucion a "+ str(x) + " con un centro de "+ str(centro1) +" es " + str(respuestacentro1))
    print("La solucion a "+ str(x) + " con un centro de "+ str(centro2) +" es " + str(respuestacentro2))
    print("La solucion a "+ str(x) + " con un centro de "+ str(centro3) +" es " + str(respuestacentro3))
    print("La solucion real de la maquina es: " + str(math.exp(x)))

if __name__ == "__main__":
    """valor = math.pi/3
    taylorCos0(valor)
    print(math.cos(valor))
    taylorSen0(valor)
    print(math.sin(valor))
    taylorExp0(valor)
    print(math.exp(valor))"""
    expDiferentesCentors(0.01)
    expDiferentesCentors(math.pi/3)
    expDiferentesCentors(5.4)