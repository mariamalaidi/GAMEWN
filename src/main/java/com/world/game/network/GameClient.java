package com.world.game.network;

import com.world.game.GameLauncher;
import com.world.game.GamePanel;

import java.io.IOException;
import java.net.*;

public class GameClient  extends Thread{
    private InetAddress ipAddress;
    private DatagramSocket socket;
    private GamePanel game;

    public GameClient(GamePanel game, String ipAddress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException | UnknownHostException e) {
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

    public void sendData(byte[] data)  {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
