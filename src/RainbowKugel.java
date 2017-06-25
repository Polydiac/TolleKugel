import sum.kern.Bildschirm;

import java.awt.*;
/**
 * @author 
 * @version 
 */
public class RainbowKugel extends GuteKugel
{
    public RainbowKugel(double prad, double px, double py, double pricht, double speed, Color c, Bildschirm pbs) {
        super(prad, px, py,  pricht,  speed, c, pbs);
        richt = pricht;
        this.st.dreheBis(pricht);
    }
    public void draw(int frame){
        
        double center = 128;
        double width = 127;
        int len = 50;
        int i = frame % len;
        double red = Math.sin(0.3*i + 0) * width + center;
        double grn = Math.sin(0.3*i + 2) * width + center;
        double blu = Math.sin(0.3*i + 4) * width + center;
        this.st.setzeFarbe(new Color(red, grn, blu));
        super.draw(frame); 
    }

}
