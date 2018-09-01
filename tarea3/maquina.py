from decimal import Decimal
"""Los argumentos son:
        numero decimal a convertir
        numero de bits reservados para la mantisa
        numero de bits reservados para el exponente
"""
def DectoBin(num,a,b, registro):
    #Si es un numero decimal
    if str(num).find('.') != -1:
        if(num <0):
            num=abs(num)
            sig="10"
        else:
            sig="11"
        aux=str(num).split(".")
        #parte entera del numero ingresado
        bi= str (bin(int(aux[0]))[2:])
        #exponente en binario
        exp=len(bi)
        exp=bin(exp)[2:]
        #parte decimal del numero ingresado
        dec='0.'+aux[1]
        dec=float(dec)
        #llenar los campos del exponente con ceros
        while len(exp)<b:
            exp='0'+exp
        #pasar la parte decimal a binario
        mh = dec
        new_bi = bi
        iter = 0
        while mh > (1*10**-16) and iter < len(new_bi) <= a:
            new_bi += str(int(2*mh))
            mh = 2 * mh - int(2 * mh)
            iter+=1
        while len(new_bi) <= a:
            new_bi += '0'
        """
        while len(bi)<=a:
            dec=dec*2  
            if dec > 1:
                bi=bi+'1'  
                #cambiar la parte entera por cero y dejar la decimal
                tmp=str(dec).split('.')
                tmp[0]='0'
                dec=float(tmp[0]+tmp[1])
            else:
                bi=bi+'0'
        """
        #formato de numero de maquina con bit implicito
        #maquina=sig+bi[1:]+exp
        maquina = sig+new_bi[1:]+exp
        print(maquina)
        registro.write(f"El numero {num} en binario: {maquina}\n")
        print("signos %s mantiza %s exp %s " % (sig,bi[1:],exp))
    #En caso de que sea un numero entero
    else:
        #Por defecto la mantisa tienen los signos positivos
        sig="11"
        #Parte entera del numero ingresado a binario
        bi=bin(num)
        bi=bi[2:]
        #exponente en binario
        exp=len(bi)
        exp=bin(exp)[2:]

        #llenar el resto de la mantisa con ceros
        while len(bi)<=a:
            bi=bi+'0'
        #llenar los campos del exponente con ceros
        while len(exp)<b:
            exp='0'+exp
        #formato del numero maquina con bit implicito
        maquina=sig+bi[1:]+exp
        print(maquina)
        registro.write(f"El numero {num} en binario: {maquina}\n")
        print("signos %s mantiza %s exp %s " % (sig,bi[1:],exp))
    
def BintoDec(mac,a,b, registro):
    
    #descomponer el formato del numero maquina
    sigE=mac[0]
    sigM=mac[1]
    man=mac[2:-b]
    exp=mac[-b:]
    #Valor del exponente en base 2
    exp=int(exp,2)
    #bit implicito
    num=2**-1
    #iterador para los exponentes de la mantisa
    cont=2
    for i in man:
        if i is '1':
            num=num+(2**-cont)
        cont=cont+1
    #multiplicar el resultado de la mantisa con el exponente
    num=num*(2**exp)
    #validar si es negativo
    if sigM is '0':
        num=num*-1
    registro.write(f"El numero {mac} en decimal: {num}\n")
    print(num)
def overflow(k, n, registro):
    """sum1 = 0
    for i in range(1, n+1): 
        sum1 += 1/(2**i)"""
    sum = (1/2)*(((1/2)**n-1)/(1/2-1))
    #print("igual: " + str(sum-sum1))
    #registro.write(f"Overflow: {'%.8E' % Decimal(sum * ((2**((2**n)-1))))}\n")
    #print(f"El numero mas grande de una maquina con una mantisa de {k} bits y un exponente de {n} bits es: {sum} * 2e{(2**n)-1}")
    #print(f"Es decir {'%.8E' % Decimal(sum * ((2**((2**n)-1))))}")
    exp=float (2 ** n) - 1
    man= float( 0.5*( (0.5**k -1) / (0.5-1) ))
    man=float(0.5)*(((0.5**(k+1))-1)/(0.5-1))
    #print(man * 2**exp)
    print(f"El numero mas grande de una maquina con una mantisa de {k} bits y un exponente de {n} bits es: {man * 2**(exp)}")

def epsilon(k,n, registro):
    #registro.write(f"Epsilon: {(2**((n * -1)+1))}\n")
    #2 a la mantisa -1
    registro.write(f"Epsilon: {2**(-k+1)}\n")
    print(f"El epsilon de la maquina con una mantisa de {k} y un exponente de {n} bit es:{2**(-k+1)}")

def underflow(k,n, registro):
    #registro.write(f"Underflow: {(2.0**(-(2.0**k)))}\n")
    #print(f"El underflow de la maquina con una mantisa de {k} y un exponente de {n} bit es:{(2.0**(-(2.0**k)))}")
    #el underflow es 2**-1 * 2**-(n-1)
    exp=2**n -1
    registro.write(f"Underflow : {2**-1 * 2**(-exp)}")
    print(f"El underflow de la maquina con una mantisa de {k} y un exponente de {n} bit es:{2**-1 * 2**(-exp)}")
def programa(registro):
    print("Primero lo primero, dame el numero de bits de la mantisa k y el numero de bits del exponente n. Asegurate de que n+k <= 30")
    print("Valor de k: ")
    k = int(input())
    registro.write(f"Mantisa: {k} \n")    
    print("Valor de n: ")
    n = int(input())
    registro.write(f"Exponente: {n}\n")
    registro.write(f"Consultas:\n")
    while True:
        print("¿Que quieres hacer?")
        print("1. Dame el numero mas grande de la maquina.")
        print("2. Dame el epsilon de la maquina")
        print("3. Quiero saber del underflow")
        print("4. Convertir numero decimal en binario")
        print("5. Convertir numero binario en decimal")
        print("0. Esto no me gusta, dejame salir")
        valor = int(input())
        if valor == 0:
            break
        if valor == 1:
            overflow(k, n, registro)
        if valor == 2:
            epsilon(k, n, registro)
        if valor == 3:
            underflow(k, n, registro)
        if valor == 4:
            print("Dame el valor decimal: ")
            dato = float(input())
            DectoBin(dato, k, n, registro)
        if valor == 5:
            print("Dame el valor binario: ")
            dato = input()
            BintoDec(dato, k, n, registro)
if __name__ == "__main__":
    registro = open("registro.txt", "w")
    programa(registro)
    registro.close()
     
