package com.tacosupremes.withsprinkles.common.blocks.tiles;

import com.tacosupremes.withsprinkles.common.blocks.BlockAutoDropper;
import com.tacosupremes.withsprinkles.common.utils.InventoryUtils;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileAutoDropper extends TileSimpleInventory implements ITickable, IInventory
{

	@Override
	public void update()
	{

		if (this.getStackInSlot(0) == null || this.getStackInSlot(0).isEmpty() || this.getWorld().isBlockPowered(pos))
			return;

		BlockPos bp = getPos().add(BlockAutoDropper.getFacing(this.getBlockMetadata()).getDirectionVec());

		if (InventoryUtils.getInventory(this.getWorld(), bp) == null)
		{

			EntityItem e = new EntityItem(this.getWorld());

			EnumFacing enumfacing = BlockAutoDropper.getFacing(getBlockMetadata());

			double d0 = this.getPos().getX() + 0.5D + 0.7D * enumfacing.getFrontOffsetX();
			double d1 = this.getPos().getY() + 0.2D + 0.7D * enumfacing.getFrontOffsetY();
			double d2 = this.getPos().getZ() + 0.5D + 0.7D * enumfacing.getFrontOffsetZ();

			if (enumfacing.getAxis() == EnumFacing.Axis.Y)
			{
				d1 = d1 - 0.125D;
			}
			else
			{
				d1 = d1 - 0.15625D;
			}

			if (enumfacing == EnumFacing.DOWN)
				d1 = this.getPos().getY() - 0.4D;

			e.setPosition(d0, d1, d2);

			double d3 = this.getWorld().rand.nextDouble() * 0.1D + 0.2D;
			int speed = 6;
			e.motionX = enumfacing.getFrontOffsetX() * d3;
			e.motionY = 0.20000000298023224D;
			e.motionZ = enumfacing.getFrontOffsetZ() * d3;
			e.motionX += 0.007499999832361937D * speed * enumfacing.getFrontOffsetX();
			e.motionY += 0.007499999832361937D * speed + (enumfacing.getAxis() == EnumFacing.Axis.Y ? 0.3D * (enumfacing == EnumFacing.UP ? 1 : -1) : 0);
			e.motionZ += 0.007499999832361937D * speed * enumfacing.getFrontOffsetZ();
			e.setItem(this.getStackInSlot(0));

			this.setInventorySlotContents(0, null);

			if (enumfacing.getAxis() == EnumFacing.Axis.Y)
			{
				e.motionX = 0;
				e.motionZ = 0;
			}

			if (!this.getWorld().isRemote)
				this.getWorld().spawnEntity(e);

		}
		else
		{

			EnumFacing enumfacing = BlockAutoDropper.getFacing(getBlockMetadata());

			IInventory t = InventoryUtils.getInventory(this.getWorld(), bp);

			ItemStack is = TileEntityHopper.putStackInInventoryAllSlots(this, t, this.getStackInSlot(0), enumfacing.getOpposite());

			this.setInventorySlotContents(0, is);
			this.markDirty();
		}
	}

	@Override
	public int getSizeInventory()
	{

		return 1;
	}

}
