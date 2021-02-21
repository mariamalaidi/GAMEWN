package com.world.game.tiles.blocks;

import com.world.game.util.AABBCollisions;
import com.world.game.util.MapVector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectBlock extends Block{

    private ObjectBlock(BufferedImage image, MapVector2D position, int width, int height) {
        super(image, position, width, height);
    }

    public static ObjectBlock createObjectBlock(BufferedImage image, MapVector2D position, int width, int height){
        return new ObjectBlock(image, position, width, height);

    }

    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
        graphics2D.setColor(Color.white);
        graphics2D.drawRect((int) position.getGameWorldCoordinates().Xcoordinate, (int) position.getGameWorldCoordinates().Ycoordinate,width, height); ;
    }

    @Override
    public boolean update(AABBCollisions aabbCollisions) {
        return false;
    }
    @Override
    public boolean isInside(AABBCollisions bounds) {
        return false;
    }
}
