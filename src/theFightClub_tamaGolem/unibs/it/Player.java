package theFightClub_tamaGolem.unibs.it;

import java.io.*;
import java.util.*;
  public class Player {


      // Attributi del Giocatore:
      // @param nomePlayer: nome del giocatore;
      // @param golemCorrente: il golem che attualmente sta lottando;
      // @param G: numero di golem che ha a disposizione;

    private String nomePlayer; // nome del giocatore
    private Golem golemCorrente;
    private int G;




    public Player(String nomePlayer) throws InterruptedException {
        this.G = Equilibrio.G;
        this.nomePlayer=nomePlayer;
        evocaGolem();
    }

    public String getNomePlayer(){
        return nomePlayer;
    }

    private boolean evocaGolem() throws InterruptedException {
        if(G>0){
            System.out.println("Il tuo golem è in arrivo!");
            Thread.sleep(1000);
            System.out.print(".  ");
            Thread.sleep(1000);
            System.out.print("  .  ");
            Thread.sleep(1000);
            System.out.println("  .  ");
            Thread.sleep(500);
            System.out.printf("%s il tuo GOLEM è stato evocato\n", nomePlayer);
            golemCorrente = new Golem();
            G--;
            return true;
        }
        return false; //se i golem sono finiti;
    }


    public Elemento getPietra(){
        return golemCorrente.lanciaPietra(); //ritorna la pietra che sta all'indice i nell'array di pietre della pancia del golem
    }

    public boolean danneggia(int danno) throws InterruptedException {
        int vitaRestante = golemCorrente.aggiornaVitaGolem(danno);
        if(vitaRestante<=0){
            System.out.printf("Il golem di %s è morto:(\n", nomePlayer);
            System.out.printf("%s hai ancora a disposizione %d Golem\n", nomePlayer, G);

            return evocaGolem();
        }
        else
            System.out.printf("%s, il tuo Golem ha subito %d danni\n", nomePlayer, danno);
        return true;
    }


}
