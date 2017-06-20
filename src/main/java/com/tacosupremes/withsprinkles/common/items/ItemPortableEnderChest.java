package com.tacosupremes.withsprinkles.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemPortableEnderChest extends ItemMod {

	public ItemPortableEnderChest() {
		super("enderChest", 1);
		
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		 playerIn.getHeldItem(handIn).setItemDamage(1);
		
		if (worldIn.isRemote)
            return super.onItemRightClick(worldIn, playerIn, handIn);
        		
		 playerIn.displayGUIChest(playerIn.getInventoryEnderChest());
		 
		 
		
		 
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(stack.getItemDamage() == 0)
			return;
		
		if(worldIn.isRemote)
			return;
		
		if(worldIn.getWorldTime() % 5 != 0)
			return;
		
		if(Minecraft.getMinecraft().currentScreen == null)
			stack.setItemDamage(0);
		
	}	
	
	
	
	
	
	

}
