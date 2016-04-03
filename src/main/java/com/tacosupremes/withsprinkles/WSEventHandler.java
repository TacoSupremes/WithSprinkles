package com.tacosupremes.withsprinkles;

import com.tacosupremes.withsprinkles.common.enchantments.ModEnchantments;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WSEventHandler {
  
    @SubscribeEvent
    public void onPlayerBreaking(BreakEvent event) {
    	
    	
    	
        if (event.getPlayer().getHeldItem(EnumHand.MAIN_HAND) != null) {
        	
        	if(!event.getPlayer().isSneaking())
        		return;
        	
        	handleExchange(event);
            
        }
    }
    
    private void handleExchange(BreakEvent event){
    	  ItemStack stack = event.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
          
          if (stack.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.exchange, stack) > 0) {
              	
          ItemStack replace = event.getPlayer().getHeldItem(EnumHand.OFF_HAND);
          
          if(replace == null || Block.getBlockFromItem(replace.getItem()) == null)
              return;
          
          Block replaceb =  Block.getBlockFromItem(replace.getItem()); 
          
          if(replaceb  == event.getState().getBlock() && replace.getItemDamage() == event.getState().getBlock().getMetaFromState(event.getState()))
          	return;
                
          event.getState().getBlock().dropBlockAsItem(event.getWorld(), event.getPos(), event.getState(), EnchantmentHelper.getEnchantmentLevel(Enchantments.fortune, stack));
          
          event.getPlayer().getEntityWorld().setBlockState(event.getPos(), Block.getBlockFromItem(replace.getItem()).getStateFromMeta(replace.getItemDamage()));
          
          if(replace.stackSize > 1)
          	event.getPlayer().getHeldItem(EnumHand.OFF_HAND).stackSize --;
          else
          	event.getPlayer().setHeldItem(EnumHand.OFF_HAND, null);
          
         event.setCanceled(true);
          
          }
    }

}
