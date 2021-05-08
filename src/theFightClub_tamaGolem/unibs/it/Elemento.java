package theFightClub_tamaGolem.unibs.it;

import java.util.HashMap;
import java.util.TreeMap;

public class Elemento {

    private int Indice;
    private HashMap<Integer, Integer> dominaSu = new HashMap<>();

    private static ScortaPietre scorta = new ScortaPietre();

    private int pietrePerElemento = (int) Math.ceil(scorta.getS()/Equilibrio.quantiElementi()); //pietre per ciascun elemento;


    //costruttore
    public Elemento(){
        this.Indice = Indice;
        this.dominaSu = dominaSu;
    }



    public void setIndice(int Indice){
        this.Indice = Indice;
    }
    public void setDominaSu(HashMap<Integer, Integer> dominaSu){
        this.dominaSu=dominaSu;
    }

    public void addElemento(int indice1, int potenza){
        this.dominaSu.put(indice1, potenza);
    }

    public int getIndice(){
        return Indice;
    }

    public HashMap<Integer, Integer> getDominaSu(){
        return dominaSu;
    }
}
