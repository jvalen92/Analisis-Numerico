# Lee un número n de la entrada y retorna una 2-tupla (a, b) donde
# a es la parte entera n y b es
def asignar_datos():
    print("Ingrese el valor de n: ")
    numero_posiciones_mantisa = int(input())
    print("ingrese el valor de k: ")
    numero_posiciones_exponente = int(input())
    return (numero_posiciones_mantisa, numero_posiciones_exponente)

#Lee de la entrada estandar un numero decimal 
def leer_numero():
    print("Ingrese el decimal a convertir: ")
    n = float(input())
    n = abs(n)
    return (int(n), n*1.0 - int(n) * 1.0)

#Devuelve el numero binario de a
def binario(a):
    numero = a
    resultado = ""
    while numero > 0:
        resultado = resultado+ str(numero & 1)
        numero >>= 1
    return resultado


# k - Numero de bits de la mantisa
# n - Numero de bits del exponente
def overflow(k, n): 
    print(f"El numero mas grande de una maquina con una mantisa de {k} bits y un exponente de {n} bits es: {(2**k)-1} * 2e{(2**n)-1}")
    print(f"Es decir {((2**k)-1)*(2**(n))-1}")

def __binario__():
    (mantisa_numero, exponente_numero) = asignar_datos()
    (a, b) = leer_numero()
    mantisa = binario(a)
    overflow(mantisa_numero, exponente_numero) 
    mantisa = mantisa[::-1]
    iter = 0
    if a > 0:
        exponente = binario(len(mantisa))
        exponente = exponente[::-1]
    else:
        exponente = "0"
    while b > (1*10**-16) and iter < mantisa_numero + 1 - len(mantisa):
        mantisa += str(int(2*b))
        b = 2 * b - int(2 * b)
        iter+=1
    while len(exponente) < exponente_numero:
        exponente += "0"
    while len(mantisa) < mantisa_numero:
        mantisa += "0"
    res = "0." + mantisa + "*2^" + exponente
    print("Numero de maquina estandarizado: ", res)
    print("Numero de maquina: ", (mantisa+exponente))
    return mantisa

if __name__ == "__main__":
    print("El numero de maquina se escribe como d1d2d3d4...dne1e2e3..ek")
    print("Nota: Se asume que tanto n como k son dados por el usuario.")
    print("Donde n es la cantidad de bits de la mantisa y k es la cantidad de bits del exponente ")
    print("Ayuda: Los numeros se escriben en la notación 0.d1d2d3...dn*2^(e1e2e3...ek), donde (d1,d2,..., dn) pertencen a {0,1}")
    print("y (e1,e2,..., ek) pertencen a {0,1}")
    print("Ayuda: Para calcular el decimal se multiplica de la siguiente manera")
    print("Ayuda: Decimal = d1*2^(k - 1) + d2*2^(k - 2) + d3*2^(k - 3) + ... + dn-1*2^(k - n - 1) + dn*2^(k - n)")
    print("donde k = e1*2^k + e2*2^(k - 1) + ... + ek*2^0")
    print("Ayuda: Siempre se cumple que k >= 0")
    print("Esta maquina trabaja con bit implicito")
    __binario__()