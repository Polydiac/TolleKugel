import sum.kern.Bildschirm;

import java.awt.*;
import java.util.Random;

/**
 * Created by Soeren on 20.05.2017.
 */
public class PowerUp extends GuteKugel {
    PowerUpType type;
    int length;

    public PowerUp(){
        int rnd = new Random().nextInt(4);
        switch(rnd){
            case 1:
                this.type = PowerUpType.ENLARGEN;
                break;
            case 2:
                this.type = PowerUpType.SLOWDOWN;
                break;
            case 3:
                this.type = PowerUpType.SMALLEN;
                break;
            case 4:
                this.type = PowerUpType.SPEEDUP;
                break;
        }
    }

    public PowerUp(PowerUpType type) {
        this.type = type;
    }

    public PowerUp(double prad, double px, double py, double pricht, double speed, Color c, Bildschirm pbs, String id, PowerUpType type) {
        super(prad, px, py, pricht, speed, c, pbs, id);
        this.type = type;
    }
    public PowerUp(double prad, double px, double py, double pricht, double speed, Color c, Bildschirm pbs, String id) {
        super(prad, px, py, pricht, speed, c, pbs, id);
        int rnd = new Random().nextInt(4);
        this.length = new Random().nextInt(10000)+50;
        switch(rnd){
            case 1:
                this.type = PowerUpType.ENLARGEN;
                break;
            case 2:
                this.type = PowerUpType.SLOWDOWN;
                break;
            case 3:
                this.type = PowerUpType.SMALLEN;
                break;
            case 4:
                this.type = PowerUpType.SPEEDUP;
                break;
        }
    }

    public void bewege(double weite, int frame){
        this.st.setzeFarbe(generateRainbow(frame));
        super.bewege(this.speed, frame);
    }

    public void bewege(int frame){
        this.st.setzeFarbe(generateRainbow(frame));
        super.bewege(frame);
    }

    public static Color generateRainbow(int frame){
        int step = frame % 255;
        double frequency = 0.3;
        double red   = Math.sin(frequency*step + 0) * 127 + 128;
        double green = Math.sin(frequency*step + 2) * 127 + 128;
        double blue  = Math.sin(frequency*step + 4) * 127 + 128;

        return new Color((int) red, (int) green, (int) blue);
    }
}
