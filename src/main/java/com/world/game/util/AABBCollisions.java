package com.world.game.util;
import com.world.game.entity.Entity;


public class AABBCollisions {
    private MapVector2D position;
    private float xOffset = 0;
    private float yOffset = 0;
    private  float width;
    private float height;
    private float radius;
    Entity entity;
    private int size;

    private AABBCollisions(MapVector2D position, int width, int height){
        this.position = position;
        this.height = height;
        this.width = width;
        size = Math.max(width, height);
    }

    public static AABBCollisions createAABBCollisions(MapVector2D position, int width, int height){
        return new AABBCollisions(position, width, height);
    }


    public void setBox(MapVector2D position, int width, int height){
        this.position = position;
        this.width = width;
        this.height = height;
        size = Math.max(width, height);
    }

    public void setCircle(MapVector2D position, int radius){
        this.position = position;
        this.radius = radius;
        size = radius;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setXOffset(float xOffset){
        this.xOffset = xOffset;
    }

    public void setYOffset(float yOffset){
        this.yOffset = yOffset;
    }

    public MapVector2D getPosition(){
        return  position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRadius() {
        return radius;
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }


    public boolean columnCircleBox(AABBCollisions aBox){

        float cx = (float) (position.getGameWorldCoordinates().Xcoordinate + radius/ Math.sqrt(2) - entity.getSize());
        float cy = (float) (position.getGameWorldCoordinates().Ycoordinate + radius/ Math.sqrt(2) - entity.getSize());

        float xDelta = cx - Math.max(aBox.position.getGameWorldCoordinates().Xcoordinate + (aBox.getWidth() / 2), Math.min(cx, aBox.position.getGameWorldCoordinates().Xcoordinate));
        float yDelta = cx - Math.max(aBox.position.getGameWorldCoordinates().Ycoordinate + (aBox.getWidth() / 2), Math.min(cy, aBox.position.getGameWorldCoordinates().Ycoordinate));

        if((xDelta * xDelta + yDelta * yDelta) < ((this.radius/ Math.sqrt(2) ) * (this.radius/ Math.sqrt((2))))){
            return true;
        }
        return false;
    }


}
