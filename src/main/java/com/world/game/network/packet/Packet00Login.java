package com.world.game.network.packet;

import com.world.game.network.GameClient;
import com.world.game.network.GameServer;

public class Packet00Login extends Packet{
    private String username;
    public Packet00Login(byte[] data){
        super(00);
        this.username = readData(data);
    }

    public Packet00Login(String username){
        super(00);
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer gameServer) {
        gameServer.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        return ("00" + this.username).getBytes();
    }
}
