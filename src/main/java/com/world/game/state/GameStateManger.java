package com.world.game.state;

import java.awt.*;

import com.world.game.util.KeyHandler;
import com.world.game.util.MouseHandler;
import java.util.ArrayList;

public class GameStateManger {
    private ArrayList<GameState> states;

    public GameStateManger(){
    states = new ArrayList<GameState>();
    states.add(new PlayState(this));
    }

    public void update(){
        for(int i = 0 ; i < states.size() ; i++){
            states.get(i).update();
        }
    }
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){
        for(int i = 0 ; i < states.size() ; i++){
            states.get(i).input(mouseHandler, keyHandler);
        }
    }
    public void render(Graphics2D graphics2D){
        for(int i = 0 ; i < states.size() ; i++){
            states.get(i).render(graphics2D);
        }
    }

}
