package com.world.game.util;

public class MapVector2D {

    public float Xcoordinate;
    public float Ycoordinate;
    public static float worldX;
    public static float worldY;

    public static MapVector2D createMapVector2D(){
        return new MapVector2D();
    }

    public static MapVector2D createMapVector2D(MapVector2D position){
        return new MapVector2D(position);
    }
    public  static  MapVector2D createMapVector2DwithCoordinate(float xcoordinate, float ycoordinate){
        return new MapVector2D(xcoordinate, ycoordinate);
    }

    private MapVector2D(){
        Xcoordinate = Ycoordinate = 0;
    }


    private MapVector2D(MapVector2D pos){
        new MapVector2D(pos.Xcoordinate, pos.Ycoordinate);
    }



    private MapVector2D(float xcoordinate, float ycoordinate){
        this.Xcoordinate = xcoordinate;
        this.Ycoordinate = ycoordinate;
    }

    public void addX(float f){
        Xcoordinate +=f;
    }
    public void addY(float f){
        Ycoordinate +=f;
    }
    public void setXcoordinate(float f){
        Xcoordinate = f;
    }
    public void setYcoordinate(float f){
        Ycoordinate = f;
    }


    public void setVector(MapVector2D mapVector2D){
        this.Xcoordinate = mapVector2D.Xcoordinate;
        this.Ycoordinate = mapVector2D.Ycoordinate;
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
