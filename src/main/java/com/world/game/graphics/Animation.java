package com.world.game.graphics;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;
    private int count;
    private int delays;
    private int timesPlayed;

    private Animation(BufferedImage[] frames){
        timesPlayed = 0;
        setFrames(frames);
    }

    private Animation(){
        timesPlayed = 0;
    }

    public static Animation createAnimation(BufferedImage[] frames){
        return new Animation(frames);
    }
    public static Animation createAnimation(){
        return  new Animation();
    }

    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        count = 0 ;
        timesPlayed = 0 ;
        delays = 2;
        numFrames = frames.length;
    }

    public void setDelays(int delays){
        this.delays = delays;
    }

    public void setFrame(int frame){
        this.currentFrame = frame;
    }

    public void setNumFrames(int frame){
        this.numFrames = frame;
    }

    public int getDelays(){
        return delays;
    }

    public int getFrame(){
        return currentFrame;
    }

    public int getCount(){
        return count;
    }

    public BufferedImage getImage(){
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce(){
        return timesPlayed > 0;
    }

    public void update(){
        if(delays == -1){
            return ;
        }
        count++;
        if(count == delays){
            currentFrame++;
            count = 0;
        }
        if(currentFrame == numFrames){
            currentFrame = 0 ;
            timesPlayed++;
        }
    }

    private boolean hasPlayed(int i ){
        return timesPlayed == i;
    }
}
