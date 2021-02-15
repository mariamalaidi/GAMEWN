package com.world.game.network.packet;

import com.world.game.network.GameClient;
import com.world.game.network.GameServer;

public abstract class Packet {
    public enum  PacketTypes {
        INVAILID(-1), LOGIN(00), DISCOUNNET(01);
        private final int packetId;
        PacketTypes(int packetId){
            this.packetId = packetId;
        }
        public int getId(){
            return packetId;
        }
    }
    public byte packetId;
    public Packet(int packetId){
        this.packetId = (byte)packetId;
    }

    public abstract void writeData(GameClient client);
    public abstract void writeData(GameServer Server);

    public String readData(byte[] data){
        String message = new String(data).trim();
        return message.substring(2);
    }
    public static PacketTypes lookingPacket(int id){
        for(PacketTypes p : PacketTypes.values()){
            if(p.getId() == id){
                return  p;
            }
        }
        return  PacketTypes.INVAILID;
    }

    public static PacketTypes lookingPacket(String packetId){
       try {
           return lookingPacket(Integer.parseInt(packetId));
       }catch (Exception e){
           return PacketTypes.INVAILID;
       }
    }
    public abstract byte[] getData();
}
