package theFightClub_tamaGolem.unibs.it;

import java.io.*;
import java.util.*;
  public class Player {

    //metto gli attributi della classe
    private String nome; // nome del giocatore
    private int numeroGolem; //numero di golem che ha a disposizione
    private Golem golemScelto;
    private static Golem golem = new Golem();
    private static ScortaPietre pietra = new ScortaPietre();
    private int numeroPietre;


    // -> G è il numero di golem
    static int G = (int) Math.ceil(((golem.getVitaGolem()-1)*(golem.getVitaGolem()-2))/(2*golem.getP()));
    private static ArrayList<Golem> golemTotali = new ArrayList<>();
    //Array list per i golem

    public void Giocatore (){
        this.G = G;
        this.nome = nome;
    }



    //Devo prendere i golem dall'array list di tamagolem che ho a disposizione
    public boolean evocazioneGolem() {
        System.out.println("Benvenuto Giocatore, adesso puoi evocare il tuo GOLEM!" );
        int i=0;
        if (G>0) {
            golem = golemTotali.get(i);
            System.out.println("Adesso hai evocato il tuo primo golem"+ golem );
            G = G-1;
            golemTotali.remove(i);
            return true;
        }

        System.out.println("Non hai più golem a disposizione");
        return false;

    }

    //SCELTA DEL SET DI PIETRE CHE OGNI GIOCATORE HA A DISPOSIZIONE PER IL GIOCO
    //QUESTO SARA' IL SET CHE OGNI GIOCATORE DOVRA' USARE PER L'INTERA PARTIT
    //DOVRA' BASTARE AL GIOCATORE PER TUTTI I GOLEM IN GIOCO


    int S = ((2*golem.getP()* Player.G)/ golem.getVitaGolem())* golem.getVitaGolem();
    public static ArrayList<ScortaPietre> scortaPietre = new ArrayList<>();

    public boolean caricamentoPietre()
    {
        int j=0;

        if (S > 0)
        {
            do {
                pietra = scortaPietre.get(j);
                S--;
                numeroPietre--;
                scortaPietre.remove(j);
            } while (numeroPietre > 0 && S > 0);
            return true;
        }
        return false;
    }
}
