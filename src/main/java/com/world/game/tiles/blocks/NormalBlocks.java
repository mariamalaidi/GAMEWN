package com.world.game.tiles.blocks;

import com.world.game.util.AABBCollisions;
import com.world.game.util.MapVector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormalBlocks extends Block {
    public static NormalBlocks createNormalBlocks(BufferedImage image, MapVector2D position, int width, int height){
        return new NormalBlocks(image, position, width, height);
    }
    private NormalBlocks(BufferedImage image, MapVector2D position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABBCollisions aabbCollisions) {
        return false;
    }

    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
    }
}
