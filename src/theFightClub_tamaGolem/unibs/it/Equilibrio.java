package theFightClub_tamaGolem.unibs.it;

import java.util.*;

public class Equilibrio {

    //un elemento Stringa vincerà su un altro elemento stringa con potenza integer
    HashMap<String, HashMap<String, Integer>> Universo = new HashMap<>();
    HashMap<String, Integer> Deboli = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    Random rand = new Random();

    public void AggiungoElemento() {
        System.out.println("Quanti elementi esistono nel vostro Universo?");
        int elementi = scanner.nextInt();
        System.out.println("Qual è la potenza massima nel tuo universo?");
        int potenza = scanner.nextInt();
        Deboli = Collegamenti(elementi, potenza);
        for (int i = 0; i < elementi; i++) {
            Universo.put("i+1", Deboli);
        }

    }


    public void ResetUniverso() {
        System.out.println("Vuoi iniziare un'altra partita?");
        int si = 0;
        while(si!=9) {
            System.out.println("Premi 9 per iniziare una nuova partita!");
            si = scanner.nextInt();
            if(si==0){
                Universo.clear();
            }
        }
    }


    public HashMap<String, Integer> Collegamenti(int elementi, int potenza) {
        /*Genero un intero random tra il numero di elementi per decidere rispetto a quanti elementi
        un determinato elemento è forte;
         */
        int pot;
        int[][] matrice_adj = null;
        int pot_restante = 0;

        for (int i = 0; i < elementi; i++) {
            for (int j = 0; j < elementi; j++) {
                pot = rand.nextInt() % potenza; //genero una potenza totale che può avere un elemento che andrà divisa sugli altri
                if (i == j)
                    matrice_adj[i][j] = 0; // perchè un elemento contro se stesso non è ne forte ne debole;
                    Deboli.put("i+1", 0);
                else {
                    while (pot_restante != 0) {
                        matrice_adj[i][j] = rand.nextInt() % pot;
                        matrice_adj[j][i] = 0; //perchè se è forte in una direzione allora nel senso inverso è 0;
                        pot_restante = pot - matrice_adj[i][j];
                        Deboli.put("i+1", matrice_adj[i][j]);
                    }
                }
            }
        }

        return Deboli;
        }


}