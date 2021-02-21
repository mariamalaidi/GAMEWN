package com.world.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Font {

    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    private int widthOfTheLetter;
    private int heightOfTheLetter;
    public int width;
    public int height;

    private Font(String file, int width, int height) {
        this.width = width;
        this.height = height;
        FONTSHEET = readFontFromImage(file);
        widthOfTheLetter = FONTSHEET.getWidth() / width;
        heightOfTheLetter = FONTSHEET.getHeight() / height;
        loadFontArrayFromImage();
    }

    public static Font createFont(String file, int width, int height){
        return new Font(file, width, height);
    }


    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        width = i;
        widthOfTheLetter = FONTSHEET.getWidth() / width;
    }

    public void setHeight(int i) {
        height = i;
        heightOfTheLetter = FONTSHEET.getHeight() / height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void loadFontArrayFromImage() {
        spriteArray = new BufferedImage[widthOfTheLetter][heightOfTheLetter];

        for (int x = 0; x < widthOfTheLetter; x++) {
            for (int y = 0; y < heightOfTheLetter; y++) {
                spriteArray[x][y] = getLetterUsingCoordinates(x, y);
            }
        }
    }


    public BufferedImage getLetterUsingCoordinates(int x, int y) {
        BufferedImage img = FONTSHEET.getSubimage(x * width, y * height, width, height);
        return img;
    }

    public BufferedImage getLetterFromImageUsingChar(char letter) {
        int value = letter;
        int x = value % widthOfTheLetter;
        int y = value / widthOfTheLetter;
        return getLetterUsingCoordinates(x, y);
    }

    private BufferedImage readFontFromImage(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File((file)));
            System.out.println(sprite);
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

}