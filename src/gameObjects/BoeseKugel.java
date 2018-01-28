package gameObjects;

import sum.kern.Bildschirm;
import util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Created by Soeren on 27.04.2017.
 */
public class BoeseKugel extends Kugel {
    boolean speedUp;
    double currentSpeed;
    int startFrame;
    boolean plop;
    int plopframe;
    UUID player;

    public BoeseKugel(double prad, double px, double py, double speed, Color c, UUID user) {
        super(prad, px, py, speed, c);
        currentSpeed = 0.01;
        player = user;
        this.draw();
    }

    public void update(int frame){
        if(speedUp){
            int duration = 10;
            if(frame-startFrame <= duration){
                this.currentSpeed = Easings.sineEaseInOut(frame-startFrame, 0.01f, (float)this.speed, duration);
            }
        }
        ArrayList<Movement> direction = ServerNetworkManager.INSTANCE.getMovementsForUser(player);
        for (int i = 0; i < direction.size(); i++) {
            this.bewege(direction.get(i).getDirection());
        }
        this.draw();
    }


    public void bewege(Vector2D richt){
        this.x = this.x + richt.getX();
        this.y = this.y + richt.getY();
    }

}
