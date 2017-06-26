package com.tacosupremes.withsprinkles.common.enchantments;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class EnchantFiery extends Enchantment {

	protected EnchantFiery() {
		super(Rarity.UNCOMMON, EnumEnchantmentType.BREAKABLE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		 this.setName(LibMisc.MODID + ":" + "fiery");
	}

	@Override
	public boolean canApply(ItemStack stack) {
	
		return stack != null && (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemSpade || stack.getItem() instanceof ItemAxe);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		
		return canApply(stack);
	}
	
	 public boolean canApplyTogether(Enchantment ench)
	    {
	        return super.canApplyTogether(ench) && ench != Enchantments.SILK_TOUCH && ench != Enchantments.FORTUNE;
	    }

}