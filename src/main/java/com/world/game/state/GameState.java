package com.world.game.state;
import java.awt.*;
import com.world.game.util.KeyHandler;
import com.world.game.util.MouseHandler;

public abstract class GameState {
    private GameStateManger gameStateManger;
    public GameState(GameStateManger gameStateManger){
        this.gameStateManger = gameStateManger;
    }
    public abstract void update();
    public abstract void input(MouseHandler mouseHandler, KeyHandler keyHandler);
    public abstract void render(Graphics2D graphics2D);
}
