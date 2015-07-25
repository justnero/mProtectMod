package ru.justnero.minecraft.forge.mprotect.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import ru.justnero.minecraft.forge.mprotect.Cuboid;
import ru.justnero.minecraft.forge.mprotect.Point3D;
import ru.justnero.minecraft.forge.mprotect.Protect;
import ru.justnero.minecraft.forge.mprotect.ProtectManager;
import static java.lang.Math.round;

public class UpdateMessage extends Message {

    private Map<String,Protect> protects;

    public UpdateMessage() {}

    public UpdateMessage(Map<String,Protect> protects) {
        this.protects = new HashMap<String,Protect>();
        this.protects.putAll(protects);
    }

    @Override
    public void fromBytes(ByteBuf bytes) {
        protects = new HashMap<String,Protect>();
        long count = bytes.readLong();
        for(long i=0;i<count;i++) {
            int len = bytes.readInt();
            char charArray[] = new char[len];
            for(int j=0;j<len;j++) {
                charArray[j] = bytes.readChar();
            }
            String pName = String.valueOf(charArray);
            Cuboid pCuboid = new Cuboid(
                    bytes.readInt(),bytes.readInt(),bytes.readInt(), // X Y Z
                    bytes.readInt(),bytes.readInt(),bytes.readInt()  // X Y Z
            );
            int pPriority = bytes.readInt();
            boolean pCanBuild = bytes.readBoolean();
            boolean pCanUse = bytes.readBoolean();
            boolean pIsOwner = bytes.readBoolean();
            boolean pIsMember = bytes.readBoolean();
            protects.put(pName,new Protect(pName,pCuboid,pPriority,pCanBuild,pCanUse,pIsOwner,pIsMember));
        }
    }

    @Override
    public void toBytes(ByteBuf bytes) {
        bytes.writeLong(protects.size());
        for(Protect protect : protects.values()) {
            bytes.writeInt(protect.getName().length());
            for(char c : protect.getName().toCharArray()) {
                bytes.writeChar(c);
            }
            Point3D p1 = protect.getCuboid().getPoint(1);
            Point3D p2 = protect.getCuboid().getPoint(2);
            bytes.writeInt((int) round(p1.x()));
            bytes.writeInt((int) round(p1.y()));
            bytes.writeInt((int) round(p1.z()));
            bytes.writeInt((int) round(p2.x()));
            bytes.writeInt((int) round(p2.y()));
            bytes.writeInt((int) round(p2.z()));
            bytes.writeInt(protect.getPriority());
            bytes.writeBoolean(protect.canBuild());
            bytes.writeBoolean(protect.canUse());
            bytes.writeBoolean(protect.isOwner());
            bytes.writeBoolean(protect.isMember());
        }
    }

    public static class Handler implements IMessageHandler<UpdateMessage,IMessage> {
        @Override
        public IMessage onMessage(UpdateMessage message,MessageContext ctx) {
        	FMLLog.info("Update message size: "+message.protects.size());
            ProtectManager.instance().put(message.protects,true);
            return null;
        }
    }

}
