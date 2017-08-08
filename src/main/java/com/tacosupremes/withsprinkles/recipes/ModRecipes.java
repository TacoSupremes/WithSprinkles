package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes
{

	public static void preInit()
	{

		EnchantedBookRecipe r;
		
		if(WithSprinkles.config.isItemEnabled(ModItems.oldPaper))
			r = new EnchantedBookRecipe();		

		addShapedRecipe(new ItemStack(ModItems.refillGem), "RGR", "GEG", "RGR", 'R', new ItemStack(Items.REDSTONE), 'G', new ItemStack(Items.GLOWSTONE_DUST), 'E', new ItemStack(Items.EMERALD));
		 
		addShapedRecipe(new ItemStack(ModItems.portableEnderChest), "OPO", "OEO", "OPO", 'O', new ItemStack(Blocks.OBSIDIAN), 'E', new ItemStack(Items.ENDER_EYE), 'P', new ItemStack(Items.ENDER_PEARL));
		 
		addShapedRecipe(new ItemStack(ModItems.portableEnderChest), "OOO", "PEP", "OOO", 'O', new ItemStack(Blocks.OBSIDIAN), 'E', new ItemStack(Items.ENDER_EYE), 'P', new ItemStack(Items.ENDER_PEARL));
		 
		addShapedRecipe(new ItemStack(ModBlocks.autoDropper), "RRR", "RDR", "RRR", 'R', new ItemStack(Items.REDSTONE), 'D', new ItemStack(Blocks.DROPPER));
		
		addShapedRecipe(new ItemStack(ModBlocks.enderHopper), "OEO", "OHO", " O ", 'O', new ItemStack(Blocks.OBSIDIAN), 'H', new ItemStack(Blocks.HOPPER), 'E', new ItemStack(Items.ENDER_PEARL));
		 
		addOreDictRecipe(new ItemStack(ModBlocks.rainDetector), "   ", "WBW", "WRW", 'W', "plankWood", 'B', new ItemStack(Items.BUCKET), 'R', new ItemStack(Items.REDSTONE));
		 
		addShapelessRecipe(new ItemStack(ModItems.portableEnderChest, 1, 2), new ItemStack(Items.REDSTONE), new ItemStack(ModItems.portableEnderChest));
		 
		addShapedRecipe(new ItemStack(ModBlocks.sharedEnderChest), "RER", "RDR", "RRR", 'R', new ItemStack(Blocks.OBSIDIAN), 'D', new ItemStack(Blocks.ENDER_CHEST), 'E', new ItemStack(Items.ENDER_PEARL));
		 
		
	}
	
	private static void addShapelessRecipe(ItemStack o, Object... i)
	{
		boolean add = Block.getBlockFromItem(o.getItem()) != Blocks.AIR ? WithSprinkles.config.isBlockEnabled(Block.getBlockFromItem(o.getItem())) : WithSprinkles.config.isItemEnabled(o.getItem());
		
		if(add)
			GameRegistry.addShapelessRecipe(o, i);	
	}

	private static void addOreDictRecipe(ItemStack o, Object... i)
	{
		boolean add = Block.getBlockFromItem(o.getItem()) != Blocks.AIR ? WithSprinkles.config.isBlockEnabled(Block.getBlockFromItem(o.getItem())) : WithSprinkles.config.isItemEnabled(o.getItem());
		
		if(add)
			GameRegistry.addRecipe(new ShapedOreRecipe(o, i));	
	}

	private static void addShapedRecipe(ItemStack o, Object... i) 
	{
		boolean add = Block.getBlockFromItem(o.getItem()) != Blocks.AIR ? WithSprinkles.config.isBlockEnabled(Block.getBlockFromItem(o.getItem())) : WithSprinkles.config.isItemEnabled(o.getItem());
		
		if(add)
			GameRegistry.addShapedRecipe(o, i);
	}

}
