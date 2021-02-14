
package com.world.game.network;
import com.world.game.GameLauncher;
import com.world.game.GamePanel;

import java.io.IOException;
import java.net.*;

public class GameServer extends Thread {
    private DatagramSocket socket;
    private GamePanel game;

    public GameServer(GamePanel game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run()  {
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try{
                socket.receive(packet);
            }
            catch (IOException exception){
                exception.printStackTrace();
            }
            String message = new String(packet.getData());
            if(message.trim().equalsIgnoreCase("Ping")){
                System.out.println("Returning pong");
                try {
                    sendData("Pong".getBytes(), packet.getAddress(), packet.getPort());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
            System.out.println("Client [ " + packet.getAddress()+" :"+packet.getPort());

       }
    }
    public void sendData(byte[] data, InetAddress ipAddress, int port) throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length,ipAddress, port);
        this.socket.send(packet);
    }
}
