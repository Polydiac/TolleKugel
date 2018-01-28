package gameObjects;  /**
 * Created by Sören on 30.03.2017.
 */


import sum.kern.*;
import java.awt.Color;

public class Kugel {
    
    public boolean hide = false;
    double rad;
    double x,y;
    double richt;
    double speed;
    boolean ri,le,to,bo = false;

    public Kugel(double prad, double px, double py, double speed, Color c){
        this.speed = speed;
        rad = prad;
        x = px;
        y = py;
    }

    public void draw(){
    }

    public void del(){
    }
    
    public boolean checkCollision(Kugel k){
        if(Math.sqrt(Math.pow(Math.abs(this.x - k.x),2)+ Math.pow(Math.abs(this.y - k.y),2)) < k.rad + this.rad){
            return true;
        }
        else {
            return false;
        }
    }
    
    public void hideK(){
        this.hide = true;
    }
}
