package com.world.game.tiles.blocks;

import com.world.game.util.AABBCollisions;
import com.world.game.util.MapVector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormalBlocks extends Block {

    private NormalBlocks(BufferedImage image, MapVector2D position, int width, int height) {
        super(image, position, width, height);
    }

    public static NormalBlocks createNormalBlocks(BufferedImage image, MapVector2D position, int width, int height){
        return new NormalBlocks(image, position, width, height);
    }


    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
        graphics2D.setColor(new Color(29, 239, 221));
        graphics2D.drawRect((int) position.getGameWorldCoordinates().Xcoordinate, (int) position.getGameWorldCoordinates().Ycoordinate, width, height);
    }

    @Override
    public boolean update(AABBCollisions aabbCollisions) {
        return true;
    }


    @Override
    public boolean isInside(AABBCollisions bounds) {
        return false;
    }
}
