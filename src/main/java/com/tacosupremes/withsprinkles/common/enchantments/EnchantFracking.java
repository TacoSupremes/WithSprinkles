package com.tacosupremes.withsprinkles.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class EnchantFracking extends ModEnchantment {

	protected EnchantFracking()
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.BREAKABLE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}, "fracking");
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
	
		return stack != null && stack.getItem() instanceof ItemPickaxe;
	}
	 
	 public int getMinEnchantability(int enchantmentLevel)
	 {
	        return 15;
	 }

	   
	  public int getMaxEnchantability(int enchantmentLevel)
	  {
	        return super.getMinEnchantability(enchantmentLevel) + 50;
	  }

}
