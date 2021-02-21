package com.world.game.util;

import com.world.game.entity.Entity;
import com.world.game.tiles.TileMapNormal;
import com.world.game.tiles.TileMapObjects;
import com.world.game.tiles.blocks.Block;
import com.world.game.tiles.blocks.HoleBlock;

public class TileCollision {
    private final Entity entity;

    public TileCollision(Entity entity) {
        this.entity = entity;
    }

    public boolean collisionTile(float areaX, float areaY){
        for(int c = 0 ; c < 4 ; c++){
            int xTile = (int)( ((int)(entity.getBounds().getPosition().Xcoordinate + areaX) + (c %2 ) * entity.getBounds().getWidth() + entity.getBounds().getxOffset()) / 64);
            int YTile = (int)((entity.getBounds().getPosition().Ycoordinate + areaY) + ((c /2 )) * entity.getBounds().getHeight() + entity.getBounds().getyOffset()) / 64;
            if(TileMapNormal.tileMapBlocks.containsKey((xTile) +"," + (YTile))){
                Block block = TileMapNormal.tileMapBlocks.get((xTile)+","+(YTile));
                if(isHoleBlock(block)){
                    return collisionHole(areaX, areaY, xTile, YTile, block);
                }
                return block.update(entity.getBounds());   }
        }
        return false;
    }

    private boolean isHoleBlock(Block block){
        return (block instanceof HoleBlock);
    }
    private boolean collisionHole(float areaX, float areaY, float XTile, float YTile, Block block){
        int nextXt = (int) ((((entity.getBounds().getPosition().Xcoordinate + areaX) + entity.getBounds().getxOffset()) / 64) + entity.getBounds().getWidth() /64);
        int nextYt = (int) ((((entity.getBounds().getPosition().Ycoordinate + areaY) + entity.getBounds().getyOffset())/64)+entity.getBounds().getHeight() /64);
        if(block.isInside(entity.getBounds())){
            entity.setFallen(true);
            return false;
        }else if(isNextXTileEqualYTile(nextXt,YTile) || isNextTileEqualXTile(nextXt ,XTile) || isNextYTileEqualLessYTile(nextYt,YTile) ||isNextXTileEqualLessXTile(nextXt , XTile)){
            if(TileMapObjects.tileMapObjects.containsKey((nextXt) + ","+(nextYt))){
                if(entity.getBounds().getPosition().Xcoordinate > block.getPosition().Xcoordinate){
                    entity.setFallen(true);
                }
                return false;
            }
        }
        entity.setFallen(false);
        return false;
    }

    private boolean isNextXTileEqualYTile(int nextXt, float YTile){
        return (nextXt == YTile + 1);
    }

    private boolean isNextTileEqualXTile(int nextXt, double XTile){
        return (nextXt == XTile + 1);
    }

    private boolean isNextYTileEqualLessYTile(int nextYt, double YTile){
        return  (nextYt == YTile -1);
    }

    private boolean isNextXTileEqualLessXTile(int nextXt, double XTile){
        return (nextXt == XTile - 1);
    }
}

