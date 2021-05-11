package theFightClub_tamaGolem.unibs.it;
import java.util.*;

public class Golem {


   private int vitaGolem ;
   private static Queue<Elemento> pietrePerGolem = (Queue<Elemento>) new ArrayList<Elemento>(); //ogni tamagolem può ingurgitare fino a P pietre
   private int p; //è il numero di pietre per Golem;

   public Golem(int vitaGolem){
      this.vitaGolem = vitaGolem;
      this.p = (int) Math.ceil(((vitaGolem+1)/3)+1);
  }

   public void aggiornoVitaGolem(int danni){
      vitaGolem = vitaGolem - danni;

   }


   public void addPietra(Elemento pietra){
      pietrePerGolem.add(pietra);
   }

   public int getVitaGolem(){
      return vitaGolem;
   }

   public int getP() {
      return p;
   }


}
