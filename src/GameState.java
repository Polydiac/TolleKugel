/**
 * Created by Soeren on 15.05.2017.
 */
public class GameState {
    PackageType type;
    String playerID;
    String username;
    BoeseKugel player = null;
    GuteKugel[] kugeln = null;

    public GameState(BoeseKugel pPlayer, String id){
        username = System.getProperty("user.name");
        playerID = id;
        type = PackageType.PLAYER;
        player = pPlayer;
    }

    public GameState(GuteKugel[] pKugeln, String id){
        username = System.getProperty("user.name");
        playerID = id;
        type = PackageType.GUTEKUGELN;
        kugeln = pKugeln;
    }

    public GameState(String id){
        username = System.getProperty("user.name");
        playerID = id;
        type = PackageType.BROADCAST;
    }

    public GameState(){
        username = System.getProperty("user.name");
        type = PackageType.CONNECTION;
    }

}
