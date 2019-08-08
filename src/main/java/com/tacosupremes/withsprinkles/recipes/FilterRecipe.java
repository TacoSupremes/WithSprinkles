package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.common.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class FilterRecipe extends ModRecipe
{

	public FilterRecipe()
	{
		super("filter");
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		boolean filter = false;
		int c = 0;
		boolean hasNBT = false;
			
		ItemStack[] isl = new ItemStack[inv.getSizeInventory()];
		
		for (int x = 0; x < inv.getWidth(); x++)
		{
			for (int y = 0; y < inv.getHeight(); y++)
			{
				ItemStack is = inv.getStackInRowAndColumn(y, x);
				
				if(is.isEmpty())
					continue;
				
				for(int i = 0; i < c; i++)
				{
					if(isl[i].isItemEqual(is))
						return false;
				}
			
				isl[c] = is.copy();
				
				c++;
				
				if(is.getItem() == ModItems.filter)
				{
					if(filter)
						return false;
					
					filter = true;
					hasNBT = is.hasTagCompound();
				}	
			}		
		}
		
		return (c == 1 && hasNBT) || (c > 1 && filter && !hasNBT);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		NBTTagList var2 = new NBTTagList();

	
		int c = 0;
		
		for (int x = 0; x < inv.getWidth(); x++)
		{
			for (int y = 0; y < inv.getHeight(); y++)
			{
				ItemStack is = inv.getStackInRowAndColumn(y, x);
				
				if(is.isEmpty())
					continue;
				
		
				if (is != null && !is.isEmpty() && is.getItem() != ModItems.filter)
				{
					NBTTagCompound var4 = new NBTTagCompound();
					is.writeToNBT(var4);
					var2.appendTag(var4);
					c++;
				}
				
			}		
		}
		
		ItemStack is = new ItemStack(ModItems.filter);
				
		if(c > 0)
		{
			is.setTagCompound(new NBTTagCompound());
		
			is.getTagCompound().setTag("Items", var2);
		}
		
		return is;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		return width >= 2 && height >= 2;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(ModItems.filter);
	}

}
