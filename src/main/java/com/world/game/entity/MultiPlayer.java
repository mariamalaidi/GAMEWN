package com.world.game.entity;

import com.world.game.graphics.Sprite;
import com.world.game.util.MapVector2D;

import java.net.InetAddress;

public class MultiPlayer extends Player{
    public InetAddress ipAddress;
    public int port;

    public MultiPlayer(Sprite sprite, MapVector2D origin, int size,String username, InetAddress ipAddress, int port) {
        super(sprite, origin, size);
        this.ipAddress = ipAddress;
        this.port = port;
        this.name = username;
    }
    public MultiPlayer(Sprite sprite, MapVector2D origin, int size,String username){
        super(sprite, origin, size);
    }
}
