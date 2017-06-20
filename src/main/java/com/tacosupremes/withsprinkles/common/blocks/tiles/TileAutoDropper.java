package com.tacosupremes.withsprinkles.common.blocks.tiles;

import com.tacosupremes.withsprinkles.common.blocks.BlockAutoDropper;
import com.tacosupremes.withsprinkles.common.utils.InventoryUtils;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public class TileAutoDropper extends TileSimpleInventory implements ITickable, IInventory {

	@Override
	public void update() {
		
		if(this.getStackInSlot(0) == null || this.getStackInSlot(0).isEmpty())
			return;
		
	BlockPos bp = getPos().add(((BlockAutoDropper)this.getWorld().getBlockState(getPos()).getBlock()).getFacing(this.getBlockMetadata()).getDirectionVec());
		
		if(InventoryUtils.getInventory(this.getWorld(), bp) == null){
			
			EntityItem e = new EntityItem(this.getWorld());
			
			EnumFacing enumfacing = ((BlockAutoDropper)this.getBlockType()).getFacing(getBlockMetadata());
			
			  double d0 = this.getPos().getX() + 0.5D + 0.7D * (double)enumfacing.getFrontOffsetX();
		      double d1 = this.getPos().getY() + 0.2D + 0.7D * (double)enumfacing.getFrontOffsetY();
		      double d2 = this.getPos().getZ() + 0.5D + 0.7D * (double)enumfacing.getFrontOffsetZ();
			
		      if (enumfacing.getAxis() == EnumFacing.Axis.Y)
		        {
		            d1 = d1 - 0.125D;
		        }
		        else
		        {
		            d1 = d1 - 0.15625D;
		        }
		      
		      if(enumfacing == EnumFacing.DOWN)
		    	  d1 = this.getPos().getY() - 0.4D;
		    	  
		      
		      e.setPosition(d0, d1, d2);
			
			  double d3 = this.getWorld().rand.nextDouble() * 0.1D + 0.2D;
			  int speed = 6;
		        e.motionX = (double)enumfacing.getFrontOffsetX() * d3;
		        e.motionY = 0.20000000298023224D;
		        e.motionZ = (double)enumfacing.getFrontOffsetZ() * d3;
		        e.motionX += 0.007499999832361937D * (double)speed * (double)enumfacing.getFrontOffsetX();
		        e.motionY += 0.007499999832361937D * (double)speed + (enumfacing.getAxis() == EnumFacing.Axis.Y ? 0.3D * (enumfacing == EnumFacing.UP ? 1 : -1) : 0);
		        e.motionZ += 0.007499999832361937D * (double)speed * (double)enumfacing.getFrontOffsetZ();
		        e.setEntityItemStack(this.getStackInSlot(0));
			
			this.setInventorySlotContents(0, null);
			
			 if(enumfacing.getAxis() == EnumFacing.Axis.Y){
				 e.motionX = 0;
			 	 e.motionZ = 0;
			 }
			
			if(!this.getWorld().isRemote)
				this.getWorld().spawnEntity(e);
				
			
		}else{
			
			EnumFacing enumfacing = ((BlockAutoDropper)this.getBlockType()).getFacing(getBlockMetadata());
			
			
			IInventory t = InventoryUtils.getInventory(this.getWorld(), bp);
			
			ItemStack is = TileEntityHopper.putStackInInventoryAllSlots(this, t, this.getStackInSlot(0), enumfacing.getOpposite());

				this.setInventorySlotContents(0, is);
				this.markDirty();
		}
	}
	
	@Override
	public int getSizeInventory() {
		
		return 1;
	}



	

	

}
