package com.world.game.tiles;

import com.world.game.graphics.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.util.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TileManger {
    public static  ArrayList<TileMap> tilesOfTheMap;

    private TileManger(String path){
        tilesOfTheMap = new ArrayList<TileMap>();
        addTileMap(path, 64  , 64);
    }

    public static  TileManger createTileManger(String path){
        return new TileManger(path);
    }

    public void render(Graphics2D graphics2D){
        for (TileMap tileMap : tilesOfTheMap) {
            tileMap.render(graphics2D);
        }
    }

    private void addTileMap(String path, int blockWidth, int blockHeight) {
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
            Document doc = builder.parse(new File((getClass().getClassLoader().getResource("src/main/resources/main.xml")).toURI()));
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
            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                eElement = (Element) node;
                if (i <= 0) {
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                }
                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
                if(i >= 1) {
                    tilesOfTheMap.add(new TileMapNormal(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                } else {
                    tilesOfTheMap.add(new TileMapObjects(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }

            }
        } catch (Exception e) {
            System.out.println("ERROR - TILEMANAGER: can not read tilemap:");
            e.printStackTrace();
            System.exit(0);
        }
    }

}
