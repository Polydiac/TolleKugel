import sum.kern.Bildschirm;
import sum.kern.Tastatur;

import java.lang.Math;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Soeren on 27.04.2017.
 */
public class BoeseKugel extends Kugel{
    Tastatur tast;
    ArrayList prevKeys;

    public BoeseKugel(double prad, double px, double py, double speed, Color c, Bildschirm pbs) {
        super(prad, px, py, speed, c, pbs);
        this.tast = new Tastatur();
        this.draw();
    }

    public void update(int frame){

        this.bewege(getDirection());
        this.draw();
    }

    protected Vector2D getDirection(){
        Vector2D dir = new Vector2D(0,0);
        //Character[] movKeys = {'w', 'a', 's', 'd'};
        ArrayList<Character> keys = getKeys();
        /*for(int i=0; i<keys.size(); i++){
            for(int x=0; x<movKeys.length; x++){
                if(keys.get(i)==movKeys[x]){
                    continue;
                }
            }
            keys.remove(i);
        }*/
        //System.out.println(keys);
        for(int i=0; i<keys.size();i++){
            switch((char)keys.get(i)){
                case 'w':
                    dir = dir.plus(new Vector2D(0, -this.speed));
                    break;
                case 's':
                    dir = dir.plus(new Vector2D(0, this.speed));
                    break;
                case 'a':
                    dir = dir.plus(new Vector2D(-this.speed, 0));
                    break;
                case 'd':
                    dir = dir.plus(new Vector2D(this.speed, 0));
                    break;
                default:
                    dir = dir.plus(new Vector2D(0,0));
            }
        }
        if(dir.x!=0&&dir.y!=0){
            dir.setR(this.speed);
        }


        //System.out.println(dir);

        return dir;

    }

    private ArrayList<Character> getKeys(){
        ArrayList<Character> keys = new ArrayList();
        ArrayList<Character> pressed = new ArrayList();
        while(this.tast.wurdeGedrueckt()){
            pressed.add(this.tast.zeichen());
            this.tast.weiter();
        }
        for(int i = 0; i < pressed.size(); i++){
            if(!keys.contains(pressed.get(i))){
                keys.add(pressed.get(i));
            }
        }
        //System.out.println(keys);
        return keys;

    }

    public void bewege(Vector2D richt){
        this.x = this.x + richt.getX();
        this.y = this.y + richt.getY();
        this.st.bewegeBis(this.x, this.y);
    }
    
    
    


}
