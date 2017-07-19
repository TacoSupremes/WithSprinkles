package com.tacosupremes.withsprinkles.common.blocks.tiles;

import java.util.UUID;

import com.tacosupremes.withsprinkles.common.utils.OfflinePlayerUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TileSharedEnderChest extends TileSimpleInventory
{

	public UUID uuid = null;
	
	@Override
	public int getSizeInventory() 
	{		
		return 27;
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) 
	{
		this.uuid = UUID.fromString(nbt.getString("PLAYER"));
		super.readCustomNBT(nbt);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt)
	{

		nbt.setString("PLAYER", uuid.toString());
		super.writeCustomNBT(nbt);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}
	
	
	
}
