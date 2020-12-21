package com.example.test;

public class LogicXOR {

    public static int convertDec(int numero){
        int exponente = 0;
        int decimal = 0; //será el equivalente en base decimal
        while (numero > 0) {
            //se toma la última cifra
            int digito = numero % 10;
            //se multiplica por la potencia de 2 correspondiente y se suma al número
            decimal += digito * (int) Math.pow(2, exponente);
            //se aumenta el exponente
            exponente++;
            //se quita la última cifra para repetir el proceso
            numero /= 10;
        } return decimal;
    }

    public static double convertBin(int numero){
        int exp=0;
        double binario=0;
        while(numero > 0){
            int digito = numero % 2;
            binario += digito * Math.pow(10, exp);
            exp++;
            numero = numero/2;
        }
        return binario;
    }

    public static int XOR(double bin1, double bin2){
        double longest;
        double shortest;
        if (bin1 > bin2){
            longest = bin1;
            shortest = bin2;
        } else{
            longest = bin2;
            shortest = bin1;
        }
        int result = 0;
        int exp = 0;
        while (longest > 0){
            int dato1 = (int) longest%10;
            int dato2 = (int) shortest%10;
            if((dato1 == 0 && dato2 == 1) || (dato1 == 1 && dato2 == 0)){
                result +=  1 * Math.pow(10,exp);
            }
            longest /= 10;
            shortest /= 10;
            exp++;
        } return result;
    }
}
