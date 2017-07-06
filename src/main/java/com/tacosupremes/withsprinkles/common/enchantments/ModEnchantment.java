package com.tacosupremes.withsprinkles.common.enchantments;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class ModEnchantment extends Enchantment {

	protected ModEnchantment(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots, String name) {
		super(rarityIn, typeIn, slots);

		this.setName(LibMisc.MODID + ":" + name);
		this.setRegistryName(LibMisc.MODID, name);
		ProxyRegistry.register(this);
		ModEnchantments.enchants.add(this);
		
	}
	

}
