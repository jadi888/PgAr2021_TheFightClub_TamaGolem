package theFightClub_tamaGolem.unibs.it;

import javax.swing.*;
import java.util.*;

public class Equilibrio {
    private static ArrayList<Elemento> elementi = new ArrayList<>();
    public static int[][] matEquilibrio;

    private static Random rand = new Random();


    public static int nr_elementi;
    public static int vitaGolem; //sarà uguale al numeri di elementi * 10, per rendere la partita più interessante ;)
    public static int G;//golem per giocatore
    public static int P; //pietre per golem
    public static int S; //pietre disponibili;
    public static int PxElemento; //pietre per elemento


    public static void quantiElementi() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Scegli il livello della partita: \n" + "1 - EASY\n" + "2 - INTERMEDIO\n" + "3 - HARD\n");
            int scelta = scan.nextInt();
            boolean exit = true;
            switch (scelta) {
                case 1:
                    nr_elementi = Math.abs(estraggoIntero(3, 5));
                    break;
                case 2:
                    nr_elementi = Math.abs(estraggoIntero(6, 8));
                    break;
                case 3:
                    nr_elementi = Math.abs(estraggoIntero(9, 10));
                default: {
                    exit = false;
                    System.out.println("Scegli uno dei livelli disponibili!");
                }
            }
        } while(false);

        vitaGolem = nr_elementi*10;
        P = (int) Math.ceil(((vitaGolem+1)/3)+1); //pietre per golem
        G = (int) Math.ceil((vitaGolem-1)*(vitaGolem-2))/(2*P); //golem per giocatore
        S = (int) Math.ceil(((2*P*G)/(vitaGolem))*vitaGolem); //scorta pietre comune ai due giocatori;
        PxElemento = (int) Math.ceil((2*P*G)/(vitaGolem));

    }



    public static void generaEquilibrio() {


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



    public void collegamenti() {
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
        return matEquilibrio[i-1][j-1]; //ritorna il danno causato o subito di un elemento;
    }



    public void stampaTabella() {
        for (int i = 0; i < elementi.size(); i++) {
            System.out.print("L'elemento " +  elementi.get(i).getIndice() + "   ");
            elementi.get(i).getDominaSu().forEach((key, value) -> System.out.println("domina sull'elemento " + key + " con potenza:" + value));
        }
    }


/*Farò scegliere al giocatore l'elemento da caricare del golem dall'elenco di elementi gia' disponibili.
Man mano che carica un elemento, dovrò diminuire la quantità dispoinibili nella scorta di quel determinato
elemento;
 */
    public static Elemento caricaPietra(){
        Scanner scan = new Scanner(System.in);
        Elemento pietra;
        int scelta;
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

        //gli mostro l'elenco delle pietre disponibili in base al nr di elementi nella partita
        System.out.printf("Premi un numero da 1 a %d per aggiungere uno degli elementi: \n");
        for(int i=0; i < nr_elementi; i++){
            System.out.printf("%d per aggiungere %s\n", i+1, nomiElementi.get(i));
        }

        //ovviamente il numero che sceglie deve essere compreso tra 1 e il numero totale di elementi;
        do {
            scelta = scan.nextInt();
            pietra = elementi.get(scelta-1);
        }while((scelta < 1) || !(scelta > nr_elementi) || Scorta.pietrePerElemento[scelta-1] == 0);
        Scorta.pietrePerElemento[scelta-1]--;

        System.out.printf("Hai scelto la pietra %s \n", nomiElementi.get(scelta));
        System.out.printf("Nella scorta restano ancora %d pietre dell'elemeneto %s\n", Scorta.pietrePerElemento[scelta-1], nomiElementi.get(scelta));

        return pietra; //ritorno la pietra che verrà aggiunta alla queue di pietre del golem.
    }




}



