package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class ModRecipe implements IRecipe
{

	public ModRecipe(ResourceLocation res)
	{
		GameRegistry.addRecipe(this);
	}

	public ModRecipe(String name)
	{
		this(new ResourceLocation(LibMisc.MODID, name));
	}

}