package theFightClub_tamaGolem.unibs.it;

public class Battaglia {
    int vitaGolem ;
    private Golem golem1 = new Golem(vitaGolem) ;
    private Golem golem2 = new Golem(vitaGolem);


    private Player giocatore1;
    private Player giocatore2;
    public Battaglia(Equilibrio equilibrio){

    }

    /*public Battaglia(Player giocatore_1,Player giocatore_2){
        this.giocatore1 = giocatore_1;
        this.giocatore2 = giocatore_2;
    }*/

    public void Scontro() {

        golem1.addPietra();



    }

}
