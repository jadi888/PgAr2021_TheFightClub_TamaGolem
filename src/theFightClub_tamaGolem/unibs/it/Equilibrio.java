package theFightClub_tamaGolem.unibs.it;

import javax.swing.*;
import java.util.*;

public class Equilibrio {
    private ArrayList<Elemento> elementi = new ArrayList<>();

    private static Random rand = new Random();
    private static int nr_elementi;

    public static int quantiElementi() {
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

        return nr_elementi;
    }

    public static int getNr_elementi(){
        return nr_elementi;
    }


    public static int[][] generaEquilibrio() {

        int nr_elementi = quantiElementi();
        int potenza_max = nr_elementi*10; //è anche la vita del golem
        int[][] matEquilibrio = new int[nr_elementi][nr_elementi];
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

        return matEquilibrio;
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



    public void collegamenti(int[][] matEquilibrio, int nrElementi) {

        int i, j;
        for (i = 0; i < nrElementi; i++) {
            Elemento elemento =  new Elemento();
            elemento.setIndice(i);
            for (j = 0; j < nrElementi; j++) {
                    elemento.addElemento(j, matEquilibrio[i][j]);
            }
            elementi.add(elemento);
        }
    }


    public void stampaTabella() {
        for (int i = 0; i < elementi.size(); i++) {
            System.out.print("L'elemento " +  elementi.get(i).getIndice() + "   ");
            elementi.get(i).getDominaSu().forEach((key, value) -> System.out.println("domina sull'elemento " + key + " con potenza:" + value));

        }
    }


    public static void nomiElementi1() {
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
    }
}



