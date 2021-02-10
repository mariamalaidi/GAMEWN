package com.world.game.tiles.blocks;

import com.world.game.util.AABBCollisions;
import com.world.game.util.MapVector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectBlock extends Block{

    public ObjectBlock(BufferedImage image, MapVector2D position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABBCollisions aabbCollisions) {
        return true;
    }

    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
        graphics2D.setColor(Color.white);
        graphics2D.drawRect((int) position.getGameWorldCoordinates().Xcoordinate, (int) position.getGameWorldCoordinates().Ycoordinate,width, height); ;
    }
}
