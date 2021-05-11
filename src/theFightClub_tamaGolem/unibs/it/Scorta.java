package theFightClub_tamaGolem.unibs.it;

import java.util.ArrayList;

public class Scorta {

    public static int[] pietrePerElemento; //gli indici corrispondono agli elementi, e per ogni elemento restano tot pietre

    public static void inizializzoScorte() {
        int totElementi = Equilibrio.nr_elementi;
        pietrePerElemento = new int[totElementi];
        for (int i = 0; i < totElementi; i++){
            pietrePerElemento[i] = Equilibrio.PxElemento; //pietre x elemento;
        }
    }




}
