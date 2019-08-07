package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.RecipeHandler;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class ModRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{

	public ModRecipe(ResourceLocation res)
	{
		RecipeHandler.addRecipe(res, this);
	}

	public ModRecipe(String name)
	{
		this(new ResourceLocation(LibMisc.MODID, name));
	}

	@Override
	public boolean isHidden()
	{
		return true;
	}


}