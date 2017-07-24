package com.tacosupremes.withsprinkles.proxy;

import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{

	public void registerRenders()
	{

	}

	public boolean isShiftDown()
	{

		return false;

	}

	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(ProxyRegistry.class);

	}

}
