package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;
import com.tacosupremes.withsprinkles.common.utils.RecipeHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

	public static void postInit() {
				
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModItems.refillGem), "RGR","GEG","RGR", 'R', ProxyRegistry.newStack(Items.REDSTONE), 'G', ProxyRegistry.newStack(Items.GLOWSTONE_DUST), 'E', ProxyRegistry.newStack(Items.EMERALD));
		
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModItems.portableEnderChest), "OPO","OEO","OPO", 'O', ProxyRegistry.newStack(Blocks.OBSIDIAN), 'E', ProxyRegistry.newStack(Items.ENDER_EYE), 'P', ProxyRegistry.newStack(Items.ENDER_PEARL));
	
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModItems.portableEnderChest), "OOO","PEP","OOO", 'O', ProxyRegistry.newStack(Blocks.OBSIDIAN), 'E', ProxyRegistry.newStack(Items.ENDER_EYE), 'P', ProxyRegistry.newStack(Items.ENDER_PEARL));		
		
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModBlocks.autoDropper), "RRR", "RDR", "RRR", 'R', ProxyRegistry.newStack(Items.REDSTONE), 'D', ProxyRegistry.newStack(Blocks.DROPPER));		
	
		RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(ModBlocks.enderHopper), "OEO", "OHO", " O " , 'O', ProxyRegistry.newStack(Blocks.OBSIDIAN), 'H', ProxyRegistry.newStack(Blocks.HOPPER), 'E', ProxyRegistry.newStack(Items.ENDER_PEARL));
		
		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(ModBlocks.rainDetector), "   ", "WBW", "WRW" , 'W', "plankWood", 'B', ProxyRegistry.newStack(Items.BUCKET), 'R', ProxyRegistry.newStack(Items.REDSTONE));
		
		
	}

}
