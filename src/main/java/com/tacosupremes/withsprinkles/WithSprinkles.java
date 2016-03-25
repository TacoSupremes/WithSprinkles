package com.tacosupremes.withsprinkles;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantments;
import com.tacosupremes.withsprinkles.common.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WithSprinkles.MODID, version = WithSprinkles.VERSION)
public class WithSprinkles
{
    public static final String MODID = "withsprinkles";
    public static final String VERSION = "1.0";
	public static CreativeTabs tab;
	public static WSEventHandler events;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	tab = new WSTab();
    	ModItems.preInit();
    	ModBlocks.preInit();
		
    	ModEnchantments.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	  events = new WSEventHandler();
          MinecraftForge.EVENT_BUS.register(events);
        
    }

    public class WSTab extends CreativeTabs{

		public WSTab() {
			super(CreativeTabs.getNextID(), MODID);
			
		}

		@Override
		public Item getTabIconItem() {
			
			return Items.apple;
		}
    	
    }
    
}
