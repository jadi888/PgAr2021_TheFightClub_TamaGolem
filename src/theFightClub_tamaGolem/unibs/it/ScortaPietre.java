package theFightClub_tamaGolem.unibs.it;

public class ScortaPietre {
    private static Golem golem = new Golem();
    private static Player player = new Player();
    int S = ((2*golem.getP()* player.G)/ golem.getVitaGolem())* golem.getVitaGolem();

    public int getS(){
        return S;
    }
}
