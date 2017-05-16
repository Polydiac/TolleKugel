/**
 * Created by Soeren on 15.05.2017.
 */
public class GameState {
    PackageType type;
    String senderID;
    BoeseKugel player = null;
    GuteKugel[] kugeln = null;

    public GameState(BoeseKugel pPlayer){
        type = PackageType.PLAYER;
        player = pPlayer;
    }

    public GameState(GuteKugel[] pKugeln){
        type = PackageType.GUTEKUGELN;
        kugeln = pKugeln;
    }

    public GameState(){
        type = PackageType.BROADCAST;
    }
}
