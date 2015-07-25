package ru.justnero.minecraft.forge.mprotect.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.settings.KeyBinding;
import ru.justnero.minecraft.forge.mprotect.ProtectMod;

import ru.justnero.minecraft.forge.mprotect.network.*;

public class CommonProxy {

    private static final String _chanelName = "mProtect";

    public KeyBinding keyDispay;
    public SimpleNetworkWrapper channel;

    public enum Messages {
        HANDSHAKE,
        CREATE,
        UPDATE,
        REMOVE,
        CLEAR
    }

    public void preInit() {
        initNetworking();
        initMessages();
    }

    public void init() {

    }

    public void postInit() {

    }

    private void initNetworking() {
        channel = NetworkRegistry.INSTANCE.newSimpleChannel(_chanelName);
    }

    private void initMessages() {
        channel.registerMessage(HandshakeMessage.Handler.class,HandshakeMessage.class,0,Side.CLIENT);
        channel.registerMessage(   CreateMessage.Handler.class,   CreateMessage.class,1,Side.CLIENT);
        channel.registerMessage(   UpdateMessage.Handler.class,   UpdateMessage.class,2,Side.CLIENT);
        channel.registerMessage(   RemoveMessage.Handler.class,   RemoveMessage.class,3,Side.CLIENT);
        channel.registerMessage(    ClearMessage.Handler.class,    ClearMessage.class,4,Side.CLIENT);
    }

    public static void sendTo(Message message, String player) {
        ProtectMod.proxy.channel.sendTo(message,FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(player));
    }

}
