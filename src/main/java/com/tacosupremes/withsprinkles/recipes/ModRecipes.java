package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void postInit() {
		
		GameRegistry.addRecipe(new ItemStack(ModItems.refillGem), "RGR","GEG","RGR", 'R', new ItemStack(Items.REDSTONE), 'G', new ItemStack(Items.GLOWSTONE_DUST), 'E', new ItemStack(Items.EMERALD));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.portableEnderChest), "OPO","OEO","OPO", 'O', new ItemStack(Blocks.OBSIDIAN), 'E', new ItemStack(Items.ENDER_EYE), 'P', new ItemStack(Items.ENDER_PEARL));
	
		GameRegistry.addRecipe(new ItemStack(ModItems.portableEnderChest), "OOO","PEP","OOO", 'O', new ItemStack(Blocks.OBSIDIAN), 'E', new ItemStack(Items.ENDER_EYE), 'P', new ItemStack(Items.ENDER_PEARL));		
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.autoDropper), "RRR", "RDR", "RRR", 'R', new ItemStack(Items.REDSTONE), 'D', new ItemStack(Blocks.DROPPER));		
	
		GameRegistry.addRecipe(new ItemStack(ModBlocks.enderHopper), "OEO", "OHO", " O " , 'O', new ItemStack(Blocks.OBSIDIAN), 'H', new ItemStack(Blocks.HOPPER), 'E', new ItemStack(Items.ENDER_PEARL));
	}

}
