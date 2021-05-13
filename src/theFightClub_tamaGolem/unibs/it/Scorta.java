package theFightClub_tamaGolem.unibs.it;

import java.util.ArrayList;

/**
 * @author jadimary
 */

public class Scorta {

    /**
     * @param pietrePerElemento la dimensione del vettore corrisponde al numero di elementi della partita e e il valore
     *                          all'indice i corrisponde al numero di pietre nella scorta per quel determinato elemento;
     */

    public static int[] pietrePerElemento;

    public static void inizializzoScorte() {
        int totElementi = Equilibrio.nr_elementi;
        pietrePerElemento = new int[totElementi];
        for (int i = 0; i < totElementi; i++){
            pietrePerElemento[i] = Equilibrio.PxElemento; //pietre x elemento;
        }
    }



}
