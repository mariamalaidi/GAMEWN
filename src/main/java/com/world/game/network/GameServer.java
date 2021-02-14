package com.world.game.network;

import com.world.game.GameLauncher;

import java.io.IOException;
import java.net.*;

public class GameServer {
    private DatagramSocket socket;
    private GameLauncher game;

    public GameServer(GameLauncher game, String ipAddress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try{
                socket.receive(packet);
            }
            catch (IOException exception){
                exception.printStackTrace();
            }
            System.out.println("Server > "+new String(packet.getData()));
        }
    }

    public void sendData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
    }
}
