package com.tacosupremes.withsprinkles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantments;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.proxy.CommonProxy;
import com.tacosupremes.withsprinkles.recipes.ModRecipes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibMisc.MODID, version = LibMisc.VERSION)
public class WithSprinkles
{
   
	public static CreativeTabs tab;
	public static WSEventHandler events;
	
	@SidedProxy(clientSide = LibMisc.CLIENTPROXY, serverSide = LibMisc.COMMONPROXY)
	public static CommonProxy proxy;
	    
    @Instance(LibMisc.MODID)
    public static WithSprinkles instance;
    
    public static final Logger logger = LogManager.getLogManager().getLogger(LibMisc.MODID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ModEnchantments.preInit();
    	tab = new WSTab();
    	ModItems.preInit();
    	ModBlocks.preInit();

    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	  events = new WSEventHandler();
          MinecraftForge.EVENT_BUS.register(events);
          
         proxy.registerRenders();
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	  ModRecipes.postInit();
    }

    public class WSTab extends CreativeTabs{

		public WSTab() 
		{
			super(CreativeTabs.getNextID(), LibMisc.MODID);		
		}

		@Override
		public ItemStack getTabIconItem() 
		{
			return new ItemStack(Items.CAKE);
		}
		
		@Override
		public void displayAllRelevantItems(NonNullList<ItemStack> l) 
		{
			
			super.displayAllRelevantItems(l);
			
			for(Enchantment e : ModEnchantments.enchants){
				
				ItemStack is = new ItemStack(Items.ENCHANTED_BOOK, 1, 0);
					
					for(int i = 1; i <= e.getMaxLevel(); i++){
						
						ItemStack is2 = is.copy();
						
						enchantItem(is2, e, i);
						
						l.add(is2);
						
					}			
			}
		}
	
    }
    
    
    public void enchantItem(ItemStack is, Enchantment e, int lvl)
    {
    	
    	NBTTagList nbtl = new NBTTagList();
    	
    	NBTTagCompound nbt = new NBTTagCompound();
    	
    	nbt.setShort("id", (short)Enchantment.getEnchantmentID(e));
        nbt.setShort("lvl", (short)lvl);
        nbtl.appendTag(nbt);
        
        if (is.getItem() == Items.ENCHANTED_BOOK)
        {
            Items.ENCHANTED_BOOK.addEnchantment(is, new EnchantmentData(e, lvl));
            return;
        }
        
        if(!is.hasTagCompound())	
        	is.setTagCompound(new NBTTagCompound());
   
        is.setTagInfo("ench", nbtl);
    	
    }
    
}
