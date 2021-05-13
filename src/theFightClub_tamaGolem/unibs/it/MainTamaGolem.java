package theFightClub_tamaGolem.unibs.it;

import java.util.Scanner;

/**
 * @author jadimary
 */

public class MainTamaGolem {

    private static Equilibrio equilibrio = new Equilibrio();


    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        int scelta;

        System.out.println("BENVENUTI A TAMAGOLAND");
        do{
            System.out.println("Volete iniziare una partita (premi 1 se SI e 0 per no per uscire dal gioco)");
            System.out.println();

        scelta = input.nextInt();

        switch (scelta) {
            case 0:
                System.exit(0);
                break;
            case 1:
                new Battaglia();
                break;
            default:
                System.out.println("Questo Universo ha delle regole" + "\n" + "Premi uno dei comandi messi a disposizione!");
                continue;
             }
        }while(scelta!=0 && scelta!=1);

    }
}
