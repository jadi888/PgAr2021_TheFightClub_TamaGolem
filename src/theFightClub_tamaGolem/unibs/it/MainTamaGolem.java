package theFightClub_tamaGolem.unibs.it;

import java.util.Scanner;

/**
 * @author jadimary
 */

public class MainTamaGolem {

    private static final Equilibrio equilibrio = new Equilibrio();


    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        String scelta;

        System.out.println("BENVENUTI A TAMAGOLAND");
        do{
            System.out.println("Volete iniziare una partita (premi 1 se SI e 0 per no per uscire dal gioco)");
            System.out.println();

            while (true) {
                scelta = input.next();
                int intInputValue = 0;
                try {
                    intInputValue = Integer.parseInt(scelta);
                    break;
                } catch (NumberFormatException ne) {
                    System.out.println("CI SONO DELLE REGOLE:\n " + "Devi necessariamente inserire un intero!");
                }
            }


        switch (Integer.parseInt(scelta)) {
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
        }while(Integer.parseInt(scelta)!=0 && Integer.parseInt(scelta)!=1);

    }
}
