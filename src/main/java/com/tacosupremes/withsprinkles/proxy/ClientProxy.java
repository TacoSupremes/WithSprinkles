package com.tacosupremes.withsprinkles.proxy;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.items.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
	}

	@Override
	public boolean isShiftDown() {
		
		return Minecraft.getMinecraft().currentScreen != null ? Minecraft.getMinecraft().currentScreen.isShiftKeyDown() : false;
		
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	
	}
	
	
}
