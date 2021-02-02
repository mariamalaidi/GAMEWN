package com.world.game.util;

public class MapVector2D {

    public float x;
    public float y;

    public static float worldx;
    public static float worldy;

    public MapVector2D(){
        x = y = 0;
    }

    public MapVector2D(MapVector2D pos){
        new MapVector2D(pos.x, pos.y);
    }

    public MapVector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void addX(float f){
        x+=f;
    }
    public void addY(float f){
        y+=f;
    }
    public void setX(float f){
        x= f;
    }
    public void setY(float f){
        y = f;
    }

    public void setVector(MapVector2D mapVector2D){
        this.x = mapVector2D.x;
        this.y = mapVector2D.y;
    }

    public static void setWorldVar(float x, float y){
        worldx = x;
        worldy = y;
    }

    public MapVector2D getWorldVar(){
        return new MapVector2D(x - worldx, y - worldy);
    }

    @Override
    public String toString(){
        return x + " , " + y;
    }
}
