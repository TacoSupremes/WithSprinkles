package com.tacosupremes.withsprinkles.common.enchantments;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class EnchantFelling extends ModEnchantment
{

	protected EnchantFelling()
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND }, "felling");
	}

	@Override
	public boolean canApply(ItemStack stack)
	{
		return stack != null && super.canApply(stack) && stack.getItem() instanceof ItemAxe;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return canApply(stack);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return 15;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}
	
}
