package com.tacosupremes.withsprinkles.common.enchantments;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class EnchantExchange extends ModEnchantment
{

	protected EnchantExchange()
	{
		super(Rarity.COMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND }, "exchange");
	}

	@Override
	public boolean canApply(ItemStack stack)
	{

		return stack != null && super.canApply(stack) && (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemSpade || stack.getItem() instanceof ItemAxe);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{

		return canApply(stack);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return 5 + (enchantmentLevel - 1) * 5;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

}
