package com.world.game.tiles.blocks;

import com.world.game.util.AABBCollisions;
import com.world.game.util.MapVector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HoleBlock extends Block{

    public static HoleBlock createHoleBlock(BufferedImage image, MapVector2D position, int width, int height){
        return new HoleBlock(image, position, width, height);
    }

    private HoleBlock(BufferedImage image, MapVector2D position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABBCollisions aabbCollisions) {
        return false;
    }

    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
        graphics2D.setColor(Color.green);
        graphics2D.drawRect((int) position.getGameWorldCoordinates().Xcoordinate, (int) position.getGameWorldCoordinates().Ycoordinate, width, height);
    }
}
