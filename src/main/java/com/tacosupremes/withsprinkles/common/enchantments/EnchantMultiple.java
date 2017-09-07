package com.tacosupremes.withsprinkles.common.enchantments;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantMultiple extends ModEnchantment
{

	protected EnchantMultiple()
	{
		super(Rarity.RARE, EnumEnchantmentType.ALL, EntityEquipmentSlot.values(), "multiple");

	}

	@Override
	public int getMaxLevel()
	{
		return 2;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return enchantmentLevel * 25;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + 50;
	}

	@Override
	public boolean canApply(ItemStack stack)
	{
		return super.canApply(stack) && stack.hasTagCompound() ? stack.getTagCompound().getInteger("MULTIPLEMODE") == 0 : true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return super.canApply(stack) && stack.hasTagCompound() ? !stack.getTagCompound().hasKey("MULTIPLEMODE") : true;
	}

}
