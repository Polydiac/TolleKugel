package gameObjects;

import sum.kern.Buntstift;

import java.awt.*;

/**
 * Beschreiben Sie hier die Klasse gameObjects.ScoreImpl.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Score
{
    int score = 0;
    double x,y;
    Color colour;

    public Score(double px, double py, Color c, int pSchriftgroesse){
        x = px;
        y = py;

    }
    public void setScore(int sc){
        score = sc;
    }
    public void incScore(){
        score ++;
    }
    public void draw(){
    }
    public void del(){
    }
}
