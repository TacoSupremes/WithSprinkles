package com.tacosupremes.withsprinkles.common.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;

public class TileAutoDropper extends TileSimpleInventory implements ITickable, IInventory {

	@Override
	public void update() {
		
		
	}
	
	@Override
	public int getSizeInventory() {
		
		return 1;
	}

	

	

	

}
