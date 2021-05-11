package theFightClub_tamaGolem.unibs.it;

import java.io.*;
import java.util.*;
  public class Player {

    //metto gli attributi della classe
    private String nome; // nome del giocatore
    private Golem golemScelto;
    private int g;


      public Player (String _nome, Golem _golemScelto){
          this.golemScelto = _golemScelto;
          this.nome = _nome;
          g = (int) Math.ceil(((golemScelto.getVitaGolem()-1)*(golemScelto.getVitaGolem()-2))/(2* golemScelto.getP()));

      } //istanziare golem e nome



    //SCELTA DEL SET DI PIETRE CHE OGNI GIOCATORE HA A DISPOSIZIONE PER IL GIOCO
    //QUESTO SARA' IL SET CHE OGNI GIOCATORE DOVRA' USARE PER L'INTERA PARTIT
    public int getG(){

          return this.g;
    }




    public void caricamentoPietre(Elemento pietra) {
          golemScelto.addPietra(pietra);

    }


}
