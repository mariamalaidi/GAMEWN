package com.world.game.util;

public class MapVector2D {
    public static float worldX;
    public static float worldY;
    public float Xcoordinate;
    public float Ycoordinate;

    private MapVector2D(){
        Xcoordinate = Ycoordinate = 0;
    }

    private MapVector2D(float xcoordinate, float ycoordinate){
        this.Xcoordinate = xcoordinate;
        this.Ycoordinate = ycoordinate;
    }

    public static MapVector2D createMapVector2D(){
        return new MapVector2D();
    }

    public  static  MapVector2D createMapVector2DwithCoordinate(float Xcoordinate, float Ycoordinate){
        return new MapVector2D(Xcoordinate, Ycoordinate);
    }

    public void setXcoordinate(float f){
        Xcoordinate = f;
    }

    public void setYcoordinate(float f){
        Ycoordinate = f;
    }

    public static void setGameWorldCoordinates(float x, float y){
        worldX = x;
        worldY = y;
    }

    public MapVector2D getGameWorldCoordinates(){
        return new MapVector2D(Xcoordinate - worldX, Ycoordinate - worldY);
    }

    @Override
    public String toString(){
        return "Position: "+Xcoordinate + " , " + Ycoordinate;
    }
}
