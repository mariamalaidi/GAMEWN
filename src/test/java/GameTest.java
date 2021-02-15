import com.world.game.GamePanel;
import com.world.game.Window;
import com.world.game.entity.Player;
import com.world.game.graphics.Sprite;
import com.world.game.tiles.TileManger;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void testGameFrames(){
        assertTrue(GamePanel.getFPS() < 90 );
    }
    @Test
    public void testWidthOfGamePanel(){
        assertEquals(Window.width, 1280);
    }
    @Test
    public void testHeightOfGamePanel(){
        assertEquals(Window.height, 720);
    }
    @Test
    public void testPlayerGoldAmount(){
        Player player= Player.createPlayer(Sprite.createSpriteFromImage("src/main/resources/Player.png"), MapVector2D.createMapVector2DwithCoordinate(300, 300), 64);
        assertEquals(player.getGoldAmount(),20);
    }

    @Test
    public void testToString(){
        MapVector2D position = MapVector2D.createMapVector2DwithCoordinate(300, 300);
        assertTrue(position.toString().contains("Position"));
    }

    @Test
    public void testBlockSize(){
        TileManger tileManger = new TileManger();
        assertEquals(tileManger.getBLOCKSIZE(),64);
    }
    @Test
    public void testMouseHandler(){
        GamePanel gamePanel = GamePanel.getInstance(64,64);
        MouseHandler mouseHandler = MouseHandler.createMouseHandler(gamePanel);
        assertEquals(MouseHandler.getMouseX(), -1);
        assertEquals(MouseHandler.getMouseY(), -1);
    }
}