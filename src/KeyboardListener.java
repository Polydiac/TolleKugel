/**
 * Created by Soeren on 28.04.2017.
 */
import java.util.Hashtable;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardListener implements KeyListener {
    private Hashtable<Character, Boolean> keys;

    public KeyboardListener(){
        keys = new Hashtable<>();
        char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(int i = 0; i<abc.length; i++){
            keys.put(abc[i], false);
        }
    }

    public boolean isKeyPressed(char key) {
        return keys.get(key);
    }

    public Hashtable<Character, Boolean> getTable(){
        return keys;
    }

    @Override
    public void keyTyped(KeyEvent e){
        //System.out.println(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.put(e.getKeyChar(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.put(e.getKeyChar(), false);
    }
}

