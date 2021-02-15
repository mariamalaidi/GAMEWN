package com.world.game.entity;
import com.world.game.graphics.Font;
import com.world.game.graphics.Sprite;
import com.world.game.util.KeyHandler;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;

import java.awt.*;


public class Player extends  Entity{

    private int goldAmount;
    public static String name ="";
    private Font font;

    public static Player createPlayer(Sprite sprite, MapVector2D origin, int size){
        return new Player(sprite, origin, size);
    }

    public Player(Sprite sprite, MapVector2D origin, int size) {
        super(sprite, origin, size);
    }

    public String getName(){
        return name;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void move(){
        if(up){
            durationY -= accuracy;
            if(durationY < -maxSpeed){
                durationY = -maxSpeed;
            }
        }
        else {
            if(durationY <  0){
                durationY += durationAccuracy;
                if(durationY < 0){
                    durationY = 0;
                }
            }
        }
        if(down){
            durationY += accuracy;
            if(durationY > maxSpeed){
                durationY = maxSpeed;
            }
        }else {
            if(durationY > 0){
                durationY -= durationAccuracy;
                if(durationY > 0){
                    durationY = 0;
                }
            }
        }
        if(left){
            durationX -= accuracy;
            if(durationX < -maxSpeed){
                durationX = -maxSpeed;
            }
        }
        else {
            if(durationX <  0){
                durationX += durationAccuracy;
                if(durationX > 0){
                    durationX = 0;
                }
            }
        }
        if(right){
            durationX += accuracy;
            if(durationX > maxSpeed){
                durationX = maxSpeed;
            }
            else {
                if(durationX > 0){
                    durationX -= durationAccuracy;
                    if(durationX < 0){
                        durationX = 0;
                    }
                }
            }
        }

    }

    public void update(){
        super.update();
        move();
        position.Xcoordinate += durationX;
        position.Ycoordinate += durationY;
    }


    @Override
    public void render(Graphics2D g) {
        g.drawImage(animation.getImage(), (int)(position.Xcoordinate), (int)(position.Ycoordinate),size,size,null);
        Sprite.drawArray(g, font, name, MapVector2D.createMapVector2DwithCoordinate((int)(position.Xcoordinate), (int)(position.Ycoordinate)-20), 20, 20, 32, 0);
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){
        if(mouseHandler.getButton() == 1){
            System.out.println("Player "+position.Xcoordinate +" , "+position.Ycoordinate);
        }
        if(keyHandler.up.down){
            up = true;
        }
        else {
            up = false;
        }
        if(keyHandler.down.down){
            down = true;
        }
        else {
            down = false;
        }
        if(keyHandler.left.down){
            left = true;
        }
        else {
            left = false;
        }
        if(keyHandler.right.down){
            right = true;
        }
        else {
            right = false;
        }
        if(keyHandler.attack.down){
            attacks = true;
        }
        else {
            attacks = false;
        }
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Player)) {
            return false;
        }
        Player that = (Player) o;
        return getGoldAmount() == that.getGoldAmount();
    }

    public String toString(){
        return "Player name: "+ getName() +
                " \n"+" Player GoldAmount: "+goldAmount;
    }
}

