package theFightClub_tamaGolem.unibs.it;

import java.util.ArrayList;

public class Scorta {

    private ArrayList<Elemento> scortaTotale = new ArrayList<>();
    int s;

    public Scorta(Golem golem, Player giocatore){
        s = ((2*golem.getP()*giocatore.getG())/golem.getVitaGolem())*golem.getVitaGolem();
    }

    public void addPietra(Elemento pietra)  {

        scortaTotale.add(pietra);
    }


}
