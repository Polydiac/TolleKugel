package gameObjects;

import sum.kern.Bildschirm;
import util.Easings;
import util.KeyboardListener;
import util.Vector2D;

import javax.xml.bind.ValidationException;
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Soeren on 27.04.2017.
 */
public class BoeseKugel extends Kugel {
    KeyboardListener keyboard;
    ArrayList prevKeys;
    boolean speedUp;
    double currentSpeed;
    int startFrame;
    boolean plop;
    int plopframe;
    char UP,DOWN,RIGHT,LEFT;
    

    public BoeseKugel(double prad, double px, double py, double speed, Color c, Bildschirm pbs, KeyboardListener kb, char up, char down, char left, char right) {
        super(prad, px, py, speed, c, pbs);
        UP = up;
        DOWN = down;
        LEFT = left;
        RIGHT = right;
        this.keyboard = kb;
        currentSpeed = 0.01;
        this.draw();
    }

    public void update(int frame){
        if(speedUp){
            int duration = 10;
            if(frame-startFrame <= duration){
                this.currentSpeed = Easings.sineEaseInOut(frame-startFrame, 0.01f, (float)this.speed, duration);
            }
        }
        Vector2D direction = getDirection(frame);

        this.bewege(direction);
        this.draw();
    }

    protected Vector2D getDirection(int frame){

        Vector2D dir = new Vector2D(0,0);

        Hashtable keys = keyboard.getTable();

        boolean anyKey = false;

        if((boolean)keys.get((Character) UP)){
            dir = dir.plus(new Vector2D(0, -this.currentSpeed));
            anyKey =true;
            if (!speedUp) {
                startFrame = frame;
                speedUp = true;
            }
        }
        if((boolean)keys.get((Character) LEFT)){
            dir = dir.plus(new Vector2D(-this.currentSpeed, 0));
            anyKey =true;
            if (!speedUp) {
                startFrame = frame;
                speedUp = true;
            }
        }
        if((boolean)keys.get((Character) DOWN)){
            dir = dir.plus(new Vector2D(0, this.currentSpeed));
            anyKey =true;
            if (!speedUp) {
                startFrame = frame;
                speedUp = true;
            }
        }
        if((boolean)keys.get((Character) RIGHT)){
            dir = dir.plus(new Vector2D(this.currentSpeed, 0));
            anyKey =true;
            if (!speedUp) {
                startFrame = frame;
                speedUp = true;
            }
        }

        if(dir.x!=0&&dir.y!=0){
            dir.setR(this.currentSpeed);

        }
        if(!anyKey){
            speedUp = false;
            startFrame = 0;
            currentSpeed = 0.1;
        }

        return dir;

    }


    public void bewege(Vector2D richt){
        this.x = this.x + richt.getX();
        this.y = this.y + richt.getY();
        this.st.bewegeBis(this.x, this.y);
    }

}
