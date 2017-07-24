package com.tacosupremes.withsprinkles.common.utils;

import com.tacosupremes.withsprinkles.WithSprinkles;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientStuff
{

	@SubscribeEvent
	public static void onRegister(ModelRegistryEvent event)
	{

		WithSprinkles.proxy.registerRenders();
	}

}
