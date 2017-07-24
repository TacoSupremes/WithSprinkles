package com.tacosupremes.withsprinkles.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class EnchantFiery extends ModEnchantment
{

	protected EnchantFiery()
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND }, "fiery");
	}

	@Override
	public boolean canApply(ItemStack stack)
	{

		return stack != null && (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemSpade || stack.getItem() instanceof ItemAxe);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{

		return canApply(stack);
	}

	@Override
	public boolean canApplyTogether(Enchantment ench)
	{
		return super.canApplyTogether(ench) && ench != Enchantments.SILK_TOUCH && ench != Enchantments.FORTUNE;
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
