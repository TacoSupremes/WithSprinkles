package com.tacosupremes.withsprinkles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.tacosupremes.withsprinkles.common.blocks.BlockMod;
import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantment;
import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantments;
import com.tacosupremes.withsprinkles.common.items.ItemMod;
import com.tacosupremes.withsprinkles.common.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig
{
	private static Configuration config;

	public static Map<Block, Boolean> bMap = new HashMap<Block, Boolean>(); 
	
	public static Map<Item, Boolean> iMap = new HashMap<Item, Boolean>(); 
	
	public static Map<Enchantment, Boolean> eMap = new HashMap<Enchantment, Boolean>(); 
	
	public void preInit(FMLPreInitializationEvent event)
	{
		for(ItemMod i : ModItems.items)
		{
			iMap.put(i, true);
		}
		
		for(Block i : ModBlocks.blocks)
		{
			bMap.put(i, true);
		}
		
		 for(Enchantment i : ModEnchantments.enchants)
	     {
			 eMap.put(i, true);
	     }
		
		config = new Configuration(event.getSuggestedConfigurationFile());
		
		syncConfig();
	}

	public boolean isBlockEnabled(Block obj)
	{
		
		if(obj == null || bMap.get(obj) == null)			
			return false;
		
		System.out.println(obj.getLocalizedName() + "  " + bMap.get(obj));
		return bMap.get(obj);
	}
	
	public boolean isItemEnabled(Item obj)
	{
		if(obj == null)
			return false;
		
		if(obj instanceof ItemBlock)
			return isBlockEnabled(((ItemBlock)obj).getBlock());
		
		return iMap.get(obj);
	}
	
	public boolean isEnchantEnabled(Enchantment entry)
	{
		
		return eMap.get(entry);
	}
	
	public static void syncConfig() {
		
	    try {
	        
	        config.load();
 
	        for(ItemMod i : ModItems.items)
	        {
	       
	        Property isItemEnabledProp = config.get(Configuration.CATEGORY_GENERAL, "is" + i.getRegistryName().getResourcePath() + "Enabled",  "true", "Whether the " + i.getLocalizedName() + " is enabled");

	        iMap.put(i, isItemEnabledProp.getBoolean());
	        
	        }
	        
	        for(Block i : ModBlocks.blocks)
	        {
	       
	        Property isBlockEnabledProp = config.get(Configuration.CATEGORY_GENERAL, "is" + i.getRegistryName().getResourcePath() + "Enabled",  "true", "Whether the " + i.getLocalizedName() + " is enabled");

	        bMap.put(i, isBlockEnabledProp.getBoolean());
	        
	        }
	        
	        for(Enchantment i : ModEnchantments.enchants)
	        {
	       
	        Property isBlockEnabledProp = config.get(Configuration.CATEGORY_GENERAL, "is" + i.getRegistryName().getResourcePath() + "Enabled",  "true", "Whether the " + I18n.translateToLocal(i.getName()) + " enchantment is enabled");

	        eMap.put(i, isBlockEnabledProp.getBoolean());
	        
	        }
	    } 
	    catch (Exception e) 
	    {
	       
	    } 
	    finally
	    {    
	    	if(config.hasChanged()) 
	    		config.save();
	    }
	}

}
