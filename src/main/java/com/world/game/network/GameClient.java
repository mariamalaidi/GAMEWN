package com.world.game.network;
import com.world.game.GamePanel;
import com.world.game.network.packet.Packet;
import com.world.game.network.packet.Packet00Login;
import com.world.game.state.GameStateManger;
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
          //  this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            System.out.println("Server > "+new String(packet.getData()));
  }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookingPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
            default:
            case INVAILID:
                break;
            case LOGIN:
                packet = new Packet00Login(data);
                handleLogin((Packet00Login) packet, address, port);
                break;
            case DISCOUNNET:
          break;

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

    private void handleLogin(Packet00Login packet, InetAddress address, int port) {
        System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUsername()
                + " has joined the game...");

   }


}
