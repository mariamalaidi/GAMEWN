package com.world.game.state;

import com.world.game.GamePanel;
import com.world.game.util.KeyHandler;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameStateManger {
    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static MapVector2D position;
    private ArrayList<GameState> states;


    public static GameStateManger createGameStateManger(){
        return new GameStateManger();
    }

    private GameStateManger()  {
        position = MapVector2D.createMapVector2DwithCoordinate(GamePanel.widthOfGameArea, GamePanel.heightOfGameArea);
        MapVector2D.setGameWorldCoordinates(position.Xcoordinate, position.Ycoordinate);
        states = new ArrayList<GameState>();
        states.add(PlayState.createPlayerState(this));
    }

    public void pop(int state) {
        states.remove(state);
    }

    public void add(int state) throws IOException {
        if (state == PLAY) {
            states.add(PlayState.createPlayerState(this));
        }
        if (state == MENU) {
            states.add(new MenuState(this));
        }
        if (state == PAUSE) {
            states.add(new PauseState(this));
        }
        if (state == GAMEOVER) {
            states.add(new GameOverState(this));
        }
    }

    public void addAndPop(int state) throws IOException {

        states.remove(0);

        add(state);
    }

    public void update() {
        updateWorldCoordinates();
        for (GameState state : states) {
            state.update();
        }
    }

    public void updateWorldCoordinates() {
        MapVector2D.setGameWorldCoordinates(position.Xcoordinate, position.Ycoordinate);
    }


    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        for (GameState state : states) {
            state.input(mouseHandler, keyHandler);
        }
    }

    public void render(Graphics2D graphics2D) {
        for (GameState state : states) {
            state.render(graphics2D);
        }
    }

}
