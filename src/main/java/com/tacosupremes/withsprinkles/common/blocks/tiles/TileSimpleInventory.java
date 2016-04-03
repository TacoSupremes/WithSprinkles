package com.tacosupremes.withsprinkles.common.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

public abstract class TileSimpleInventory extends TileEntity implements IInventory{

		ItemStack[] inventorySlots = new ItemStack[getSizeInventory()];

	
		public void readCustomNBT(NBTTagCompound par1NBTTagCompound) {
			NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10);
			inventorySlots = new ItemStack[getSizeInventory()];
			for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
				NBTTagCompound var4 = var2.getCompoundTagAt(var3);
				byte var5 = var4.getByte("Slot");
				if (var5 >= 0 && var5 < inventorySlots.length)
					inventorySlots[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}

		
		public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
			NBTTagList var2 = new NBTTagList();
			for (int var3 = 0; var3 < inventorySlots.length; ++var3) {
				if (inventorySlots[var3] != null) {
					NBTTagCompound var4 = new NBTTagCompound();
					var4.setByte("Slot", (byte)var3);
					inventorySlots[var3].writeToNBT(var4);
					var2.appendTag(var4);
				}
			}
			par1NBTTagCompound.setTag("Items", var2);
		}

		@Override
		public ItemStack getStackInSlot(int i) {
			return inventorySlots[i];
		}

		@Override
		public ItemStack decrStackSize(int i, int j) {
			if (inventorySlots[i] != null) {
				ItemStack stackAt;

				if (inventorySlots[i].stackSize <= j) {
					stackAt = inventorySlots[i];
					inventorySlots[i] = null;
					return stackAt;
				} else {
					stackAt = inventorySlots[i].splitStack(j);

					if (inventorySlots[i].stackSize == 0)
						inventorySlots[i] = null;

					return stackAt;
				}
			}

			return null;
		}

	

		@Override
		public void setInventorySlotContents(int i, ItemStack itemstack) {
			inventorySlots[i] = itemstack;
		}

		@Override
		public int getInventoryStackLimit() {
			return 64;
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer entityplayer) {
			return worldObj.getTileEntity(this.getPos()) != this ? false : entityplayer.getDistanceSq(this.getPos().add(0.5D, 0.5D, 0.5D)) <= 64;
		}

		@Override
		public boolean isItemValidForSlot(int i, ItemStack itemstack) {
			return true;
		}

		
		@Override
		public ITextComponent getDisplayName() {
			
			return null;
		}

		@Override
		public String getName() {
			
			return null;
		}

		@Override
		public boolean hasCustomName() {
			
			return false;
		}
		
		@Override
		public void openInventory(EntityPlayer player) {
			
			
		}

		@Override
		public void closeInventory(EntityPlayer player) {
			
			
		}
		
		@Override
		public int getField(int id) {
			
			return 0;
		}

		@Override
		public void setField(int id, int value) {
			
			
		}

		@Override
		public int getFieldCount() {
			
			return 0;
		}

		@Override
		public void clear() {
			for(int i =0; i<inventorySlots.length; i++){
			
				inventorySlots[i] = null;
				
			}
			
		}
		
		@Override
		public ItemStack removeStackFromSlot(int index) {
			
			if(inventorySlots[index] == null)
				return null;
			
			ItemStack is =  inventorySlots[index].copy();
			
			inventorySlots[index] = null;
			 
			return is;
		}


		@Override
		public int getSizeInventory() {
			
			return 0;
		}


		@Override
		public void readFromNBT(NBTTagCompound compound) {
			
			super.readFromNBT(compound);
			readCustomNBT(compound);
		}


		@Override
		public void writeToNBT(NBTTagCompound compound) {
			
			super.writeToNBT(compound);
			writeCustomNBT(compound);
		}
		

		@Override
		public Packet getDescriptionPacket() {
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			writeCustomNBT(nbttagcompound);
			return new SPacketUpdateTileEntity(this.getPos(), -999, nbttagcompound);
		}

		@Override
		public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
			super.onDataPacket(net, packet);
			readCustomNBT(packet.getNbtCompound());
		}
		
}
