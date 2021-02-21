package com.world.game.graphics;
import com.world.game.util.MapVector2D;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite {
    private final BufferedImage SPRTIEIMAGE;
    private BufferedImage[][] spriteArray;
    private int widthSprite;
    private int heightSprite;

    public int width;
    public int height;

    private Sprite(String file)  {
        int TILE_SIZE = 32;
        width = TILE_SIZE;
        height = TILE_SIZE;
        SPRTIEIMAGE = loadSprite(file);
        widthSprite = SPRTIEIMAGE.getWidth()/width;
        heightSprite = SPRTIEIMAGE.getHeight()/height;
        loadSpriteArray();
    }

    private Sprite(String file, int width, int height)  {
        this.width = width;
        this.height = height;
        SPRTIEIMAGE = loadSprite(file);
        widthSprite = SPRTIEIMAGE.getWidth()/width;
        heightSprite = SPRTIEIMAGE.getHeight()/height;
        loadSpriteArray();
    }

    public static Sprite createSpriteFromImage(String file){
        return new Sprite(file);
    }

    public static  Sprite createSpriteFromImageWithSize(String file, int width, int height){
        return new Sprite(file, width, height);
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

    public void loadSpriteArray(){
        spriteArray = new BufferedImage[heightSprite][widthSprite];
        for(int y = 0 ; y < heightSprite ; y++){
            for(int x = 0 ; x < widthSprite ; x++){
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }


    public BufferedImage getSprite(int x,int y){

        return SPRTIEIMAGE.getSubimage(x * width , y * height ,width,height);
    }

    public BufferedImage[] getSpriteArray(int i){
        return spriteArray[i];
    }

    public static void drawArray(Graphics2D graphics2D, Font font, String word, MapVector2D pos, int width, int height, int xOffset, int yOffset){
        float x = pos.Xcoordinate;
        float y = pos.Ycoordinate;

        for(int i = 0 ; i < word.length() ; i++){
            if(word.charAt(i) != 32){
                graphics2D.drawImage(font.getLetterFromImageUsingChar(word.charAt(i)), (int) x, (int) y, width ,height,null);
            }
            x+=xOffset;
            y+=yOffset;
        }
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(new File((file)));
        }catch (Exception e){
            System.out.println("Error There is no Image");
        }
        return sprite;
    }
}
