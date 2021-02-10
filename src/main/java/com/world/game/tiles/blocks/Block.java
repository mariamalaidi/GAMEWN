package com.world.game.tiles.blocks;

import com.world.game.util.AABBCollisions;
import com.world.game.util.MapVector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected  int width;
    protected  int height;
    protected BufferedImage image;
    protected MapVector2D position;

    public Block(BufferedImage image, MapVector2D position, int width, int height){
        this.image = image;
        this.height = height;
        this.width = width;
        this.position = position;
    }

    public abstract boolean update(AABBCollisions aabbCollisions);

    public void render(Graphics2D graphics2D){
        graphics2D.drawImage(image, (int) position.getGameWorldCoordinates().Xcoordinate, (int) position.getGameWorldCoordinates().Ycoordinate, width, height, null);
    }

}
