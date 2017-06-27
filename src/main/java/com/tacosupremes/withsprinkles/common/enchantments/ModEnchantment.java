package com.tacosupremes.withsprinkles.common.enchantments;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class ModEnchantment extends Enchantment {

	protected ModEnchantment(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots, String name) {
		super(rarityIn, typeIn, slots);

		this.setName(LibMisc.MODID + ":" + name);
		
		Enchantment.REGISTRY.register(75 + ModEnchantments.enchants.size(), new ResourceLocation(name), this);
		
		ModEnchantments.enchants.add(this);
		
	}
	

}
