package com.example.test;

public class NodoXOR {
    public static int id = 1;
    private int reference;
    private Object dato;
    private int instanceId;

    public NodoXOR(Object newDato){
        this.dato = newDato;
        this.instanceId = id;
        id++;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int prev, int next) {
        double bin1 = LogicXOR.convertBin(prev);
        double bin2 = LogicXOR.convertBin(next);
        this.reference = LogicXOR.XOR(bin1, bin2);
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }
}
