package com.world.game.state;

import java.net.InetAddress;

public class MultiplayerStateManger extends GameStateManger{
    public static InetAddress ipAddress;
    public static int port;
    public static String  name;
    public MultiplayerStateManger(String name, InetAddress ip, int port){
       this.name = name;
        this.ipAddress = ip;
        this.port = port;
    }

    public String getName(){
        return name;
    }
}
