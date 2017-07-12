package com.tacosupremes.withsprinkles;

import java.util.List;
import java.util.Map;

import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantments;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.BlockUtils;
import com.tacosupremes.withsprinkles.common.utils.ToolUtils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.RenderTooltipEvent.PostText;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WSEventHandler
{
  
    @SubscribeEvent
    public void onPlayerBreaking(BreakEvent event) 
    {
    	
        if (event.getPlayer().getHeldItem(EnumHand.MAIN_HAND) != null)
        {
        	
        	handleFiery(event);
        	
        	handleFelling(event);
        	
        	if(!event.getPlayer().isSneaking())
        		handleExchange(event);
            
        }
     
    }
    
    private void handleFelling(BreakEvent event) 
    {
    	ItemStack stack = event.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
         
         if (stack.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.felling, stack) > 0) 
         {
        	 
        	 if(event.getState().getBlock().isWood(event.getWorld(), event.getPos()))
        	 {    		 
        		 
        		 List<BlockPos> l = BlockUtils.getConnectedLogs(event.getWorld(), event.getPos());
        		 
        		 World w = event.getWorld();
        		 
        		 for(BlockPos bp : l)
        		 {
        			 
        			 if(stack.isEmpty())
        				 break;
        			 
        			IBlockState ib = w.getBlockState(bp);
        			
        			ib.getBlock().harvestBlock(w, event.getPlayer(), bp, ib, null, stack);
        			
        			w.setBlockToAir(bp);
        			
        			stack.damageItem(2, event.getPlayer());
        			 
        		 }
        		 
        		 event.setCanceled(true);
        	 }
 
         }
	}

	@SubscribeEvent
    public void onLootLoad(LootTableLoadEvent event) 
    {
    	
    	String name = event.getName().toString();

    	try{
    		  
    		  if(name.contains("minecraft:chests"))
    			 event.getTable().addPool(getAdditive(WithSprinkles.oldPagesLoot));

    	   }catch(Exception exc){}
    	}

    private LootPool getAdditive(ResourceLocation name) 
    {
    	return new LootPool(new LootEntry[] { getAdditiveEntry(name, 1) }, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "Additive_pool");
   	}

    private LootEntryTable getAdditiveEntry(ResourceLocation name, int weight)
    {
    	return new LootEntryTable(name, weight, 0, new LootCondition[0], "Additive_entry");
    }
     
    @SubscribeEvent
    public void onPlayerRightClick(RightClickItem event)
    {
    	handleMultiple(event);	
    }
      
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void renderText(ItemTooltipEvent event)
    {
    		
    	if(event.getItemStack().isEmpty())
    		return;
    	
    	handleMultiple(event);
    	    	
    }
    
    private void handleMultiple(RightClickItem event)
    {
    	
    	if(!event.getEntityPlayer().isSneaking())
    		return;
    	
    	if(event.getItemStack() == ItemStack.EMPTY)
    		return;
    	
    	if(!event.getItemStack().hasTagCompound())
    		return;
    	
    	if((EnchantmentHelper.getEnchantmentLevel(ModEnchantments.multiple, event.getItemStack()) > 0))
    	{
    		
    	
    		if(event.getItemStack().getItem() instanceof ItemArmor || event.getItemStack().getItem() instanceof ItemFishingRod)
    		{
    			
    			event.setCanceled(true);
    			event.setCancellationResult(EnumActionResult.FAIL);
    		}
    		
    		if(!event.getItemStack().getTagCompound().hasKey("MULTIPLELVL"))
    		{
    		
    			event.getItemStack().getTagCompound().setInteger("MULTIPLELVL", EnchantmentHelper.getEnchantmentLevel(ModEnchantments.multiple, event.getItemStack()));
    			event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 1);
    			
    			event.getItemStack().getTagCompound().setTag("ench1", event.getItemStack().getTagCompound().getTagList("ench",10));
    		
    			event.getItemStack().getTagCompound().removeTag("ench");
    			
    			
    		
    		}
    		else
    		{
    			
    			if((EnchantmentHelper.getEnchantmentLevel(ModEnchantments.multiple, event.getItemStack()) != event.getItemStack().getTagCompound().getInteger("MULTIPLELVL")))
    				event.getItemStack().getTagCompound().setInteger("MULTIPLELVL", EnchantmentHelper.getEnchantmentLevel(ModEnchantments.multiple, event.getItemStack()));
    			
    			
    			
    			if(event.getItemStack().getTagCompound().getInteger("MULTIPLELVL") > 1)
    			{
    				
    			int mode = event.getItemStack().getTagCompound().getInteger("MULTIPLEMODE");
    			
    			
    			switch(mode)
    			{
    			
    				case(0):
    				{
    					event.getItemStack().getTagCompound().setTag("ench1", event.getItemStack().getEnchantmentTagList());
    					event.getItemStack().getTagCompound().setTag("ench", event.getItemStack().getTagCompound().getTagList("ench2", 10));
    					event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 1);
    					break;
    				}
    				
    				case(1):
    				{
    					event.getItemStack().getTagCompound().setTag("ench2", event.getItemStack().getEnchantmentTagList());
    					event.getItemStack().getTagCompound().setTag("ench", event.getItemStack().getTagCompound().getTagList("ench3", 10));
    					event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 2);
    					break;
    				}
    			
    				case(2):
    				{  				
    					event.getItemStack().getTagCompound().setTag("ench3", event.getItemStack().getEnchantmentTagList()); 	
    					event.getItemStack().getTagCompound().setTag("ench", event.getItemStack().getTagCompound().getTagList("ench1", 10));
    					event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 0);
    					break;
    				}
    			
    			
    			}
    				
    			}
    			else
    			{
    				
    				if((EnchantmentHelper.getEnchantmentLevel(ModEnchantments.multiple, event.getItemStack()) != event.getItemStack().getTagCompound().getInteger("MULTIPLELVL")))
    				{
        				event.getItemStack().getTagCompound().setInteger("MULTIPLELVL", EnchantmentHelper.getEnchantmentLevel(ModEnchantments.multiple, event.getItemStack()));
        				handleMultiple(event);
        				return;
        				
        			}
    				
    				NBTTagList ench = event.getItemStack().getTagCompound().getTagList("ench2", 10);

    				event.getItemStack().getTagCompound().setTag("ench1", event.getItemStack().getEnchantmentTagList());
    				
    				event.getItemStack().getTagCompound().setTag("ench", ench);
    				
    				event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 1);
    				
    			}
    			
    		
    		}
    	
    	}
    	else
    	{
    		
    		if(!event.getItemStack().getTagCompound().hasKey("MULTIPLELVL"))
    			return;

    		if(event.getItemStack().getTagCompound().getInteger("MULTIPLEMODE") == 0)
    		{
    			
    			event.getItemStack().getTagCompound().removeTag("MULTIPLELVL");
    			
    			event.getItemStack().getTagCompound().removeTag("MULTIPLEMODE");
    			
    			event.getItemStack().getTagCompound().removeTag("ench1");
    			
    			event.getItemStack().getTagCompound().removeTag("ench2");
    			
    			event.getItemStack().getTagCompound().removeTag("ench3");
    			
				return;
				
			}
			
    		if(event.getItemStack().getItem() instanceof ItemArmor || event.getItemStack().getItem() instanceof ItemFishingRod)
    		{
    			
    			event.setCanceled(true);
    			event.setCancellationResult(EnumActionResult.FAIL);
    		}
    		
    		if(event.getItemStack().getTagCompound().getInteger("MULTIPLELVL") > 1)
    		{

    			int mode = event.getItemStack().getTagCompound().getInteger("MULTIPLEMODE");

    			switch(mode)
    			{
    			
    				case(0):
    				{
    					event.getItemStack().getTagCompound().setTag("ench1", event.getItemStack().getEnchantmentTagList());
    					event.getItemStack().getTagCompound().setTag("ench", event.getItemStack().getTagCompound().getTagList("ench2", 10));
    					event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 1);
    					break;
    				}
    			
    				case(1):
    				{
    					event.getItemStack().getTagCompound().setTag("ench2", event.getItemStack().getEnchantmentTagList());
    					event.getItemStack().getTagCompound().setTag("ench", event.getItemStack().getTagCompound().getTagList("ench3", 10));
    					event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 2);
    					break;
    				}
    			
    				case(2):
    				{  				
    					event.getItemStack().getTagCompound().setTag("ench3", event.getItemStack().getEnchantmentTagList()); 	
    					event.getItemStack().getTagCompound().setTag("ench", event.getItemStack().getTagCompound().getTagList("ench1", 10));
    					event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 0);
    					break;
    				}
    			
    			
    			}

    		}
    		else
    		{
    			
    			
    			
    			NBTTagList ench = event.getItemStack().getTagCompound().getTagList("ench1", 10);
				
				event.getItemStack().getTagCompound().setTag("ench2", event.getItemStack().getEnchantmentTagList());
				
				event.getItemStack().getTagCompound().setTag("ench", ench);
    			
				event.getItemStack().getTagCompound().setInteger("MULTIPLEMODE", 0);
    			
    		}
    	
    	
    	}
    }
    
    private void handleMultiple(ItemTooltipEvent event)
    {
    	
    	if(!event.getItemStack().hasTagCompound())
    		return;
    	
    	if(event.getItemStack().getTagCompound().hasKey("MULTIPLEMODE"))
    	{
   		
    		Map<Enchantment, Integer> j = EnchantmentHelper.getEnchantments(event.getItemStack());
    		
    		if(!j.keySet().contains(ModEnchantments.multiple) && event.getItemStack().isItemEnchanted())
    		{	

    			Enchantment[] en = j.keySet().toArray(new Enchantment[j.keySet().size()]);
    	
    			int pos = event.getToolTip().indexOf(en[en.length-1].getTranslatedName(j.get(en[en.length-1])));
    	
    		event.getToolTip().add(pos + 1, ModEnchantments.multiple.getTranslatedName(event.getItemStack().getTagCompound().getInteger("MULTIPLELVL")));
    	}
 		
    	int pos = event.getToolTip().indexOf(ModEnchantments.multiple.getTranslatedName(event.getItemStack().getTagCompound().getInteger("MULTIPLELVL")));
    	
    	event.getToolTip().add(pos == -1 ? 1 : pos + 1, I18n.translateToLocal(LibMisc.MODID + ".mode") + ": " + (event.getItemStack().getTagCompound().getInteger("MULTIPLEMODE")+1));
    	
    	
    	
    	}
    }
    
    private void handleFiery(BreakEvent event)
    {
    	
    	
    	 ItemStack stack = event.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
         
         if (stack.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fiery, stack) > 0 && ForgeHooks.canToolHarvestBlock(event.getWorld(), event.getPos(), stack)) 
         {
         
        	 ItemStack result = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().getMetaFromState(event.getState())));
        	
        	 if(result != ItemStack.EMPTY)
        	 {
        		 
        		 World w = event.getPlayer().getEntityWorld();
        		 BlockPos pos = event.getPos();
        		 
        		 if(!w.isRemote)
        		 {
        			 w.destroyBlock(pos, false);
        			 w.getBlockState(pos).getBlock().spawnAsEntity(w, pos, new ItemStack(result.getItem(), 1, result.getItemDamage()));
        		 }
        		 
        		 event.getPlayer().getHeldItem(EnumHand.MAIN_HAND).damageItem(1, event.getPlayer());
        		 event.setCanceled(true);
        	 }
        	 
         }
		
	}

	private void handleExchange(BreakEvent event)
	{
		
    	  ItemStack stack = event.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
          
          if (stack.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.exchange, stack) > 0) {
              	
          ItemStack replace = event.getPlayer().getHeldItem(EnumHand.OFF_HAND);
          
          if(replace.isEmpty() || Block.getBlockFromItem(replace.getItem()) == Blocks.AIR)
              return;
          
          Block replaceb =  Block.getBlockFromItem(replace.getItem()); 
          
          if(replaceb  == event.getState().getBlock() && replace.getItemDamage() == event.getState().getBlock().getMetaFromState(event.getState()))
          	return;
                
          event.getState().getBlock().dropBlockAsItem(event.getWorld(), event.getPos(), event.getState(), EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));
          
          event.getPlayer().getEntityWorld().setBlockState(event.getPos(), Block.getBlockFromItem(replace.getItem()).getStateFromMeta(replace.getItemDamage()));
          
          if(replace.getCount() > 1)
          	event.getPlayer().getHeldItem(EnumHand.OFF_HAND).shrink(1);
          else
        	  event.getPlayer().setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
          
         event.setCanceled(true);
          
          }
    }

}
