package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;
import com.tacosupremes.withsprinkles.common.utils.RecipeHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class ModRecipes {

	public static void preInit() {
				
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModItems.refillGem), "RGR","GEG","RGR", 'R', ProxyRegistry.newStack(Items.REDSTONE), 'G', ProxyRegistry.newStack(Items.GLOWSTONE_DUST), 'E', ProxyRegistry.newStack(Items.EMERALD));
		
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModItems.portableEnderChest), "OPO","OEO","OPO", 'O', ProxyRegistry.newStack(Blocks.OBSIDIAN), 'E', ProxyRegistry.newStack(Items.ENDER_EYE), 'P', ProxyRegistry.newStack(Items.ENDER_PEARL));
	
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModItems.portableEnderChest), "OOO","PEP","OOO", 'O', ProxyRegistry.newStack(Blocks.OBSIDIAN), 'E', ProxyRegistry.newStack(Items.ENDER_EYE), 'P', ProxyRegistry.newStack(Items.ENDER_PEARL));		
		
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModBlocks.autoDropper), "RRR", "RDR", "RRR", 'R', ProxyRegistry.newStack(Items.REDSTONE), 'D', ProxyRegistry.newStack(Blocks.DROPPER));		
	
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModBlocks.enderHopper), "OEO", "OHO", " O " , 'O', ProxyRegistry.newStack(Blocks.OBSIDIAN), 'H', ProxyRegistry.newStack(Blocks.HOPPER), 'E', ProxyRegistry.newStack(Items.ENDER_PEARL));
		
		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(ModBlocks.rainDetector), "   ", "WBW", "WRW" , 'W', "plankWood", 'B', ProxyRegistry.newStack(Items.BUCKET), 'R', ProxyRegistry.newStack(Items.REDSTONE));
		
		RecipeHandler.addShapelessRecipe(ProxyRegistry.newStack(ModItems.portableEnderChest, 1, 2), ProxyRegistry.newStack(Items.REDSTONE), ProxyRegistry.newStack(ModItems.portableEnderChest));
		
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModBlocks.sharedEnderChest), "RER", "RDR", "RRR", 'R', ProxyRegistry.newStack(Blocks.OBSIDIAN), 'D', ProxyRegistry.newStack(Blocks.ENDER_CHEST), 'E', ProxyRegistry.newStack(Items.ENDER_PEARL));		
			
		EnchantedBookRecipe r = new EnchantedBookRecipe();
	}

}
