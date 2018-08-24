"""Los argumentos son:
        numero decimal a convertir
        numero de bits reservados para la mantisa
        numero de bits reservados para el exponente
"""
def DectoBin(num,a,b):
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
        #formato de numero de maquina con bit implicito
        maquina=sig+bi[1:]+exp
        print(maquina)
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
        print("signos %s mantiza %s exp %s " % (sig,bi[1:],exp))
    
def BintoDec(mac,a,b):
    
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
    print(num)

#BintoDec("100101001100110",a,b)
#DectoBin(-42.375)

