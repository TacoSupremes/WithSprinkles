package com.tacosupremes.withsprinkles.common.blocks.tiles;

import com.tacosupremes.withsprinkles.common.blocks.BlockEnderHopper;
import com.tacosupremes.withsprinkles.common.utils.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEnderHopper extends TileSimpleInventory implements ITickable {

	@Override
	public int getSizeInventory() {
		
		return 1;
	}
	
	public String pName = "";

	@Override
	public void update() {
		
		EnumFacing enumf = BlockEnderHopper.getFacing(this.getBlockMetadata());
		
		if(InventoryUtils.getInventory(getWorld(), getPos().up()) != null && this.getWorld().getBlockState(getPos().up()).getBlock() != Blocks.ender_chest){
			
			IInventory ii = InventoryUtils.getInventory(getWorld(), getPos().up());
			
			for(int i = 0; i < ii.getSizeInventory(); i++){
				if(ii.getStackInSlot(i) == null)
					continue;
				
				ItemStack is = ii.getStackInSlot(i);
				
				if(this.getStackInSlot(0) == null)
					this.setInventorySlotContents(0, ii.decrStackSize(i, 1));
				else if(is.getItem() == this.getStackInSlot(0).getItem() && is.getItemDamage() == this.getStackInSlot(0).getItemDamage() && is.stackSize + ii.getStackInSlot(i).stackSize <= is.getMaxStackSize())
					this.setInventorySlotContents(0, new ItemStack(is.getItem(),this.getStackInSlot(0).stackSize+ii.getStackInSlot(i).splitStack(1).stackSize,is.getItemDamage()));
			
			ii.markDirty();
			this.markDirty();
			}
			
		}else if(this.getWorld().getBlockState(getPos().up()).getBlock() == Blocks.ender_chest){
			
			ItemStack is = this.getStackInSlot(0);
			
			
			EntityPlayer player = this.getWorld().getPlayerEntityByName(pName);
			
			if(player == null)
				return;
			
			InventoryEnderChest ii = player.getInventoryEnderChest();
			
			
			int slotChosen = -1;
			for(int i = 18; i<27; i++){
				
				if(ii.getStackInSlot(i) == null)
					continue;
				
				
				if(is == null){
					this.setInventorySlotContents(0, ii.decrStackSize(i, 1));
					ii.markDirty();
					this.markDirty();
					
				}
				
			}
			
		}
		
		if(InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())) != null && this.getWorld().getBlockState(getPos().up()).getBlock() == Blocks.ender_chest){
			
			if(this.getStackInSlot(0) == null)
				return;
			
			TileEntityHopper.putStackInInventoryAllSlots(InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())), this.decrStackSize(0, 1), enumf.getOpposite());
			this.markDirty();
			InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())).markDirty();
			
			
		}else if(this.getWorld().getBlockState(getPos().add(enumf.getDirectionVec())).getBlock() == Blocks.ender_chest){
			
			if(this.getStackInSlot(0) == null)
				return;
			
			ItemStack is = this.getStackInSlot(0);
			
			EntityPlayer player = this.getWorld().getPlayerEntityByName(pName);
			
			if(player == null)
				return;
			
			InventoryEnderChest ii = player.getInventoryEnderChest();
			
			int slotChosen = -1;
			for(int i = 18; i<27; i++){
				
				if(ii.getStackInSlot(i) != null){
				
				if(ii.getStackInSlot(i).getItem() == is.getItem() && ii.getStackInSlot(i).getItemDamage() == is.getItemDamage()){
				
					
					if(ii.getStackInSlot(i).stackSize == ii.getStackInSlot(i).getMaxStackSize())
						continue;
					
					if(is.stackSize + ii.getStackInSlot(i).stackSize <= is.getMaxStackSize()){
						
						ii.setInventorySlotContents(i, new ItemStack(is.getItem(),is.stackSize+ii.getStackInSlot(i).stackSize,is.getItemDamage()));
						this.setInventorySlotContents(0, null);
						ii.markDirty();
						this.markDirty();
						return;
					}else{
						
						ii.setInventorySlotContents(i, new ItemStack(is.getItem(),is.getMaxStackSize(),is.getItemDamage()));
						this.setInventorySlotContents(0, new ItemStack(is.getItem(),is.stackSize+ii.getStackInSlot(i).stackSize-is.getMaxStackSize(),is.getItemDamage()));
						ii.markDirty();
						this.markDirty();
						return;
					
					}
					
				}
				
			}else{
					slotChosen = i;
					break;
				}
				
			}
			
			if(slotChosen != -1){
				ii.setInventorySlotContents(slotChosen, is);
				this.setInventorySlotContents(0, null);
				ii.markDirty();
				this.markDirty();
			}
		
			
		}
		
	}

	@Override
	public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
		
		super.readCustomNBT(par1nbtTagCompound);
		
		this.pName = par1nbtTagCompound.getString("PNAME");
		
	}

	@Override
	public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
		
		super.writeCustomNBT(par1nbtTagCompound);
		
		par1nbtTagCompound.setString("PNAME", this.pName);
	}
	
	
	
	

}
