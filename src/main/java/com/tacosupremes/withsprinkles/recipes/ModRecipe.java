package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.common.utils.RecipeHandler;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class ModRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	public ModRecipe(ResourceLocation res) {
		RecipeHandler.addRecipe(res, this);
	}
	
}