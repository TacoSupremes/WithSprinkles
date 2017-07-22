package com.tacosupremes.withsprinkles.common.blocks;

import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.withsprinkles.common.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModBlocks {

	public static List<Block> blocks = new ArrayList<Block>();
	
	public static Block autoDropper;
	
	public static Block enderHopper;
	
	public static Block rainDetector;
	
	public static Block sharedEnderChest; //TODO: Shared Ender Chest
	
	public static Block enchantmentBuff;
	
	public static void preInit() {
		
		autoDropper = new BlockAutoDropper();
		
		enderHopper = new BlockEnderHopper();
		
		rainDetector = new BlockRainDetector();
		
		sharedEnderChest = new BlockSharedEnderChest();
		
		
	}

	
	public static void registerRenders(){
		
		for(Block i : blocks){
			ModItems.registerItemRender(Item.getItemFromBlock(i), 0);
		}
		
		
	}
}
