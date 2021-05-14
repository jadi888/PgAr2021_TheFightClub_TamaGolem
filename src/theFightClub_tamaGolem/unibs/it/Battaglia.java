package theFightClub_tamaGolem.unibs.it;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author jadimary aka Maria Ciobanu
 */

public class Battaglia {
    public final static String RIGA = "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";

private Player player_1, player_2;
private Equilibrio equilibrio;


public Scanner scan = new Scanner(System.in);

    public Battaglia() throws InterruptedException {
        int quantiPareggi;

        Equilibrio.quantiElementi();
        /**
         * Quando inizia la battaglia per prima cosa viene chiesto il livello di difficoltà della partita
         * in base alla quale verrà stabilito anche il numero di elementi:
         *
         *         EASY  - da 3 a 5 elementi;
         *         MEDIUM - da 6 a 8 elementi;
         *         HARD -  da 9 a 10 elementi;
         */

        /* una volta determinato il numero di elementi della partita viene generato l'equilibro dell'universo
          che resterà uguale per l'intera durata della partita.
         */
            Equilibrio.generaEquilibrio();
            Scorta.inizializzoScorte();

            equilibrio = new Equilibrio();

            System.out.println("I vostri Golem avranno una vita iniziale di " + Equilibrio.vitaGolem);
            System.out.println();

            String nomePlayer1, nomePlayer2;

             System.out.println(RIGA);
             System.out.println("Giocatore 1 inserisci il tuo nome");
             System.out.println(RIGA);
            nomePlayer1= scan.nextLine();
            player_1 = new Player(nomePlayer1.toUpperCase());


            System.out.println(RIGA);
            System.out.println("Giocatore 2 inserisci il tuo nome");
            System.out.println(RIGA);
            nomePlayer2 = scan.nextLine();
            player_2 = new Player(nomePlayer2.toUpperCase());




            boolean golem1Vivo = true, golem2Vivo = true;
            int conteggiaPietre = 0;

        /**@param golem1Vivo è true fino a quando il golem del giocatore @param player_1 è vivo
         * @param golem2Vivo è true fino a quando il golem del giocatore @param player_2 è vivo
         *
         * Fino a quando uno dei due è ancora vivo o ha ancora pietre a disposizione la battaglia tra
         * i due golem continua. Se uno dei due golem muore, viene evocato un nuovo golem e l'utente sceglie le pietre
         * con cui riempirlo fino a quando ha golem a disposizione e ci sono pietre nella scorta.
         */

        do {

            /* In base all'indice della pietra gli assegno il nome preso dal metodo <getNomeElemento>
             * della classe <{@link Equilibrio}> che ha come parametro un int che è l'indice</></>
             */

                quantiPareggi=0;
                Elemento pietraGolem1 = player_1.getPietra();
                int indice1 = pietraGolem1.getIndice();
                String nomePietraGolem1 = Equilibrio.getNomeElemento(indice1 + 1);

                Elemento pietraGolem2 = player_2.getPietra();
                int indice2 = pietraGolem2.getIndice();
                String nomePietraGolem2 = Equilibrio.getNomeElemento(indice2 + 1);

                System.out.println();
                System.out.println("Giocatori, avete scagliato le vostre pietre");
                System.out.println("Il giudice di TamaGoLand sta elaborando i risultati, attendete pochi secondi");
                Thread.sleep(1000); //per questioni estetiche decido di stampare dei puntini a distanza di secondi
                System.out.print("  .  ");// per creare suspance :D
                Thread.sleep(1000);
                System.out.print("  .  ");
                Thread.sleep(1000);
                System.out.print("  .  ");
                Thread.sleep(1000);

                System.out.printf("\nIl golem di %s ha scagliato la pietra %s\n", player_1.getNomePlayer(), nomePietraGolem1);
                System.out.printf("Il golem di %s ha scagliato la pietra %s\n", player_2.getNomePlayer(), nomePietraGolem2);

            /*
             * <getDanno></getDanno> della classe <Equilibrio> mi restituisce @return danno l'intero in posizione i,j della matrice del
             * l'equilibrio, che se negativo, significa che il @param player_1 ha subito danno
             * se positivo signifia che @param player_2 ha subito danno;
             */
            int danno = equilibrio.getDanno(indice1, indice2);

                if (danno > 0) {
                    golem2Vivo = player_2.danneggia(danno);
                    conteggiaPietre = 0;
                } else if (danno < 0) {
                    golem1Vivo = player_1.danneggia(Math.abs(danno));
                    conteggiaPietre = 0;
                } else {
                    System.out.println(RIGA);
                    System.out.println("*****Avete scagliato le stesse pietre, per questa volta siete salvi entrambi!****");
                    quantiPareggi++;
                    System.out.println(RIGA);
                    conteggiaPietre++;
                }

            } while (golem1Vivo && golem2Vivo && conteggiaPietre < Equilibrio.P && quantiPareggi<Equilibrio.P);

            if (conteggiaPietre == Equilibrio.P)
                System.out.println("Avete Pareggiato");
            else {
                if (golem1Vivo){
                    Thread.sleep(2000);
                    System.out.println();
                    System.out.printf("\n****%s****, Congratulazioni, Hai vinto!\n", player_1.getNomePlayer());
                }


                else {
                    System.out.println();
                    System.out.printf("\n****%s****, Congratulazioni, Hai vinto!\n", player_2.getNomePlayer());
                }
            }


            Thread.sleep(3000);
            System.out.println("L'equilibrio iniziale era il seguente!");
            Thread.sleep(3000);
            System.out.println();
            equilibrio.stampaTabella();
            equilibrio.stampaMatrice();

    }

}
