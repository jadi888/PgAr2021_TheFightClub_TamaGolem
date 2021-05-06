package theFightClub_tamaGolem.unibs.it;

import java.text.DecimalFormat;
import java.util.*;

public class Equilibrio {

    private ArrayList<Elemento> elementi = new ArrayList<>();
    private static Random rand = new Random();


    public static int[][] generaEquilibrio(int nr_elementi, int potenza_max) {

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
                                }
                            else {
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
                                            if((somma == 0 || Math.abs(somma) > potenza_max))
                                                restart = true;
                                             }
                                        }
                                    else {
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



        public static int estraggoIntero ( int minimo, int massimo){
            int range = massimo + 1 - minimo;
            int intCasuale = rand.nextInt(range);
            return intCasuale + minimo;
        }

        //solo per la verifica della funzionalità della matrice di equilibrio
        public void stampaValori ( int n, int[][] mat){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(mat[i][j] + "    ");
                }
                System.out.print("\n");
            }
        }
    }



