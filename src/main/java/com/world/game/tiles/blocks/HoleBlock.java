package com.world.game.tiles.blocks;
import com.world.game.util.AABBCollisions;
import com.world.game.util.MapVector2D;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HoleBlock extends Block{

    private HoleBlock(BufferedImage image, MapVector2D position, int width, int height) {
        super(image, position, width, height);
    }

    public static HoleBlock createHoleBlock(BufferedImage image, MapVector2D position, int width, int height){
        return new HoleBlock(image, position, width, height);
    }

    public boolean isInside(AABBCollisions aabbCollisions) {
        if(aabbCollisions.getPosition().Xcoordinate + aabbCollisions.getxOffset() < position.Xcoordinate)   return false;
        if(aabbCollisions.getPosition().Ycoordinate + aabbCollisions.getyOffset() < position.Ycoordinate)   return false;
        if(width + position.Xcoordinate < aabbCollisions.getWidth() + (aabbCollisions.getPosition().Xcoordinate + aabbCollisions.getxOffset()))return false;
        if(height + position.Ycoordinate < aabbCollisions.getHeight() + (aabbCollisions.getPosition().Ycoordinate + aabbCollisions.getyOffset()))return false;
        return true;

    }

    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
        graphics2D.setColor(new Color(211, 62, 224));
        graphics2D.drawRect((int) position.getGameWorldCoordinates().Xcoordinate, (int) position.getGameWorldCoordinates().Ycoordinate, width, height);
    }

    @Override
    public boolean update(AABBCollisions aabbCollisions) {
        System.out.println("update");
        System.out.println(isInside(aabbCollisions));
        if (isInside(aabbCollisions)) {
            System.out.println("I am a hole !");
        }
        return true;
    }
}
