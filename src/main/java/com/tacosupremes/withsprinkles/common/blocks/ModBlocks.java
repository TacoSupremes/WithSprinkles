package com.tacosupremes.withsprinkles.common.blocks;

import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ModBlocks
{

	public static List<Block> blocks = new ArrayList<Block>();

	public static Block autoDropper;

	public static Block enderHopper;

	public static Block rainDetector;

	public static Block sharedEnderChest;

	public static Block enchantmentBuff;

	public static void preInit()
	{

		autoDropper = new BlockAutoDropper();

		enderHopper = new BlockEnderHopper();

		rainDetector = new BlockRainDetector();

		sharedEnderChest = new BlockSharedEnderChest();

	}
	
	public static void register()
	{
		for (Block i : blocks)
		{
			if(WithSprinkles.config.isBlockEnabled(i))
			{		
				ProxyRegistry.register(i);
				//ProxyRegistry.register(new ItemBlock(i).setRegistryName(i.getRegistryName()));	
			}
		}
		
	}

	public static void registerRenders()
	{

		for (Block i : blocks)
		{
			ModItems.registerItemRender(Item.getItemFromBlock(i), 0);
		}

	}

	
}
