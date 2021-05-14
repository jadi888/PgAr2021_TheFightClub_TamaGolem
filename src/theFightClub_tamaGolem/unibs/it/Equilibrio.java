package theFightClub_tamaGolem.unibs.it;

import javax.swing.*;
import java.util.*;

/**
 * @author Souhaila Mouak
 */

public class Equilibrio {

    /**
     * @param elementi in base alla difficoltà della partita conterrà solo alcune pietre,
     * ad esempio le pietre Monkey King Bar e Divine Rapier non compariranno mai in una partita EASY o MEDIUM;
     */
    public static ArrayList<Elemento> elementi = new ArrayList<>();
    public static int[][] matEquilibrio;

    private static Random rand = new Random();

    /**
     * @param vitaGolem per rendere il gioco più interessante e duraturo sarà uguale al numero di elementi * 10;
     * @param P numero di pietre che un golem può ingurgitare;
     * @param G numero di Golem che i giocatori hanno a disposizione all'inizio della partita
     * @param S numero di pietre totali disponibili nella scorta per entrambi i giocatori;
     * @param PxElemento numero di pietre per elemento;
     */
    public static int nr_elementi;
    public static int vitaGolem; //sarà uguale al numeri di elementi * 10, per rendere la partita più interessante ;)
    public static int G;//golem per giocatore
    public static int P; //pietre per golem
    public static int S; //pietre disponibili;
    public static int PxElemento; //pietre per elemento


    public static void quantiElementi() {
        Scanner scan = new Scanner(System.in);
        String scelta;
        do {
            while (true) {
                System.out.println("Scegli il livello della partita: \n" + "1 - EASY\n" + "2 - INTERMEDIO\n" + "3 - HARD\n");
                scelta = scan.next();
                int intInputValue = 0;
                try {
                    intInputValue = Integer.parseInt(scelta);
                    break;
                } catch (NumberFormatException ne) {
                    System.out.println("SCEGLI UNO DEI LIVELLI DISPONIBILI");
                }
            }

            switch (Integer.parseInt(scelta)) {
                case 1:
                    nr_elementi = Math.abs(estraggoIntero(3, 5));
                    break;
                case 2:
                    nr_elementi = Math.abs(estraggoIntero(6, 8));
                    break;
                case 3:
                    nr_elementi = Math.abs(estraggoIntero(9, 10));
                default: {
                    System.out.println("Scegli uno dei livelli disponibili!");
                    continue;
                }
            }
        } while(Integer.parseInt(scelta)!=1 && Integer.parseInt(scelta)!=2 && Integer.parseInt(scelta)!=3);


        vitaGolem = nr_elementi*10;
        P = (int) Math.ceil(((nr_elementi+1)/3.0)+1);
        G = (int) Math.ceil((nr_elementi-1)*(nr_elementi-2))/(2*P);
        S = (int) Math.ceil(((2*P*G)/(double)(nr_elementi))*nr_elementi);
        PxElemento = (int) Math.ceil((2*P*G)/(double)(nr_elementi));

    }



    public static void generaEquilibrio() {

         // potenza_max coincide con la vita del golem ed è il massimo danno che un elemento può fare un su altro elemento;

        int potenza_max = nr_elementi*10; //è anche la vita del golem
        matEquilibrio = new int[nr_elementi][nr_elementi];
        int somma = 0;
        int i, j, k;

        //uso restart per rigenerare la matrice ogni volta giusta
        boolean restart = false;
        do {
            restart = false;
            for (i = 0; i < nr_elementi; i++) {
                do {
                    for (j = i; j < nr_elementi; j++) {
                        if (i == j) {
                            /*Sulla diagonale dovro' avere solo zeri, perchè un elemento non fa danni su se stesso*/
                            matEquilibrio[i][j] = 0;
                            if (i == nr_elementi - 1)
                                somma = 1;
                        } else {
                                /*se sono arrivata all'ultimo elemento sulla riga, non gli assegno un random
                                ma gli do l'opposto della somma dei termini fino a nr_elementi-1;
                                 */
                            if (j == nr_elementi - 1) {
                                    /*
                                    con questo ciclo calcolo la somma sulla riga escluso l'ultimo elemento
                                     */
                                for (k = 0; k < nr_elementi; k++) {
                                    somma = somma + matEquilibrio[i][k];
                                }
                                    /*
                                    alla posizione nella triangolare inferiore assegno l'opposto della somma;
                                     */
                                matEquilibrio[i][j] = -somma;
                                matEquilibrio[j][i] = somma;
                                if (i == nr_elementi - 1) {
                                    if ((somma == 0 || Math.abs(somma) > potenza_max))
                                        restart = true;
                                }
                            } else {
                                //per evitare di avere zeri oltre che sulla diagonale
                                do {
                                    matEquilibrio[i][j] = estraggoIntero(-potenza_max, potenza_max);
                                    matEquilibrio[j][i] = -matEquilibrio[i][j];
                                }
                                while (matEquilibrio[i][j] == 0);
                            }
                        }
                    }
                    if (restart) break;
                }
                // non devo mai superare la vita del tamagolem
                while ((Math.abs(somma) > potenza_max || somma == 0));
                somma = 0;
            }
        }
        while (restart);

    }


    public static int estraggoIntero(int minimo, int massimo) {
        int range = massimo + 1 - minimo;
        int intCasuale = rand.nextInt(range);
        return intCasuale + minimo;
    }

    //solo per la verifica della funzionalità della matrice di equilibrio
    public void stampaValori(int n, int[][] mat) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + "     ");
            }
            System.out.print("\n");
        }
    }



    public static void collegamenti() {
        int i, j, danno;
        for (i = 0; i < nr_elementi; i++) {
            Elemento elemento =  new Elemento();
            elemento.setIndice(i);
            for (j = 0; j < nr_elementi; j++) {
                    danno = matEquilibrio[i][j];
                    elemento.addElemento(j, danno);
            }
            elementi.add(elemento);
        }
    }

    public int getDanno(int i, int j){
        return matEquilibrio[i][j]; //ritorna il danno causato o subito di un elemento;
    }



    public void stampaTabella() {
        for (int i = 0; i < elementi.size(); i++) {
            System.out.println();
            System.out.print("L'elemento " +  elementi.get(i).getIndice() + "   ");
            elementi.get(i).getDominaSu().forEach((key, value) -> System.out.println("domina sull'elemento " + key + " con potenza:" + value));
        }
    }


/**
 * Il giocatore sceglie l'elemento da caricare nel golem dall'elenco di elementi gia' disponibili <pietrePerGolem></>
 * Man mano che carica un elemento, dovrò diminuire la quantità disponibili nella scorta <pietrePerElemento></>
 * di quel determinato elemento;
 */
    public static Elemento caricaPietra(){
        Scanner scan = new Scanner(System.in);
        Elemento pietra =  new Elemento();
        collegamenti();
        String scelta;


        TreeMap<Integer, String> nomiElementi = new TreeMap<Integer, String>();
        nomiElementi.put(1, "Scythe of Vyse");
        nomiElementi.put(2, "Blade Mail");
        nomiElementi.put(3, "Soul Booster");
        nomiElementi.put(4, "Black King Bar");
        nomiElementi.put(5, "Linke's Sphere");
        nomiElementi.put(6, "Assault Cuirass");
        nomiElementi.put(7, "Skull Basher");
        nomiElementi.put(8, "Ethereal Blade");
        nomiElementi.put(9, "Monkey King Bar");
        nomiElementi.put(10, "Divine Rapier");

        System.out.println();
        //gli mostro l'elenco delle pietre disponibili in base al nr di elementi nella partita
        System.out.printf("Premi un numero da 1 a %d per aggiungere uno degli elementi: \n", nr_elementi);
        for(int i=1; i <= nr_elementi; i++){
            System.out.printf("%d per aggiungere %s\n", i, nomiElementi.get(i));
        }


        //ovviamente il numero che sceglie deve essere compreso tra 1 e il numero totale di elementi;
        do{
            while (true) {
                scelta = scan.next();
                int intInputValue = 0;
                try {
                    intInputValue = Integer.parseInt(scelta);
                    break;
                } catch (NumberFormatException ne) {
                    System.out.println("Deve inserire una cifra corrispondente a un elemento!");
                }
            }
            int j=0;
            if(Integer.parseInt(scelta) >=1 &&Integer.parseInt(scelta)<=nr_elementi){
                if(Scorta.pietrePerElemento[Integer.parseInt(scelta)-1]!=0){
                    pietra = elementi.get(Integer.parseInt(scelta) - 1);
                    return pietra;
                }
                else System.out.printf("\nLe pietre dell'elemento %s nella scorta sono finite, scegli un'altro\n", nomiElementi.get(scelta));
            }
            else {
                System.out.println("***  Seleziona una tra le pietre disponibili!  ***");
            }
            //controllo se nella scorta per quel determinato elemento ci sono ancora pietre
        }while( Integer.parseInt(scelta)<=1 || Integer.parseInt(scelta)>=nr_elementi);

        Scorta.pietrePerElemento[Integer.parseInt(scelta)-1]--; //diminuisco il numero di pietre disponibili per un determinato elemento;

        System.out.printf("Hai scelto la pietra %s \n", nomiElementi.get(scelta));
        System.out.printf("Nella scorta restano ancora %d pietre dell'elemeneto %s\n", Scorta.pietrePerElemento[Integer.parseInt(scelta)-1], nomiElementi.get(scelta));


        // è la pietra che verrà aggiunta alla arraylist <pietreperGolem></pietreperGolem>

        return pietra;
    }


    public static String getNomeElemento(int indiceElemento){
        if(indiceElemento==1) return "Scythe of Vyse";
        else if(indiceElemento==2) return "Blade Mail";
        else if(indiceElemento==3) return "Soul Booster";
        else if(indiceElemento==4) return "Black King Bar";
        else if(indiceElemento==5) return "Linke's Sphere";
        else if(indiceElemento==6) return "Assault Cuirass";
        else if(indiceElemento==7) return "Skull Basher";
        else if(indiceElemento==8) return "Ethereal Blade";
        else if(indiceElemento==9) return "Monkey King Bar";
        else if(indiceElemento==10) return "Divine Rapier";

        return null;
    }

    public void stampaMatrice() {
        for (int i = 0; i < nr_elementi; i++) {
            for (int j = 0; j < nr_elementi; j++) {
                System.out.printf("%s    ", matEquilibrio[i][j]);
            }
            System.out.println();
        }
    }


}



