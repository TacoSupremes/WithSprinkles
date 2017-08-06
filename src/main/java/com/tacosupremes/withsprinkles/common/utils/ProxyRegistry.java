package com.tacosupremes.withsprinkles.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantment;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ProxyRegistry
{

	//private static Multimap<Class<?>, IForgeRegistryEntry<?>> entries = MultimapBuilder.hashKeys().arrayListValues().build();

	private static HashMap<Block, Item> temporaryItemBlockMap = new HashMap();

	public static void register(Item obj)
	{

		GameRegistry.register(obj);
		
	}

	public static void register(Block obj)
	{

		GameRegistry.register(obj);
		GameRegistry.register(new ItemBlock(obj).setRegistryName(obj.getUnlocalizedName().substring(5)));
		
	}
	
	public static void register(Enchantment obj)
	{

		GameRegistry.register(obj);
		
	}
	private static Item getItemMapping(Block block)
	{
		Item i = Item.getItemFromBlock(block);

		if ((i == null || i == Item.getItemFromBlock(Blocks.AIR)) && temporaryItemBlockMap.containsKey(block))
			return temporaryItemBlockMap.get(block);

		return i;
	}

	public static ItemStack newStack(Block block)
	{
		return newStack(block, 1);
	}

	public static ItemStack newStack(Block block, int size)
	{
		return newStack(block, size, 0);
	}

	public static ItemStack newStack(Block block, int size, int meta)
	{
		return newStack(getItemMapping(block), size, meta);
	}

	public static ItemStack newStack(Item item)
	{
		return newStack(item, 1);
	}

	public static ItemStack newStack(Item item, int size)
	{
		return newStack(item, size, 0);
	}

	public static ItemStack newStack(Item item, int size, int meta)
	{
		return new ItemStack(item, size, meta);
	}



}