/**
 * Created by Soeren on 15.05.2017.
 */
public class Message {
    PackageType type;
    String playerID;
    String username;
    long timecode;
    PackageType requestType;
    BoeseKugel player = null;
    GuteKugel[] kugeln = null;

    public Message(BoeseKugel pPlayer, String id){
        timecode = System.currentTimeMillis();
        username = System.getProperty("user.name");
        playerID = id;
        type = PackageType.PLAYER;
        player = pPlayer;
    }

    public Message(GuteKugel[] pKugeln, String id){
        timecode = System.currentTimeMillis();
        username = System.getProperty("user.name");
        playerID = id;
        type = PackageType.GUTEKUGELN;
        kugeln = pKugeln;
    }

    public Message(PackageType type){
        switch (type){
            case BROADCAST:
                timecode = System.currentTimeMillis();
                username = System.getProperty("user.name");
                this.type = PackageType.BROADCAST;
                break;
            case CONNECTION:
                timecode = System.currentTimeMillis();
                username = System.getProperty("user.name");
                this.type = PackageType.CONNECTION;
                break;
            default:
                break;
        }

    }

    public Message(PackageType type, PackageType requestType){
        switch (type){
            case REQUEST:
                timecode = System.currentTimeMillis();
                username = System.getProperty("user.name");
                this.type = PackageType.CONNECTION;
                this.requestType = requestType;
                break;
            default:
                break;
        }

    }

}
