package theFightClub_tamaGolem.unibs.it;

import java.util.*;

public class Player {


    private static String nomeGiocatore;
    private Golem golemInGioco;

    int G = (int) Math.ceil(((golem.getVitaGolem()-1)*(golem.getVitaGolem()-2))/(2*golem.getP())); //numero di golem per giocatore
    private static ArrayList<Golem> golems = new ArrayList<>(); // un giocatore ha a disposizione una quantità G di Golem;

    public void PLayer(){
        this.G = G;
        this.nomeGiocatore = nomeGiocatore;
    }



    //quando il giocatore evoca un Golem, il golem automaticamente viene tolto dall'arraylist golems
    // e viene dimunuito il numero di golems a disposizione del giocatore
    public boolean evocaGolem(){
        int i=0;
        if (G>0){
            System.out.println("Il giocatore " + nomeGiocatore + " ha evocato un GOLEM!");
            golem = golems.get(i);
            G--;
            golems.remove(i);
            return true;
        }
        else return false;
    }

   public void setNomeGiocatore(String nome){
        this.nomeGiocatore = nome;
   }


}
