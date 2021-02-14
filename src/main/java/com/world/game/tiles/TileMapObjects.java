package com.world.game.tiles;

import com.world.game.graphics.Sprite;
import com.world.game.tiles.blocks.Block;
import com.world.game.tiles.blocks.HoleBlock;
import com.world.game.tiles.blocks.ObjectBlock;
import com.world.game.util.MapVector2D;
import java.awt.*;
import java.util.HashMap;

public class TileMapObjects implements TileMap{
    private int tileWidth;
    private  int tileHeight;
    public static HashMap<String, Block>tileMapObjects;
    public static int width;
    public static int height;


    public TileMapObjects(String data, Sprite sprite,int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tileMapObjects = new HashMap<String, Block>();

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        TileMapObjects.width = width;
        TileMapObjects.height =  height;

        String[] block = data.split(",");
        for(int i = 0 ; i < (width * height) ;i++){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if(temp != 0){
                if(temp == 172){
                    tempBlock = HoleBlock.createHoleBlock((sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns) )), MapVector2D.createMapVector2DwithCoordinate((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                }
                else {
                    tempBlock = ObjectBlock.createObjectBlock((sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns) )), MapVector2D.createMapVector2DwithCoordinate((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                }
                tileMapObjects.put(String.valueOf((int)(i % width)) + "," + String.valueOf((int)(i/ height)), tempBlock);

            }
        }
    }
    public void render(Graphics2D graphics2D){
        for(Block block: tileMapObjects.values()){
            block.render(graphics2D);
        }
    }
}
