package com.world.game.state;

import com.world.game.GamePanel;
import com.world.game.entity.Player;
import com.world.game.graphics.Font;
import com.world.game.graphics.Sprite;
import com.world.game.tiles.TileManger;
import com.world.game.util.KeyHandler;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;

import java.awt.*;

public class PlayState extends GameState {
    public static String test = " World Navigator Game !";
    private Font font;
    private Player player;
    private TileManger tileManger;

    private PlayState(GameStateManger gameStateManger) {
        super(gameStateManger);
        tileManger = TileManger.createTileManger("src/main/resources/main.xml");
        font = Font.createFont("src/main/resources/Font_Main.png", 10, 10);
        player = Player.createPlayer(Sprite.createSpriteFromImage("src/main/resources/Player.png"), MapVector2D.createMapVector2DwithCoordinate(300, 300), 64);
        player.setFont(font);
    }

    public static PlayState createPlayerState(GameStateManger gameStateManger) {
        return new PlayState(gameStateManger);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        player.input(mouseHandler, keyHandler);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        tileManger.render(graphics2D);
        StringBuilder stringBuilder = new StringBuilder("FPS:");
        stringBuilder.append(GamePanel.oldFrameCount);
        Sprite.drawArray(graphics2D, font, stringBuilder.toString(), MapVector2D.createMapVector2DwithCoordinate(GamePanel.widthOfGameArea - 192,32),20 ,20,32,0);


        Sprite.drawArray(graphics2D, font,"World navigator",  MapVector2D.createMapVector2DwithCoordinate(12, 30
        ),20,20,24,0);
        player.render(graphics2D);

    }

}
