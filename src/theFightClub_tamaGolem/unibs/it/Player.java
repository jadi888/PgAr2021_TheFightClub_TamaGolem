package theFightClub_tamaGolem.unibs.it;

import com.sun.source.tree.BreakTree;

import java.util.*;

import static theFightClub_tamaGolem.unibs.it.ScortaPietre.golem;

public class Player {


    private static String nomeGiocatore;
    private Golem golemInGioco;
    private int G; //golem per giocatore;


    public void PLayer() {
        this.G = (int) Math.ceil(((golem.getVitaGolem() - 1) * (golem.getVitaGolem() - 2)) / (2 * golem.getP()));
        this.nomeGiocatore = nomeGiocatore;
        evocaGolem();
    }


    //quando il giocatore evoca un Golem, il golem automaticamente viene tolto dall'arraylist golems
    // e viene dimunuito il numero di golems a disposizione del giocatore
    public boolean evocaGolem() {
        if (G > 0) {
            System.out.println("Giocatore %s evoca un nuovo Golem\n" + nomeGiocatore);
            golemInGioco = new Golem();
            G--;
            return true;
        }
        return false; //non ha più Golem a disposizione;
    }

    public int getPietra() {
        return golemInGioco. ();
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public boolean attacca(int danno) {
        int vitaRestante = golemInGioco.attacca(danno);

        if (vitaRestante <= 0) {
            System.out.printf("Il Golem del giocatore %s è morto \n", nomeGiocatore);
            System.out.printf("Gli rimangono %d tama \n", G);
            return evocaGolem();
        } else System.out.printf("Il golem del giocatore %s ha subito %d danni \n", nomeGiocatore, danno);
        return true;
    }

}
