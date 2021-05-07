package theFightClub_tamaGolem.unibs.it;

import java.util.HashMap;
import java.util.TreeMap;

public class Elemento {

    private int Indice;
    private static HashMap<Integer, Integer> dominaSu = new HashMap<>();



    //costruttore
    public Elemento(){
        this.Indice = Indice;
        this.dominaSu = dominaSu;
    }

    public void setIndice(int Indice){
        this.Indice = Indice;
    }
    public void setDominaSu(HashMap dominaSu){
        this.dominaSu=dominaSu;
    }

    public int getIndice(){
        return Indice;
    }

    public HashMap getDominaSu(){
        return dominaSu;
    }
}
