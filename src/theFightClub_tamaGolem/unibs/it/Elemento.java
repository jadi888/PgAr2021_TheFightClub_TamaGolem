package theFightClub_tamaGolem.unibs.it;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author jadimary
 */

public class Elemento {

    /**
     * @param Indice attributo dell'elemento;
     * @param dominaSu attributo dell'elemento: contiene la combinazione dell'elemento su cui è forte o debole in base alla value
     *                 se positiva, significa che è forte su quell'elemento,
     *                 se negativa, significa che è debole su quell'elemento;
     */
    private int Indice;
    private HashMap<Integer, Integer> dominaSu = new HashMap<>();



    public Elemento(){
        this.Indice = Indice;
        this.dominaSu = dominaSu;
    }

    public void setIndice(int Indice){
        this.Indice = Indice;
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
