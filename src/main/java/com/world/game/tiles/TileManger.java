package com.world.game.tiles;

import com.world.game.graphics.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.awt.Graphics2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TileManger {
    private int BLOCKSIZE = 64;
    public static  ArrayList<TileMap> tileMapArray;

    public static  TileManger createTileManger(){
        return new TileManger();
    }

    private TileManger(){
        tileMapArray = new ArrayList<TileMap>();
        addTileMap( 64  , 64);
    }
    public int getBLOCKSIZE(){
        return BLOCKSIZE;
    }

    private void addTileMap(int blockWidth, int blockHeight) {

        String imagePath;
        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileCount;
        int tileColumns;
        int layers = 0;
        Sprite sprite;
        String[] data = new String[10];
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(("src/main/resources/main.xml")));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;
            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            tileColumns =  Integer.parseInt(eElement.getAttribute("columns"));

            sprite = Sprite.createSpriteFromImageWithSize("src/main/resources/dungeon_tiles_formatted_v2.png", tileWidth, tileHeight);
            list = doc.getElementsByTagName("layer");
            layers = list.getLength();
            System.out.println(list.getLength()+" layers");
            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                eElement = (Element) node;
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
                if(i <= 0) {
                    tileMapArray.add(TileMapNormal.createTileMapNormal(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                } else {
                    tileMapArray.add(TileMapObjects.createTileMapObject(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }

            }

        } catch (Exception e) {
            System.out.println("ERROR - TILEMANAGER: can not read tilemap:");
            e.printStackTrace();
            System.exit(0);
        }


    }

    public void render(Graphics2D graphics2D){
        for(int i = 0 ; i < tileMapArray.size() ; i++){
            tileMapArray.get(i).render(graphics2D);
        }
    }
}
