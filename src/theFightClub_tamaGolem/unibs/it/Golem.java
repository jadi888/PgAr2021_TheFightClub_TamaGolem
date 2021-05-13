package theFightClub_tamaGolem.unibs.it;
import java.util.*;

/**
 * @author Souhaila Mouak
 */

public class Golem {

    /**
     * Attributi del GOLEM:
     * @param pietrePerGolem contiene le pietre che il golem potrà usare nella battaglia;
     *
     */
   private int vitaGolem;
   private ArrayList<Elemento> pietrePerGolem =  new ArrayList<Elemento>();
   private int indice=0; //indice della queue quando ciclo sulle pietre;


    public Golem(){
      this.vitaGolem = Equilibrio.vitaGolem;
      int pietre = Equilibrio.P; //pietre per golem

      spazio();
      System.out.printf("Il Golem evocato può contenere fino a %d pietre degli elementi!\n", pietre);
      spazio();

      for(int i=0; i < pietre; i++){
          System.out.printf("Scegli una delle pietre disponibili da caricare in posizione %d \n", i+1);
          pietrePerGolem.add(Equilibrio.caricaPietra());
          //ho aggiunto un elemento al array
          spazio();
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
       return pietra;
   }

   public void spazio(){
       System.out.println();
   }


}
