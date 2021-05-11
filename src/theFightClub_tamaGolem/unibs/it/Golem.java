package theFightClub_tamaGolem.unibs.it;
import java.util.*;

public class Golem {


   private static int vitaGolem = Equilibrio.getNr_elementi();
   private static List<Elemento> pietrePerGolem = new ArrayList<Elemento>(); //ogni tamagolem può ingurgitare fino a P pietre
   int P = (int) Math.ceil(((vitaGolem+1)/3)+1); //è il numero di pietre per Golem;

   public Golem(){
       for(int i=0; i < P; i++){
          Elemento elemento = new Elemento();
          pietrePerGolem.add(elemento);
       }
       //this.vitaGolem = Equilibrio.quantiElementi(); //va in base al livello di difficoltà della partita scelto dall'utente;
   }



   public int aggiornoVitaGolem(int danni){
      int newVitaGolem = getVitaGolem() - danni;
      return newVitaGolem;
   }


   public int getVitaGolem(){
      return vitaGolem;
   }

   public int getP(){
      return P;
   }


   public int attacca(int danno) {
   }
}
