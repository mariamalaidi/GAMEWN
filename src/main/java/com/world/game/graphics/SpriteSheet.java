package com.world.game.graphics;

import com.world.game.util.MapVector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheet {
    private BufferedImage SPRTIEIMAGE = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    private int widthSprite;
    private int heightSprite;
    public int width;
    public int height;

    public SpriteSheet(String file) throws IOException {
        width = TILE_SIZE;
        height = TILE_SIZE;
        System.out.println("Loading : "+file+" ...");
        SPRTIEIMAGE =loadSprite(file);
    }

    public SpriteSheet(String file, int width, int height){
        this.width = width;
        this.height = height;
        System.out.println("Loading : "+file+" ...");
        widthSprite = SPRTIEIMAGE.getWidth()/width;
        heightSprite = SPRTIEIMAGE.getHeight()/height;
        loadSpriteArray();
    }

    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width){
        this.width = width;
        widthSprite = SPRTIEIMAGE.getWidth() / width;
    }

    public void setHeight(int height){
        this.height= height;
        heightSprite = SPRTIEIMAGE.getHeight()/height;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    private BufferedImage loadSprite(String file) throws IOException {
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }catch (Exception e){
            System.out.println("Error There is no Image");
        }
        return sprite;
    }

    public void loadSpriteArray(){
        spriteArray = new BufferedImage[widthSprite][heightSprite];
        for(int x = 0 ; x < widthSprite ; x++){
            for(int y = 0 ; y < heightSprite ; y++){
                spriteArray[x][y] = getSprite(x, y);
            }
        }
    }

    public BufferedImage getSpriteSheet(){
        return SPRTIEIMAGE;
    }

    public BufferedImage getSprite(int x,int y){
        return SPRTIEIMAGE.getSubimage(x , y ,width,height);
    }

    public BufferedImage[] getSpriteArray(int i){
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2D(int i){
        return spriteArray;
    }
    public static void drawArray(Graphics2D graphics2D, ArrayList<BufferedImage> image, MapVector2D pos, int width, int height, int xOffset, int yOffset){
        float x = pos.x;
        float y = pos.y;

        for(int i = 0 ; i  < image.size() ; i++){
            if(image.get(i) != null){
                graphics2D.drawImage(image.get(i), (int) x, (int) y, width ,height,null);
            }
            x+=xOffset;
            y+=yOffset;
        }
    }
    public static void drawArray(Graphics2D graphics2D, Font font,String word, MapVector2D pos,int width,int height,int xOffset,int yOffset){
        float x = pos.x;
        float y = pos.y;

        for(int i = 0 ; i < word.length() ; i++){
            if(word.charAt(i) != 32){
                graphics2D.drawImage(font.getLetter(word.charAt(i)), (int) x, (int) y, width ,height,null);
            }
            x+=xOffset;
            y+=yOffset;
        }
    }
}
