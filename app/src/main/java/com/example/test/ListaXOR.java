package com.example.test;

public class ListaXOR {
    private NodoXOR head;
    private NodoSuplementario headSuplementaria;

    public ListaXOR(){
        this.head = null;
        this.headSuplementaria = null;
    }

    public boolean isVacia(){
        return head == null;
    }

    public void insertar(Object dato){
        if (isVacia()){
            head = new NodoXOR(dato);
            head.setReference(0,0);
            headSuplementaria = new NodoSuplementario(head);
        } else{
            //Crea nuevo nodo
            NodoXOR newNodo = new NodoXOR(dato);
            //Se inserta en lista suplementaria
            NodoSuplementario temphead = headSuplementaria;
            while(temphead.next != null){
                temphead = temphead.next;
            } temphead.next = new NodoSuplementario(newNodo);
            //Se inserta en la listaXOR
            int prev = 0;
            NodoXOR tmpNodo = head;
            int next = LogicXOR.convertDec(LogicXOR.XOR(prev,tmpNodo.getReference()));
            while(next != 0){
                prev = tmpNodo.getInstanceId();
                NodoSuplementario NodoSplm = headSuplementaria;
                while(NodoSplm != null){
                    NodoXOR actual = (NodoXOR) NodoSplm.getDato();
                    if(next == actual.getInstanceId()){
                        tmpNodo = actual;
                        break;
                    } NodoSplm = NodoSplm.next;
                }
                next = LogicXOR.convertDec(LogicXOR.XOR(LogicXOR.convertBin(prev),tmpNodo.getReference()));
            }
            tmpNodo.setReference(prev, newNodo.getInstanceId());
            newNodo.setReference(tmpNodo.getInstanceId(),0);
        }
    }

    public void eliminar(Object dato){
        if (isVacia()){
            System.out.println("Dato no se encuentra");
            return;
        }

        NodoXOR tmpNodo = head;
        int prev = 0;
        int next = LogicXOR.convertDec(LogicXOR.XOR(prev,tmpNodo.getReference()));
        if(next == 0){
            head = null;
            headSuplementaria = null;
        }
        if(tmpNodo.getDato().equals(dato)){
            NodoSuplementario tmpSplm = headSuplementaria;
            NodoXOR newHead = null;
            while(tmpSplm != null){

                newHead = (NodoXOR) tmpSplm.getDato();
                if(next == newHead.getInstanceId()) {
                    break;
                }
                tmpSplm = tmpSplm.next;
            };
            newHead.setReference(
                    0,
                    LogicXOR.convertDec(LogicXOR.XOR(LogicXOR.convertBin(head.getInstanceId()), newHead.getReference()))
            );
            head = newHead;
            headSuplementaria = tmpSplm;
            return;
        }
        while(next != 0){
            //Se cambia prev a id de nodo actual
            prev = tmpNodo.getInstanceId();
            //Se encuentra el siguiente NodoXOR por medio de la lista suplementaria
            NodoSuplementario tmpSplm = headSuplementaria;
            NodoSuplementario prevSplm = null;
            NodoXOR actual = null;
            while(tmpSplm != null){
                actual = (NodoXOR) tmpSplm.getDato();
                if(next == actual.getInstanceId()){
                    break;
                }
                prevSplm = tmpSplm;
                tmpSplm = tmpSplm.next;
            };
            //Cuando se obtiene el Nodo comienza el proceso de eliminacion
            //Si el nodo tiene el dato ingresado
            if(actual.getDato().equals(dato)){
                //Se cambia la referencia del nodo anterior
                tmpNodo.setReference(
                        LogicXOR.convertDec(LogicXOR.XOR(tmpNodo.getReference(),LogicXOR.convertBin(next))),
                        LogicXOR.convertDec(LogicXOR.XOR(LogicXOR.convertBin(prev),actual.getReference()))
                );
                //Se quita el nodo de la lista suplementaria
                prevSplm.next = tmpSplm.next;
                //Se consigue nodo siguiente por medio de lista suplementaria
                tmpSplm = headSuplementaria;
                next = LogicXOR.convertDec(LogicXOR.XOR(LogicXOR.convertBin(prev),actual.getReference()));
                if(next != 0){
                    NodoXOR actualNext = null;
                    while(tmpSplm != null){
                        actualNext = (NodoXOR) tmpSplm.getDato();
                        if(next == actualNext.getInstanceId()) {
                            break;
                        }
                        tmpSplm = tmpSplm.next;
                    };
                    //Se cambia la referencia de nodo siguiente
                    actualNext.setReference(tmpNodo.getInstanceId(),
                            LogicXOR.convertDec(LogicXOR.XOR(LogicXOR.convertBin(actual.getInstanceId()),actualNext.getReference())));
                }
                System.out.println("Se logro eliminar");
                break;
            }
            //Si no se encuenta, se cambia para el siguiente nodo
            tmpNodo = actual;
            next = LogicXOR.convertDec(LogicXOR.XOR(LogicXOR.convertBin(prev),actual.getReference()));
        };
    }

    public String print(){
        StringBuilder listXOR = new StringBuilder("[");
        if(isVacia()){
            listXOR.append("]");
        } else{
            NodoXOR tmpNodo = head;
            int prev = 0;
            int next = LogicXOR.convertDec(LogicXOR.XOR(prev,tmpNodo.getReference()));
            listXOR.append(tmpNodo.getDato());
            while(next != 0){
                //System.out.println("Id: " + tmpNodo.getInstanceId() + " Dato: "+ tmpNodo.getDato());
                listXOR.append(",");
                prev = tmpNodo.getInstanceId();
                NodoSuplementario tmpSplm = headSuplementaria;
                NodoXOR actual = null;
                while(tmpSplm != null){
                    actual = (NodoXOR) tmpSplm.getDato();
                    if(next == actual.getInstanceId()){
                        break;
                    } tmpSplm = tmpSplm.next;
                }
                listXOR.append(actual.getDato());
                tmpNodo = actual;
                next = LogicXOR.convertDec(LogicXOR.XOR(LogicXOR.convertBin(prev),actual.getReference()));
            } listXOR.append("]");
        }
        return listXOR.toString();
    }

    private class NodoSuplementario{
        private NodoSuplementario next;
        private Object dato;

        public NodoSuplementario(Object newDato){
            this.dato = newDato;
            this.next = null;
        }

        public Object getDato(){
            return dato;
        }
    }
}
