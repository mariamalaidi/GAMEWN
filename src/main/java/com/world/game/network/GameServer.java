
package com.world.game.network;
import com.world.game.GamePanel;
import com.world.game.entity.MultiPlayer;
import com.world.game.graphics.Sprite;
import com.world.game.network.packet.Packet;
import com.world.game.network.packet.Packet00Login;
import com.world.game.state.GameStateManger;
import com.world.game.util.MapVector2D;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class GameServer extends Thread {
    private DatagramSocket socket;
    private GamePanel game;
    private List<MultiPlayer> connectedPlayers;
    private GameStateManger gameStateManger;

    public GameServer(GamePanel game) {
        connectedPlayers = new ArrayList<MultiPlayer>();
        this.game = game;
        this.gameStateManger = game.getGameStateManger();
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();

        }
    }

    public DatagramSocket getSocket(){
        return socket;
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
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
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
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                        + ((Packet00Login) packet).getUsername() + " has connected...");
               MultiPlayer player = new MultiPlayer((Sprite.createSpriteFromImage("src/main/resources/Player.png")), MapVector2D.createMapVector2DwithCoordinate(300, 300), 64,((Packet00Login) packet).getUsername(), address, port);
                gameStateManger.addPlayer(player);
             this.addConnection(player, (Packet00Login) packet);
                break;


        }
    }

    public void addConnection(MultiPlayer player, Packet00Login packet) {
        boolean alreadyConnected = false;
        for(MultiPlayer p: this.connectedPlayers){
            if(player.getName().equalsIgnoreCase(p.getName())){
                if(p.ipAddress == null){
                    p.ipAddress = player.ipAddress;
                }
                if(p.port == -1){
                    p.port = player.port;
                }
                alreadyConnected = true;
            }
            else {
                sendData(packet.getData(),p.ipAddress,p.port);
                packet = new Packet00Login(p.getName());
                sendData(packet.getData(), player.ipAddress, player.port);
            }
            if(alreadyConnected){
                this.connectedPlayers.add(player);
                packet.writeData(this);
            }

        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length,ipAddress, port);
        try {
            this.socket.send(packet);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void sendDataToAllClients(byte[] data) {
        for(MultiPlayer player: connectedPlayers){
            sendData(data, player.ipAddress, player.port);
        }
    }
}
