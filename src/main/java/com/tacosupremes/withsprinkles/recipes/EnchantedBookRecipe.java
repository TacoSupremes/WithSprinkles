package com.tacosupremes.withsprinkles.recipes;

import java.util.Random;

import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.utils.EnchantUtils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnchantedBookRecipe extends ModRecipe 
{

	public EnchantedBookRecipe() 
	{
		super("enchantBook");
		
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		
		boolean hasLeather = false;
		
		int paper = 0;
		int oldPaper = 0;
		
		for(int x = 0; x < inv.getWidth(); x++)
		{
			
			for(int y = 0; y < inv.getHeight(); y++)
			{
				
				ItemStack is = inv.getStackInRowAndColumn(y, x);
				
				if(is.isEmpty())
					continue;
				
				if(is.getItem() == Items.LEATHER)
				{
					
					if(hasLeather)
						return false;
					
					hasLeather = true;
					continue;
				}
				
				if(is.getItem() == ModItems.oldPaper)
				{
					paper++;
					oldPaper++;
				}
				
				if(is.getItem() == Items.PAPER)
					paper++;
				
			}
		}
		
		return oldPaper > 0 && paper == 3 && hasLeather;
	}

	Random rand = new Random();
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
	
		ItemStack is = new ItemStack(Items.ENCHANTED_BOOK);
		
		int oldPaper = 0;
		
		for(int x = 0; x < inv.getWidth(); x++)
		{
			
			for(int y = 0; y < inv.getHeight(); y++)
			{
				
				ItemStack invs = inv.getStackInRowAndColumn(y, x);
				
				if(!invs.isEmpty() && invs.getItem() == ModItems.oldPaper)
					oldPaper++;
			}
			
		}
		
		//1 oldPaper gives Common to Rare, 2 gives Uncommon to Rare, 3 gives treasure enchant and Very Rare
		Rarity r  = Rarity.values()[ Math.min(oldPaper - 1 + rand.nextInt(2), Rarity.values().length-1)];
		
		Enchantment e = EnchantUtils.randEnchantmentTier(rand, is, oldPaper == 3, oldPaper == 3 ? Rarity.VERY_RARE : r);

		ItemEnchantedBook.addEnchantment(is, new EnchantmentData(e, e.getMaxLevel()));
		
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
		return new ItemStack(Items.ENCHANTED_BOOK);
	}

	
	
}
