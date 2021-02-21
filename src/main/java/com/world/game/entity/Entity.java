package com.world.game.entity;

import com.world.game.graphics.Animation;
import com.world.game.graphics.Sprite;
import com.world.game.util.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected final int UP = 3 ;
    protected final int DOWN = 2;
    protected final int RIGHT = 0;
    protected final int LEFT = 1;
    protected final int FALLEN = 4;

    protected int currentAnimation;
    protected  Animation animation;
    protected  Sprite sprite;
    protected MapVector2D position;
    protected  int size;
    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attacks;
    protected boolean fallen;
    protected float durationX;
    protected float durationY;
    protected float maxSpeed = 4f;
    protected float accuracy = 3f;
    protected float durationAccuracy = .3f;
    protected AABBCollisions hitBounds;
    protected AABBCollisions bounds;
    protected TileCollision tileCollision;

    public Entity(Sprite sprite, MapVector2D origin, int size){
        this.sprite = sprite;
        position = origin;
        this.size = size;
        bounds = AABBCollisions.createAABBCollisions(origin, size, size);
        hitBounds = AABBCollisions.createAABBCollisions(MapVector2D.createMapVector2DwithCoordinate(origin.Xcoordinate +(size / 2), origin.Ycoordinate),size,size);
        hitBounds.setXOffset(size/2);
        animation = Animation.createAnimation();
        setAnimation(RIGHT,sprite.getSpriteArray(RIGHT),10);
        tileCollision = new TileCollision(this);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setFallen(boolean fallen){
        this.fallen = fallen;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setMaxSpeed(float speed){
        maxSpeed = speed;
    }

    public void setDurationAccuracy(float delta){
        durationAccuracy = delta;
    }

    public void setAnimation(int path, BufferedImage[] frames, int delay){
        currentAnimation = path;
        animation.setFrames(frames);
        animation.setDelays(delay);

    }
    private  void setHitAABBBoxDirection(){
        if(up){
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset(-size/ 2);
        }
        else if(down){
            hitBounds.setXOffset(-size / 2);
            hitBounds.setYOffset(size /2);
        }
        else if(left){
            hitBounds.setXOffset(-size);
            hitBounds.setYOffset(0);
        }
        else if(right){
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(0);
        }
    }

    public int getSize(){
        return size;
    }


    public AABBCollisions getBounds(){
        return bounds;
    }

    public void update(){
        animate();
        setHitAABBBoxDirection();
        animation.update();
    }
    public void animate() {
        if (up) {
            if (currentAnimation != UP || animation.getDelays() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        } else if (down) {
            if (currentAnimation != DOWN || animation.getDelays() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        } else if (left) {
            if (currentAnimation != LEFT || animation.getDelays() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (right) {
            if (currentAnimation != RIGHT || animation.getDelays() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else if (fallen) {
            if (currentAnimation != FALLEN || animation.getDelays() == -1)
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);

        }else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public abstract void render (Graphics2D g);

}

