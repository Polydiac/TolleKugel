import java.awt.Color;

import sum.kern.*;
/**
 * Beschreiben Sie hier die Klasse Score.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Score
{
    int score = 0;
    double x,y;
    Color colour;
    Buntstift st;
    
    public Score(double px, double py, Color c){
        x = px;
        y = py;
        st = new Buntstift();
        st.setzeFarbe(c);
        
    }
    public void setScore(int sc){
        score = sc;
    }
    public void incScore(){
        score ++;
    }
    public void draw(){
        st.bewegeBis(x,y);
        st.schreibeZahl(score);
    }
    public void del(){
        st.radiere();
        draw();
        st.normal();
    }
}
