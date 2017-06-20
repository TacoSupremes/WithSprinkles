package com.tacosupremes.withsprinkles;

import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantments;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WSEventHandler {
  
    @SubscribeEvent
    public void onPlayerBreaking(BreakEvent event) {
    	
    	
    	
        if (event.getPlayer().getHeldItem(EnumHand.MAIN_HAND) != null) {
        	
        	handleFiery(event);
        	
        	if(!event.getPlayer().isSneaking())
        	handleExchange(event);
        	
        	
            
        }
        
     
    }
    
    private void handleFiery(BreakEvent event) {
    	
    	
    	 ItemStack stack = event.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
         
         if (stack.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fiery, stack) > 0 && stack.getItem().canHarvestBlock(event.getState(), stack)) {
         
        	 ItemStack result = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().getMetaFromState(event.getState())));
        	 if(result != ItemStack.EMPTY){
        		 
        		 World w = event.getPlayer().getEntityWorld();
        		 BlockPos pos = event.getPos();
        		 
        		 if(!w.isRemote){
        		 w.destroyBlock(pos, false);
        		 w.getBlockState(pos).getBlock().spawnAsEntity(w, pos, new ItemStack(result.getItem(), 1, result.getItemDamage()));
        		 }
        		 
        		 event.getPlayer().getHeldItem(EnumHand.MAIN_HAND).damageItem(1, event.getPlayer());
        		 event.setCanceled(true);
        	 }
        	 
         }
		
	}

	private void handleExchange(BreakEvent event){
		
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
