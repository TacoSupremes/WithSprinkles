package com.tacosupremes.withsprinkles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

		public WSTab() {
			super(CreativeTabs.getNextID(), LibMisc.MODID);
			
		}

		@Override
		public ItemStack getTabIconItem() {
			
		
			
			return new ItemStack(Items.CAKE);
		}
		
		@Override
		public void displayAllRelevantItems(NonNullList<ItemStack> l) {
			
			super.displayAllRelevantItems(l);
			for(Enchantment e : ModEnchantments.enchants){
			ItemStack is = new ItemStack(Items.ENCHANTED_BOOK, 1, 0);
			Map<Enchantment, Integer> lm = new HashMap<Enchantment,Integer>();
			lm.put(e, e.getMaxLevel());
			EnchantmentHelper.setEnchantments(lm, is);
			l.add(is);
			}
		}
	
    }
    
}
