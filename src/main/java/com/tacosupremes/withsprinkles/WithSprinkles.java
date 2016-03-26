package com.tacosupremes.withsprinkles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantments;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WithSprinkles.MODID, version = WithSprinkles.VERSION)
public class WithSprinkles
{
    public static final String MODID = "withsprinkles";
    public static final String VERSION = "1.0";
	private static final String CLIENTPROXY = "com.tacosupremes.withsprinkles.proxy.ClientProxy";
	private static final String COMMONPROXY = "com.tacosupremes.withsprinkles.proxy.CommonProxy";
	public static CreativeTabs tab;
	public static WSEventHandler events;
	
	@SidedProxy(clientSide = WithSprinkles.CLIENTPROXY, serverSide = WithSprinkles.COMMONPROXY)
	public static CommonProxy proxy;
	    
	   
	    
    @Instance(WithSprinkles.MODID)
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

    public class WSTab extends CreativeTabs{

		public WSTab() {
			super(CreativeTabs.getNextID(), MODID);
			
		}

		@Override
		public Item getTabIconItem() {
			
			return Items.apple;
		}

		@Override
		public void displayAllRelevantItems(List<ItemStack> l) {
			
			super.displayAllRelevantItems(l);
			for(Enchantment e : ModEnchantments.enchants){
			ItemStack is = new ItemStack(Items.enchanted_book, 1, 0);
			Map<Enchantment, Integer> lm = new HashMap<Enchantment,Integer>();
			lm.put(e, e.getMaxLevel());
			EnchantmentHelper.setEnchantments(lm, is);
			l.add(is);
			}
		}
		
		
    	
    }
    
}
