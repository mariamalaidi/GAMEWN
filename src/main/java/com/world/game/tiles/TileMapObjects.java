package com.world.game.tiles;
import com.world.game.graphics.Sprite;
import com.world.game.tiles.blocks.*;
import com.world.game.util.MapVector2D;
import java.awt.*;
import java.util.HashMap;

public class TileMapObjects implements TileMap {
    public static HashMap<String, Block> tileMapObjects;
    public static int width;
    public static int height;


    private TileMapObjects(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tileMapObjects = new HashMap<>();
        TileMapObjects.width = width;
        TileMapObjects.height = height;
        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                System.out.println("Temp " + temp);
                if (temp == 254)
                    tempBlock = ChestBlock.createChestBlock((sprite.getSprite(((temp - 1) % tileColumns), ((temp - 1) / tileColumns))), MapVector2D.createMapVector2DwithCoordinate((i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight);
                else if (temp == 263) {
                    tempBlock = LightBlock.createLightBlock((sprite.getSprite(((temp - 1) % tileColumns), ((temp - 1) / tileColumns))), MapVector2D.createMapVector2DwithCoordinate((i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight);
                } else if (temp == 170) {
                    tempBlock = DoorBlock.createDoorBlock((sprite.getSprite(((temp - 1) % tileColumns), ((temp - 1) / tileColumns))), MapVector2D.createMapVector2DwithCoordinate((i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight);
                } else if (temp == 171) {
                    tempBlock = SpecialDoorBlock.createSpecialDoorBlock((sprite.getSprite(((temp - 1) % tileColumns), ((temp - 1) / tileColumns))), MapVector2D.createMapVector2DwithCoordinate((i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight);

                } else if (temp == 262) {
                    tempBlock = ShopBlock.createShopBlock((sprite.getSprite(((temp - 1) % tileColumns), ((temp - 1) / tileColumns))), MapVector2D.createMapVector2DwithCoordinate((i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight);
                } else {
                    tempBlock = ObjectBlock.createObjectBlock((sprite.getSprite(((temp - 1) % tileColumns), ((temp - 1) / tileColumns))), MapVector2D.createMapVector2DwithCoordinate((i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight);
                }
                tileMapObjects.put(((i % width)) + "," + ((i / height)), tempBlock);

            }
        }
    }

    public static TileMapObjects createTileMapObject(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        return new TileMapObjects(data, sprite, width, height, tileWidth, tileHeight, tileColumns);
    }


    public void render(Graphics2D graphics2D) {
        for (Block block : tileMapObjects.values()) {
            block.render(graphics2D);
        }
    }
}
