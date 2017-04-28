/**
 * Created by Soeren on 28.04.2017.
 */
/*
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Dictionary;
import java.util.Hashtable;

public class KeyboardListener {
    private static Hashtable<Character, Boolean> keys = new Hashtable<>();
    public static boolean isKeyPressed(char key) {
        keys.put('w', false);
        keys.put('a', false);
        keys.put('s', false);
        keys.put('d', false);
        synchronized (KeyboardListener.class) {
            return keys.get(key);
        }
    }

    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (KeyboardListener.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            keys.put(ke.getKeyChar(), true);
                            break;

                        case KeyEvent.KEY_RELEASED:
                            keys.put(ke.getKeyChar(), false);
                            break;
                    }
                    return false;
                }
            }
        });
    }
}*/
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

