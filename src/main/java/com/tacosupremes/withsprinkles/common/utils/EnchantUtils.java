package com.tacosupremes.withsprinkles.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;

public class EnchantUtils 
{
	
	  public static void enchantItem(ItemStack is, Enchantment e, int lvl)
	  {
	    	
	    	NBTTagList nbtl = new NBTTagList();
	    	
	    	NBTTagCompound nbt = new NBTTagCompound();
	    	
	    	nbt.setShort("id", (short)Enchantment.getEnchantmentID(e));
	        nbt.setShort("lvl", (short)lvl);
	        nbtl.appendTag(nbt);
	        
	        if (is.getItem() == Items.ENCHANTED_BOOK)
	        {
	        	ItemEnchantedBook.addEnchantment(is, new EnchantmentData(e, lvl));
	            return;
	        }
	        
	        if(!is.hasTagCompound())	
	        	is.setTagCompound(new NBTTagCompound());
	   
	        is.setTagInfo("ench", nbtl);
	    	
	  }
  
	  public static Enchantment randEnchantmentBook(Random rand, ItemStack is, boolean treasureOnly, Rarity rarity)
	  {
		  
		  List<Enchantment> treasure = new ArrayList<Enchantment>();
		  List<Enchantment> canApply = new ArrayList<Enchantment>();
		  
		  if(treasureOnly)
			  rarity = Rarity.RARE;
		  
	
		  for(ResourceLocation r : Enchantment.REGISTRY.getKeys())
		  {
			  
			 Enchantment e = Enchantment.REGISTRY.getObject(r);
			 
			 if(e.isCurse())
				 continue;
			 
			 if(e.isTreasureEnchantment())
				 treasure.add(e);

			 if(e.getRarity() == rarity)
				 canApply.add(e); 
			 
			 if(treasureOnly && e.getRarity() == Rarity.VERY_RARE)
				canApply.add(e); 
		  }
		  	
		  if(treasureOnly)
		  canApply.addAll(treasure);
		  
		  return canApply.get(rand.nextInt(canApply.size()));

	  }
	  
	  public static Enchantment randEnchantmentMinTier(Random rand, ItemStack is, boolean treasure, Rarity rarity)
	  {
		  
		  List<Enchantment> treasureL = new ArrayList<Enchantment>();
		  List<Enchantment> canApply = new ArrayList<Enchantment>();
		  
		  for(ResourceLocation r : Enchantment.REGISTRY.getKeys())
		  {
			  
			 Enchantment e = Enchantment.REGISTRY.getObject(r);
			 
			 if(e.isCurse())
				 continue;
			 
			 if(e.isTreasureEnchantment())
				 treasureL.add(e);
			 else if(e.getRarity().getWeight() <= rarity.getWeight())
				 canApply.add(e); 
		  }
		  	
		  if(treasure)
			  canApply.addAll(treasureL);
		  
		  return canApply.get(rand.nextInt(canApply.size()));

	  }
	  
	  public static Enchantment randEnchantmentMaxTier(Random rand, ItemStack is, boolean treasure, Rarity rarity)
	  {
		  
		  List<Enchantment> treasureL = new ArrayList<Enchantment>();
		  List<Enchantment> canApply = new ArrayList<Enchantment>();
		  
		  for(ResourceLocation r : Enchantment.REGISTRY.getKeys())
		  {
			  
			 Enchantment e = Enchantment.REGISTRY.getObject(r);
			 
			 if(e.isCurse())
				 continue;
			 
			 if(e.isTreasureEnchantment())
				 treasureL.add(e);
			 else if(e.getRarity().getWeight() >= rarity.getWeight())
				 canApply.add(e); 
		  }
		  	
		  if(treasure)
			  canApply.addAll(treasureL);
		  
		  return canApply.get(rand.nextInt(canApply.size()));

	  }
	  
	  public static Enchantment randEnchantmentTier(Random rand, ItemStack is, boolean treasure, Rarity rarity)
	  {
		  
		  List<Enchantment> treasureL = new ArrayList<Enchantment>();
		  List<Enchantment> canApply = new ArrayList<Enchantment>();
		  
		  for(ResourceLocation r : Enchantment.REGISTRY.getKeys())
		  {
			  
			 Enchantment e = Enchantment.REGISTRY.getObject(r);
			 
			 if(e.isCurse())
				 continue;
			 
			 if(e.isTreasureEnchantment())
				 treasureL.add(e);
			 else if(e.getRarity() == rarity)
				 canApply.add(e); 
		  }
		  	
		  if(treasure)
			  canApply.addAll(treasureL);
		  
		  return canApply.get(rand.nextInt(canApply.size()));

	  }
}
