import gameObjects.BoeseKugel;
import gameObjects.GuteKugel;
import gameObjects.Score;
import sum.kern.*;
import util.GameState;

import java.util.Random;
import java.awt.Color;

/**
 * Created by Sören on 30.03.2017.
 */
public class Game extends DrawThread{
    GameState state;
    int spawnEntities;

    public void init(){

        Score[] scoreboards = new Score[2];
        spawnEntities = 2000;
        BoeseKugel[] players = new BoeseKugel[2];
        players[0] = new BoeseKugel(30,500, 500, 20, Color.BLACK, this.bs, this.kb, 'w', 's', 'a', 'd');
        players[1] = new BoeseKugel(30,500, 500, 20, Color.BLACK, this.bs, this.kb, 'u', 'j', 'h', 'k');
        GuteKugel[] kugeln = getKugelArray(spawnEntities, this.bs);
        for(int i = 0; i <scoreboards.length;i++){
            scoreboards[i] = new Score(900, (i+1)*40, Color.GREEN, 25);
        }
        state = new GameState(kugeln, players, scoreboards);

    }

    public void draw(int frame){

        for(int i = 0; i< state.getKugeln().length;i++){
            if(!state.getKugeln()[i].hide){
                state.getKugeln()[i].bewege(frame);
            }
            if(state.getKugeln()[i].anim){
                state.getKugeln()[i].animateOut(frame);
                state.getKugeln()[i].draw();
            }
        }
        for(int i=0;i<state.getPlayers().length;i++){
            state.getPlayers()[i].update(frame);
        }

        for(int i= 0; i< state.getKugeln().length;i++){

            for(int o=0;o<state.getPlayers().length;o++){
                if(state.getKugeln()[i].checkCollision(state.getPlayers()[o])){
                    if(!state.getKugeln()[i].hide) {
                        state.getScore()[o].incScore();


                        //player.rad = util.Easings.quadEaseOut((float)player.rad+1, 30, 170, 500);
                    }
                    state.getKugeln()[i].toggleAnim(frame);
                    state.getKugeln()[i].hideK();

                }
            }
        }
        for(int i=0; i < state.getScore().length;i++){
            state.getScore()[i].draw();
        }
        int currentBalls = 0;
        for(int i = 0; i< state.getKugeln().length;i++){
            if(!state.getKugeln()[i].hide) {
                currentBalls++;
            }
        }
        if(currentBalls < spawnEntities * (2/3)){
            respawn(frame);
        }
    }

    public void respawn(int frame){
        Random rnd = new Random(System.currentTimeMillis());
        if(frame%1==0){
            for(int i = 0; i< state.getKugeln().length;i++){
                if(state.getKugeln()[i].hide) {
                    state.getKugeln()[i] = new GuteKugel(5 + rnd.nextInt(50), rnd.nextDouble() * bs.breite(), rnd.nextDouble() * bs.hoehe(), rnd.nextInt(360), rnd.nextDouble() * 10, new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)), bs);
                }
            }
        }
    }

    public void delete(){
        for(int i=0;i<state.getPlayers().length;i++){
            state.getPlayers()[i].del();
        }
        for(int i = 0; i< state.getKugeln().length;i++){
            state.getKugeln()[i].del();
        }
        for(int i = 0; i <state.getScore().length;i++){
            state.getScore()[i].del();
        }


    }

    public static void main(String[]args){
        new Game();
    }

    public static GuteKugel[] getKugelArray(int kugelCount, Fenster pbs){
        Random rnd = new Random(System.currentTimeMillis());
        GuteKugel[] kugeln = new GuteKugel[kugelCount];
        for(int i = 0; i<kugeln.length;i++){
            kugeln[i] = new GuteKugel(5+rnd.nextInt(50),rnd.nextDouble()*pbs.breite(),rnd.nextDouble()*pbs.hoehe(), rnd.nextInt(360), rnd.nextDouble()*10,new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)) , pbs);
        }
        return kugeln;
    }
}
