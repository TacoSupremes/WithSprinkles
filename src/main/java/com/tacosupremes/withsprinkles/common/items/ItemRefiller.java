package com.tacosupremes.withsprinkles.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRefiller extends ItemMod{

	public ItemRefiller() {
		super("refillGem", 1);
		
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
		
	}

	@Override
	public void onUpdate(ItemStack stack, World w, Entity e, int itemSlot, boolean isSelected) {
	
		if(!(e instanceof EntityPlayer))
			return;
		
		EntityPlayer player = (EntityPlayer)e;
		
		
		if(player.getHeldItem(EnumHand.OFF_HAND) != null){
			
			ItemStack target = player.getHeldItem(EnumHand.OFF_HAND);
			
			if(target.stackSize != target.getMaxStackSize()){
				
				for(int i = 9; i < player.inventory.mainInventory.length; i++){
					
					if(player.inventory.getStackInSlot(i) == null)
						continue;
					
					
					ItemStack replace = player.inventory.getStackInSlot(i);
					
					if(replace.getItem() == target.getItem() && replace.getItemDamage() == target.getItemDamage()){
						
						
						if(replace.stackSize + target.stackSize <= target.getMaxStackSize()){
							
							player.inventory.offHandInventory[0] = new ItemStack(target.getItem(), target.stackSize+replace.stackSize, target.getItemDamage());
							
							player.inventory.setInventorySlotContents(i, null);
							
							
							
						}else{
							
							player.inventory.setInventorySlotContents(i,new ItemStack(target.getItem(),target.stackSize + replace.stackSize-target.getMaxStackSize(),target.getItemDamage()));
							player.inventory.offHandInventory[0] = new ItemStack(target.getItem(),target.getMaxStackSize(),target.getItemDamage());
							
							
						
						}
						
						
						
					}
						
					
					
				}
				
			}
			
		}
		
		outer:
		for(int slot = 0; slot < 9; slot ++){
			
			if(player.inventory.getStackInSlot(slot) == null)
				continue;
			
			ItemStack target = player.inventory.getStackInSlot(slot);
			
			if(target.stackSize == target.getMaxStackSize())
				continue;
			
			for(int i = 9; i < player.inventory.mainInventory.length; i++){
			
			if(player.inventory.getStackInSlot(i) == null)
				continue;
			
			
			ItemStack replace = player.inventory.getStackInSlot(i);
			
			if(replace.getItem() == target.getItem() && replace.getItemDamage() == target.getItemDamage()){
				
				
				if(replace.stackSize + target.stackSize <= target.getMaxStackSize()){
					
					player.inventory.setInventorySlotContents(slot, new ItemStack(target.getItem(), target.stackSize+replace.stackSize, target.getItemDamage()));
					
					player.inventory.setInventorySlotContents(i, null);
					
					if(target.stackSize == target.getMaxStackSize())
						continue outer;
					
				}else{
					
					player.inventory.setInventorySlotContents(i,new ItemStack(target.getItem(),target.stackSize + replace.stackSize-target.getMaxStackSize(),target.getItemDamage()));
					player.inventory.setInventorySlotContents(slot,new ItemStack(target.getItem(),target.getMaxStackSize(),target.getItemDamage()));
					
					continue outer;
				
				}
				
				
				
			}
				
			
			
		}
		}
		
		
		
	}
	
	
	
	
	
	

}
