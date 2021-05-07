package theFightClub_tamaGolem.unibs.it;

import java.util.*;

public class Player {

    private static Golem golem = new Golem();

    int G = (int) Math.ceil(((golem.getVitaGolem()-1)*(golem.getVitaGolem()-2))/(2*golem.getP()));
    private int pietreRimaste;
    private static ArrayList<Golem> golems = new ArrayList<>(); // un giocatore ha a disposizione una quantità G di Golem;



}
