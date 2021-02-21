package com.world.game.tiles;
import com.world.game.graphics.Sprite;
import com.world.game.tiles.blocks.Block;
import com.world.game.tiles.blocks.HoleBlock;
import com.world.game.tiles.blocks.ObjectBlock;
import com.world.game.util.MapVector2D;
import java.awt.*;
import java.util.HashMap;

public class TileMapNormal implements TileMap{
    public static HashMap<String, Block> tileMapBlocks;

    private TileMapNormal(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tileMapBlocks = new HashMap<>();
        String[] block = data.split(",");
        for(int i = 0 ; i < (width * height) ; i++){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if(temp != 0){
                System.out.println("temp normal "+temp);
                if(isTempHole(temp)){
                    tempBlock = HoleBlock.createHoleBlock((sprite.getSprite(((temp - 1) % tileColumns),((temp - 1) / tileColumns) )), MapVector2D.createMapVector2DwithCoordinate( (i % width) * tileWidth,  (i / height) * tileHeight), tileWidth, tileHeight);
                }
                else {
                    tempBlock = ObjectBlock.createObjectBlock((sprite.getSprite(((temp - 1) % tileColumns), ((temp - 1) / tileColumns) )), MapVector2D.createMapVector2DwithCoordinate((i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight);
                }
                tileMapBlocks.put(((i % width)) + "," + ((i/ height)), tempBlock);
            }
        }
    }

    public static TileMapNormal createTileMapNormal(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        return new TileMapNormal(data,sprite,width,height,tileHeight,tileHeight,tileColumns);
    }


    private boolean isTempHole(int temp){
        return temp == 172;
    }

    public void render(Graphics2D graphics2D){
        for(Block block: tileMapBlocks.values()){
            block.render(graphics2D);
        }
    }
}