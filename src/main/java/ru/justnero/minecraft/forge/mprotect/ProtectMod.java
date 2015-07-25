package ru.justnero.minecraft.forge.mprotect;

import ru.justnero.minecraft.forge.mprotect.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="mProtect", name="mProtect Mod", version="3.0", dependencies="required-after:jetMinecraft;")
public class ProtectMod {

	@Instance(value = "mProtect")
    public static ProtectMod instance;

    @SidedProxy(clientSide="ru.justnero.minecraft.forge.mprotect.proxy.ClientProxy", serverSide="ru.justnero.minecraft.forge.mprotect.proxy.CommonProxy")
    public static CommonProxy proxy;

	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit();
    }

}
