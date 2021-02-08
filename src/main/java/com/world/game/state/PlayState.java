package com.world.game.state;

import com.world.game.GamePanel;
import com.world.game.entity.Player;
import com.world.game.graphics.Sprite;
import com.world.game.graphics.Font;
import com.world.game.util.KeyHandler;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;

import java.awt.*;
import java.io.IOException;

public class PlayState extends GameState{
    private Font font;
    private Player player;

    public PlayState(GameStateManger gameStateManger) throws IOException {
        super(gameStateManger);
        font = new Font("src/main/resources/Font_Main.png",10, 10);
        player = new Player(new Sprite("src/main/resources/Player.png"), new MapVector2D(300,300),64);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        player.input(mouseHandler, keyHandler);

    }
    public static String test = " World Navigator Game !";
    @Override
    public void render(Graphics2D graphics2D) {
        Sprite.drawArray(graphics2D, font, GamePanel.oldFrameCount+ "FPS", new MapVector2D(GamePanel.width - 192,32),20 ,20,32,0);
        Sprite.drawArray(graphics2D, font,test, new MapVector2D(0, 30
        ),20,20,24,0);
        player.render(graphics2D);
    }

}
