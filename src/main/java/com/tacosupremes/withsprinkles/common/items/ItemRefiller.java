package com.tacosupremes.withsprinkles.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
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
	
		if(stack.getItemDamage() == 0)
			return;
		
		if(!(e instanceof EntityPlayer))
			return;
		
		EntityPlayer player = (EntityPlayer)e;
		
		
		if(player.getHeldItem(EnumHand.OFF_HAND) != null){
			
			ItemStack target = player.getHeldItem(EnumHand.OFF_HAND);
			
			if(target.getCount() != target.getMaxStackSize()){
				
				for(int i = 0; i < player.inventory.mainInventory.size(); i++){
					
					if(player.inventory.getStackInSlot(i) == null || player.inventory.getStackInSlot(i).isEmpty())
						continue;
					
					
					ItemStack replace = player.inventory.getStackInSlot(i);
					
					if(replace.getItem() == target.getItem() && replace.getItemDamage() == target.getItemDamage()){
						
						
						if(replace.getCount() + target.getCount() <= target.getMaxStackSize()){
							
							player.inventory.offHandInventory.set(0, new ItemStack(target.getItem(), target.getCount()+replace.getCount(), target.getItemDamage()));
							
							player.inventory.removeStackFromSlot(i);
							
							break;
							
						}else{
							
							player.inventory.setInventorySlotContents(i,new ItemStack(target.getItem(),target.getCount() + replace.getCount()-target.getMaxStackSize(),target.getItemDamage()));
							player.inventory.offHandInventory.set(0, new ItemStack(target.getItem(),target.getMaxStackSize(),target.getItemDamage()));
							
							break;
						
						}
						
						
						
					}
						
					
					
				}
				
				
				
			}
			
		}
		
		outer:
		for(int slot = 0; slot < 9; slot ++){
			
			if(player.inventory.getStackInSlot(slot) == null || player.inventory.getStackInSlot(slot).isEmpty())
				continue;
			
			ItemStack target = player.inventory.getStackInSlot(slot);
			
			if(target.getCount() == target.getMaxStackSize())
				continue;
			
			for(int i = 9; i < player.inventory.mainInventory.size(); i++){
			
			if(player.inventory.getStackInSlot(i) == null)
				continue;
			
			
			ItemStack replace = player.inventory.getStackInSlot(i);
			
			if(replace.getItem() == target.getItem() && replace.getItemDamage() == target.getItemDamage()){
				
				
				if(replace.getCount() + target.getCount() <= target.getMaxStackSize()){
					
					player.inventory.setInventorySlotContents(slot, new ItemStack(target.getItem(), target.getCount()+replace.getCount(), target.getItemDamage()));
					
					player.inventory.removeStackFromSlot(i);
					
					if(target.getCount() == target.getMaxStackSize())
						continue outer;
					
				}else{
					
					player.inventory.setInventorySlotContents(i,new ItemStack(target.getItem(),target.getCount() + replace.getCount()-target.getMaxStackSize(),target.getItemDamage()));
					
					player.inventory.setInventorySlotContents(slot,new ItemStack(target.getItem(),target.getMaxStackSize(),target.getItemDamage()));
					
					continue outer;
				}	
			}	
		}
	}		
}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
		
		if(player.isSneaking()){
			
			if(player.getHeldItem(hand).getItemDamage() == 0)
				player.getHeldItem(hand).setItemDamage(1);
			else
				player.getHeldItem(hand).setItemDamage(0);
		
			return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		
		return super.onItemRightClick(worldIn, player, hand);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		
		return stack.getItemDamage() == 1;
	}

	@Override
	public boolean needsDifferentNames() {
		
		return false;
	}

	
	
	
	
	
	
	

}
