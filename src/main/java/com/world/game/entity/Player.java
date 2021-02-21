package com.world.game.entity;
import com.world.game.GamePanel;
import com.world.game.graphics.Font;
import com.world.game.graphics.Sprite;
import com.world.game.state.PlayState;
import com.world.game.util.KeyHandler;
import com.world.game.util.MapVector2D;
import com.world.game.util.MouseHandler;
import java.awt.*;

public class Player extends  Entity{
    private Font font;
    private final int goldAmount = 20;

    public static String name ="";

    public Player(Sprite sprite, MapVector2D origin, int size) {
        super(sprite, origin, size);
        bounds.setWidth(42);
        bounds.setXOffset(12);
        bounds.setYOffset(40);
        bounds.setHeight(20);
    }

    public static Player createPlayer(Sprite sprite, MapVector2D origin, int size){
        return new Player(sprite, origin, size);
    }

    public void setFont(Font font) {
        this.font = font;
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
                if(durationY < 0){
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

    public void update() {
        super.update();
        if (!fallen) {
            move();
            if (!tileCollision.collisionTile(durationX, 0)) {
                PlayState.map.Xcoordinate += durationX;
                position.Xcoordinate += durationX;
            }
            if (!tileCollision.collisionTile(0, durationY)) {
                PlayState.map.Ycoordinate += durationY;
                position.Ycoordinate += durationY;
            }
        }
        else{
            if(animation.hasPlayedOnce()){
                resetPosition();
                fallen = false;
            }
        }
    }



    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){
        if(MouseHandler.getButton() == 1){
            System.out.println("Player "+position.Xcoordinate +" , "+position.Ycoordinate);
        }
        if(!fallen) {
            up = keyHandler.up.down;
            down = keyHandler.down.down;
            left = keyHandler.left.down;
            right = keyHandler.right.down;
            attacks = keyHandler.attack.down;
        }
        else {
            up = false;
            down = false;
            right = false;
            left = false;
        }
    }

    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Player)) {
            return false;
        }
        Player that = (Player) object;
        return getGoldAmount() == that.getGoldAmount();
    }

    private void resetPosition() {
        position.Xcoordinate = GamePanel.widthOfGameArea/ 2 - 32;
        PlayState.map.Xcoordinate = 0 ;
        position.Ycoordinate = GamePanel.heightOfGameArea/ 2 - 32;
        PlayState.map.Ycoordinate = 0 ;
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(245, 15, 15));
        g.drawRect( (int)(position.getGameWorldCoordinates().Xcoordinate + bounds.getxOffset()), (int)(position.getGameWorldCoordinates().Ycoordinate + bounds.getyOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g.drawImage(animation.getImage(), (int)(position.getGameWorldCoordinates().Xcoordinate), (int)(position.getGameWorldCoordinates().Ycoordinate),size,size,null);
        Sprite.drawArray(g, font, name, MapVector2D.createMapVector2DwithCoordinate( (int)(position.getGameWorldCoordinates().Xcoordinate), (int)(position.getGameWorldCoordinates().Ycoordinate) - 20), 20, 20, 32, 0);
    }


    public String toString(){
        return "Player name: "+ getName() +
                " \n"+" Player GoldAmount: "+goldAmount;
    }
}

