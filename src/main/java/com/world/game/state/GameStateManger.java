package com.world.game.state;

import com.world.game.GamePanel;
import com.world.game.util.KeyHandler;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameStateManger {
    private final ArrayList<GameState> states;
    private final PlayState playerState ;
    private String userName;
    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static MapVector2D position;


    public GameStateManger()  {
        playerState = PlayState.createPlayerState(this);
        position = MapVector2D.createMapVector2DwithCoordinate(GamePanel.widthOfGameArea, GamePanel.heightOfGameArea);
        MapVector2D.setGameWorldCoordinates(position.Xcoordinate, position.Ycoordinate);
        states = new ArrayList<GameState>();
        states.add(playerState);
    }

    public static GameStateManger createGameStateManger(){
        return new GameStateManger();
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
