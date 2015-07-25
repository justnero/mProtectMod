package ru.justnero.minecraft.forge.mprotect.proxy;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import ru.justnero.minecraft.forge.jet.client.GuiManipulator;
import ru.justnero.minecraft.forge.jet.client.gui.IIngameTrigger;
import ru.justnero.minecraft.forge.jet.client.gui.JetIngame;
import ru.justnero.minecraft.forge.mprotect.ProtectManager;
import ru.justnero.minecraft.forge.mprotect.client.ProtectEntity;
import ru.justnero.minecraft.forge.mprotect.client.ProtectRender;
import ru.justnero.minecraft.forge.mprotect.client.IngameRenderer;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
        initRender();
        initTick();
        initKey();
        initJet();
    }

    public void initRender() {
        RenderingRegistry.registerEntityRenderingHandler(ProtectEntity.class,new ProtectRender(FMLClientHandler.instance().getClient()));
    }

    public void initTick() {
        FMLCommonHandler.instance().bus().register(ProtectManager.instance());
    }

    public void initKey() {
        keyDispay = new KeyBinding("key.jet.display",Keyboard.KEY_F12,"key.categories.jet");
        ClientRegistry.registerKeyBinding(keyDispay);
    }

    public void initJet() {
    	initDebugInfo();
    	initIngameRender();
    }

    public void initDebugInfo() {
    	JetIngame.debugInfo.add("mprotect.debug.protects");
    	JetIngame.debugInfo.add("mprotect.debug.set");
    	JetIngame.debugInfo.add("mprotect.debug.add");
    	JetIngame.debugInfo.add("mprotect.debug.delete");
    	JetIngame.debugInfo.add("mprotect.debug.info");
    	JetIngame.debugInfo.add("mprotect.debug.remove");
    	JetIngame.debugInfo.add("mprotect.debug.toggle");
    	List<IIngameTrigger> tmp = new ArrayList<IIngameTrigger>();
    	tmp.add( new IIngameTrigger() {
			@Override
			public String execute() {
				return GameSettings.getKeyDisplayString(keyDispay.getKeyCode());
			}
    	});
    	JetIngame.debugTriggers.put("mprotect.debug.toggle",tmp);
    }

    public void initIngameRender() {
    	GuiManipulator.addIngameRender(new IngameRenderer());
    }

}
