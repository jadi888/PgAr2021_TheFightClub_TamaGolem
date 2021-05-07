package theFightClub_tamaGolem.unibs.it;

import java.util.*;

public class Equilibrio {

    private static Elemento elemento = new Elemento();
    private static ArrayList<Elemento> elementi = new ArrayList<>();

    private static Random rand = new Random();

    public static void nomiElementi() {
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


       public void collegamenti(int[][] matEquilibrio, int nrElementi) {

        /* creo la hashmap che è un attributo di ogni elementi, ossia, per ogni elemento elenco la lista di elementi
        su cui e forte e la potenza con cui e forte (non salvo le potenze negative o nulle);
         */
        HashMap<Integer, Integer> domina = new HashMap<>();

            int i, j;
            for (i = 0; i < nrElementi; i++) {
                for(j=0; j < nrElementi; j++) {
                    elemento.setIndice(i);//assegno l'indice i come attributo all'elemento i;
                    if(matEquilibrio[i][j]>0) { //se la potenza è positiva, allora la salvo;
                        domina.put(j, matEquilibrio[i][j]);
                        elemento.setDominaSu(domina);
                    }
                    if(matEquilibrio[i][j]<=0) // se la potenza è negativa o nulla skippo;
                        continue;
                }
                elementi.add(elemento);
            }
        }


        public void stampaTabella(){
            /*for (TypeKey name: example.keySet()) {
                String key = name.toString();
                String value = example.get(name).toString();
                System.out.println(key + " " + value);
            }*/

            for(int i=0; i < elementi.size(); i++){
                System.out.print(elementi.get(i).getIndice());
                elementi.get(i).getDominaSu().forEach((key, value) -> System.out.println(key + ":" + value));


            }
        }
    }



