package com.tacosupremes.withsprinkles.common.enchantments;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

public class ModEnchantments {
	
	public static Enchantment exchange;
	
	public static Enchantment fiery;
	
	public static Enchantment multiple;
	
	public static Enchantment felling;
	
	public static Enchantment fracking;
	
	public static List<Enchantment> enchants = new ArrayList<Enchantment>();

	public static void preInit(){
		
		exchange = new EnchantExchange();
		
		fiery = new EnchantFiery();
		
		multiple = new EnchantMultiple();
		
		felling = new EnchantFelling();
		
		fracking = new EnchantFracking();
		
	}

}
