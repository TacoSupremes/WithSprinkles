package com.tacosupremes.withsprinkles.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class EnchantExchange extends Enchantment {

	protected EnchantExchange() {
		super(Rarity.UNCOMMON, EnumEnchantmentType.BREAKABLE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		 this.setName("exchange");
	}

	@Override
	public boolean canApply(ItemStack stack) {
	
		return stack != null && (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemSpade || stack.getItem() instanceof ItemAxe);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		
		return canApply(stack);
	}

	
	
}
