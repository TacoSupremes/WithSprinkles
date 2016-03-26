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
	
	public static void preInit() {
		
		autoDropper = new BlockAutoDropper();
		
	}

	
	public static void registerRenders(){
		
		for(Block i : blocks){
			ModItems.registerItemRender(Item.getItemFromBlock(i), 0);
		}
		
		
	}
}