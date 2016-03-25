package com.tacosupremes.withsprinkles.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

public class ModEnchantments {
	
	public static Enchantment exchange;
	
	
	public static void preInit(){
		
		exchange = new EnchantExchange();
		
		Enchantment.enchantmentRegistry.register(75, new ResourceLocation("exchange"), exchange);

	}

}
