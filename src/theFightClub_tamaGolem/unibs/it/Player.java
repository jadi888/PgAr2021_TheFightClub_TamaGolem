package theFightClub_tamaGolem.unibs.it;

import java.io.*;
import java.util.*;
  public class Player {


    private String nomePlayer; // nome del giocatore
    private Golem golemCorrente;
    private int G;


    public Player(String nomePlayer){
        this.G = Equilibrio.G;
        this.nomePlayer=nomePlayer;
        evocaGolem();
    }

    public String getNomePlayer(){
        return nomePlayer;
    }

    private boolean evocaGolem(){
        if(G>0){
            System.out.printf("Il Giocatore %s evoca un Golem\n", nomePlayer);
            golemCorrente = new Golem();
            G--;
            return true;
        }
        return false; //se i golem sono finiti;
    }

    public Elemento getPietra(){
        return golemCorrente.lanciaPietra();
    }

    public boolean danneggia(int danno){
        int vitaRestante = golemCorrente.aggiornaVitaGolem(danno);
        if(vitaRestante<=0){
            System.out.printf("Il golem del giocatore %s è morto:(\n", nomePlayer);
            System.out.printf("Il giocatore %s ha ancora a disposizione %d Golem\n", nomePlayer, G);
            return evocaGolem();
        }
        else
            System.out.printf("Giocatore %s, il tuo Golem ha subito %d danni", nomePlayer, danno);
        return true;
    }


}
