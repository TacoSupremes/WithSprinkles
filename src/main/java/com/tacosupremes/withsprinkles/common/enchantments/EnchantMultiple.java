package com.tacosupremes.withsprinkles.common.enchantments;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantMultiple extends ModEnchantment {

	protected EnchantMultiple() {
		super(Rarity.RARE, EnumEnchantmentType.ALL, EntityEquipmentSlot.values(), "multiple");
		
	}

	@Override
	public int getMaxLevel() 
	{	
		  return 2;
	}
	
	public int getMinEnchantability(int enchantmentLevel)
	{
	      return enchantmentLevel * 25;
	}

	public int getMaxEnchantability(int enchantmentLevel)
	{
	      return this.getMinEnchantability(enchantmentLevel) + 50;
	}
	
	
	

}
