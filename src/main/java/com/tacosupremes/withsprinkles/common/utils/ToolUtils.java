package com.tacosupremes.withsprinkles.common.utils;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ToolUtils
{

	public enum ToolType 
	{
		Pickaxe(Material.ROCK, Material.IRON,Material.ANVIL,Material.PACKED_ICE), Spade(Material.GROUND,Material.SNOW,Material.SAND,Material.CLAY,Material.GRASS), Axe(Material.WOOD, Material.GOURD), Hoe(Material.GOURD), Items(Material.AIR);
	
	
		public Material[] effective;

		private ToolType(Material... i)
		{
		this.effective = i;
		}
	
	}	
	
	public static boolean isToolEffective(ItemStack is, IBlockState i){
		
		return isToolEffective(getToolType(is), i);
	}
	
	private static boolean isToolEffective(ToolType t, IBlockState i)
	{
		
		if(t == ToolType.Items)
			return false;
		
		for(Material m : t.effective)
		{
			
			if(m == i.getMaterial())
				return true;
		}
		
		return false;
	}
	
	public static ToolType getToolType(ItemStack is)
	{
		
		if(is.getItem() instanceof ItemPickaxe)
			return ToolType.Pickaxe;
		
		if(is.getItem() instanceof ItemSpade)
			return ToolType.Spade;
		
		if(is.getItem() instanceof ItemAxe)
			return ToolType.Axe;
		
		if(is.getItem() instanceof ItemHoe)
			return ToolType.Hoe;
		
		return ToolType.Items;
		
	}

}