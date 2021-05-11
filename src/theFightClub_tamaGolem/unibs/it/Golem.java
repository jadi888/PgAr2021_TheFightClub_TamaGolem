package theFightClub_tamaGolem.unibs.it;
import java.util.*;

public class Golem {



   private int vitaGolem;
   private static ArrayList<Elemento> pietrePerGolem =  new ArrayList<Elemento>();
   private int indice=0; //indice della queue quando ciclo sulle pietre;
    private Object Elemento;


    public Golem(){
      this.vitaGolem = Equilibrio.vitaGolem;
      int pietre = Equilibrio.P; //pietre per golem

      System.out.printf("Il Golem evocato può contenere fino a %d pietre degli elementi!", pietre);

      for(int i=0; i < pietre; i++){
          System.out.printf("Scegli una delle pietre disponibili da caricare in posizione ", i+1);
          pietrePerGolem.add(Equilibrio.caricaPietra()); //ho aggiunto un elemento alla queue
      }
  }

   public int aggiornaVitaGolem(int danni){
      vitaGolem-= danni;
      return vitaGolem;
   }

   public Elemento lanciaPietra(){
       Elemento pietra = pietrePerGolem.get(indice);
       if(indice<Equilibrio.P-1) indice++;
       else indice=0;
       return (theFightClub_tamaGolem.unibs.it.Elemento) Elemento;
   }
   


}
