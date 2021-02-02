package com.world.game.state;

import java.awt.*;

import com.world.game.GamePanel;
import com.world.game.util.KeyHandler;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;
import java.util.ArrayList;

public class GameStateManger {
    private ArrayList<GameState> states;
    public static MapVector2D map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    public GameStateManger(){
        map = new MapVector2D(GamePanel.width, GamePanel.height);
        MapVector2D.setWorldVar(map.x, map.y);
        states = new ArrayList<GameState>();
        states.add(new PlayState(this));
    }

    public void pop(int state){
        states.remove(state);
    }

    public void add(int state){
        if(state == PLAY){
            states.add(new PlayState((this)));
        }
        if(state == MENU){
            states.add(new MenuState(this));
        }
        if(state == PAUSE){
            states.add(new PauseState(this));
        }
        if(state == GAMEOVER){
            states.add(new GameOverState(this));
        }
    }
    public void addAndPop(int state){
        states.remove(0);
        add(state);
    }
    public void update(){
        MapVector2D.setWorldVar(map.x, map.y);
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
