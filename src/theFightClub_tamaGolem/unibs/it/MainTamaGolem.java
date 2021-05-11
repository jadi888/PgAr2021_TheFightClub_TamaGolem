package theFightClub_tamaGolem.unibs.it;

import java.util.Scanner;

public class MainTamaGolem {

    private static Equilibrio equilibrio = new Equilibrio();


    public static void main(String[] args) {

       Scanner input = new Scanner(System.in);

        //var x = Equilibrio.generaEquilibrio(5,10);

       /*equilibrio.stampaValori(5, x);
       equilibrio.collegamenti(x, 5);
       equilibrio.stampaTabella();*/


        System.out.println("BENVENUTI A TAMAGOLAND");
        System.out.println("Volete iniziare una partita?");
        System.out.println("(Premi 1 per iniziare una partita)");

        int scelta = input.nextInt();
        switch (scelta){

            case 1:
                Equilibrio.quantiElementi();
                System.out.println("L'equilibrio dell'Universo è stato generato \n" + " Che vinca il più forte!");
            //case 2:





           // case 10: equilibrio.ResetUniverso();
        }


    }
}
