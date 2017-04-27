 

import sum.kern.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 * Created by Sören on 22.03.2017.
 */
public abstract class DrawThread {

    protected Fenster bs;
    protected Tastatur ts;
    int frame = 0;
    int framerate = 25;
    long interval;

    public DrawThread(){
        this(1000, 1000, 25);

    }

    public DrawThread(int width, int height){
        this(width, height, 25);
    }

    public DrawThread(int width, int height, int pframerate){
        bs = new Fenster(width, height, true);
        ts = new Tastatur();
        framerate = pframerate;
        interval = 1000 / framerate;

        bs.setIgnoreRepaint(true);
        bs.createBufferStrategy(2);

        this.run();


    }

    public Fenster getFenster(){ return bs;}

    protected void run(){
        bs.setBackground(new Color(255, 255, 255));
        init();
        while(!(ts.wurdeGedrueckt()&& ts.zeichen() == Zeichen.ESCAPE)){
            //bs.getBu
            long startTime = System.nanoTime();
            this.delete();
            this.draw(frame);

            bs.getBufferStrategy().show();
            if(bs != null){
                bs.zeichneDich();
            }
            long stopTime = System.nanoTime() - startTime;
            if(interval - (stopTime / 1000000) > 0){
                try {
                    Thread.sleep(interval - (stopTime / 1000000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            frame++;
        }
        bs.gibFrei();
    }

    public void init(){

    }

    public void draw(int frame){

    }


    public void delete(){
        //bs.setzeFarbe(Color.WHITE);
        bs.loescheAlles();

    }

}
