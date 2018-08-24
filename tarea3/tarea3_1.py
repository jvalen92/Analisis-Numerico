def asignar_datos():
    print("Ingrese el valor de n: ")
    numero_posiciones_mantisa = int(input())
    print("ingrese el valor de k: ")
    numero_posiciones_exponente = int(input())
    return (numero_posiciones_mantisa, numero_posiciones_exponente)
    
# k - Numero de bits de la mantisa
# n - Numero de bits del exponente
def overflow(k, n): 
    print(f"El numero mas grande de una maquina con una mantisa de {k} bits y un exponente de {n} bits es: {(2**k)-1} * 2e{(2**n)-1}")
    sum = 0
    for(i in range(1, n+1)): 
        sum += 1/(2**i)
print(f"Es decir {sum * (2**(n))-1}") 


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
    (k, n) = asignar_datos() 
    overflow(k, n)