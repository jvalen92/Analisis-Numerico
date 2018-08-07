import math
def expDiferentesCentors(x):
    #definicion de centros
    centro1 = 0
    centro2 = math.pi/2
    centro3 = 5
    
    #asigancion de  e^c
    exp1 = 1
    exp2 = math.exp(centro2)
    exp3 = math.exp(centro3)
    
    #Declaracion de las repuestas con diferentes centros
    respuestacentro1 = 0
    respuestacentro2 = 0
    respuestacentro3 = 0

    #Declaracion errores
    errorcentro1 = 1
    errorcentro2 = 1
    errorcentro3 = 1

    #Declaracion contadores n
    ncentro1 = 5
    ncentro2 = 5
    ncentro3 = 5

    #Escritura inicial de archivos
    pconclusion.write("Aproximacion funcion exp(x) para x = " + str(x) + " y centros: \n 1) ")
    pconclusion.write(str(centro1) + " \n 2) " + str(centro2) + "\n 3) " + str(centro3) + "\n")
    pcentro1.write("Funcion e^x para x = " + str(x) + " y centro= " + str(centro1) + "\n")
    pcentro2.write("Funcion e^x para x = " + str(x) + " y centro= " + str(centro2) + "\n")
    pcentro3.write("Funcion e^x para x = " + str(x) + " y centro= " + str(centro3) + "\n")

    #Calcular primera parte de la conclusion
    for n in range(0,5):
        factorialn = math.factorial(n)
        respuestacentro1 += (exp1*(x-centro1)**n)/factorialn
        pcentro1.write("En la iteracion: " + str(n) + ", resultado: " + str(respuestacentro1) + "\n")
        respuestacentro2 += (exp2*(x-centro2)**n)/factorialn
        pcentro2.write("En la iteracion: " + str(n) + ", resultado: " + str(respuestacentro2) + "\n")
        respuestacentro3 += (exp3*(x-centro3)**n)/factorialn
        pcentro3.write("En la iteracion: " + str(n) + ", resultado: " + str(respuestacentro3) + "\n")

    pconclusion.write("Resultado 5 iteraciones centro 1:  " + str(respuestacentro1))
    pconclusion.write("\n")
    pconclusion.write("Resultado 5 iteraciones centro 2: " + str(respuestacentro2))
    pconclusion.write("\n")
    pconclusion.write("Resultado 5 iteraciones centro 3: " + str(respuestacentro3))
    pconclusion.write("\n \n")

    #Llegar al numero con una toleracia menos a 0.5*10**-18 centro 1
    while errorcentro1 > 0.5 * 10 ** -18 and ncentro1 <= 1000:
        errorcentro1 = (exp1*(x-centro1)**ncentro1)/math.factorial(ncentro1)
        respuestacentro1 += errorcentro1
        pcentro1.write("En la iteracion: " + str(ncentro1) + ", resultado: " + str(respuestacentro1) + "\n")
        errorcentro1 = abs(errorcentro1)
        ncentro1 += 1

    #Llegar al numero con una toleracia menos a 0.5*10**-18 centro 2 
    while errorcentro2 > 0.5 * 10 ** -18 and ncentro2 <= 1000:
        errorcentro2 = (exp2*(x-centro2)**ncentro2)/math.factorial(ncentro2)
        respuestacentro2 += errorcentro2
        pcentro2.write("En la iteracion: " + str(ncentro2) + ", resultado: " + str(respuestacentro2) + "\n")
        errorcentro2 = abs(errorcentro2)
        ncentro2 += 1
    
    #Llegar al numero con una toleracia menos a 0.5*10**-18 centro 3
    while errorcentro3 > 0.5 * 10 ** -18 and ncentro3 <= 1000:
        errorcentro3 = (exp3*(x-centro3)**ncentro3)/math.factorial(ncentro3)
        respuestacentro3 += errorcentro3
        pcentro3.write("En la iteracion: " + str(ncentro3) + ", resultado: " + str(respuestacentro3) + "\n")
        errorcentro3 = abs(errorcentro3)
        ncentro3 += 1
    
    #conclusion 2 parte
    pconclusion.write("Resultado con toleracia 10**-18 y centro 1 al valor x= " + str(x))
    pconclusion.write(" en " + str(ncentro1) + " iteraciones ")
    pconclusion.write("Resultado: " + str(respuestacentro1) + "\n" )
    pconclusion.write("Resultado con toleracia 10**-18 y centro 2 al valor x= " + str(x))
    pconclusion.write(" en " + str(ncentro2) + " iteraciones ")
    pconclusion.write("Resultado: " + str(respuestacentro2) + "\n" )
    pconclusion.write("Resultado con toleracia 10**-18 y centro 3 al valor x= " + str(x))
    pconclusion.write(" en " + str(ncentro3) + " iteraciones ")
    pconclusion.write("Resultado: " + str(respuestacentro3) + "\n \n" )

if __name__ == "__main__":
    pconclusion = open("punto2_conclusion.txt","w")
    pcentro1 = open("punto2_centro1.txt","w")
    pcentro2 = open("punto2_centro2.txt","w")
    pcentro3 = open("punto2_centro3.txt","w")
    expDiferentesCentors(0.01)
    expDiferentesCentors(math.pi/3)
    expDiferentesCentors(5.4)