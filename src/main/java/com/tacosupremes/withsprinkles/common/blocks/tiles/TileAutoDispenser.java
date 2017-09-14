package com.tacosupremes.withsprinkles.common.blocks.tiles;

import com.tacosupremes.withsprinkles.common.blocks.BlockAutoDropper;
import com.tacosupremes.withsprinkles.common.utils.InventoryUtils;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.network.FluidIdRegistryMessageHandler;
import net.minecraftforge.fluids.DispenseFluidContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.FluidTankPropertiesWrapper;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public class TileAutoDispenser extends TileSimpleInventory 
{
	@Override
	public void update()
	{

		if (this.getStackInSlot(0) == null || this.getStackInSlot(0).isEmpty() || this.getWorld().isBlockPowered(pos))
			return;
	
		BlockSourceImpl blocksourceimpl = new BlockSourceImpl(world, pos);
	                
	    IBehaviorDispenseItem ibehaviordispenseitem = BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(this.getStackInSlot(0).getItem());
	     
	    if(ibehaviordispenseitem != IBehaviorDispenseItem.DEFAULT_BEHAVIOR)
	        this.setInventorySlotContents(0, ibehaviordispenseitem.dispense(blocksourceimpl, this.getStackInSlot(0)));
	    
	    if(isBucket(this.getStackInSlot(0)))
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
	}
	
	private boolean isBucket(ItemStack is)
	{
		if(is.isEmpty())
			return false;
		
		return is.getItem() == Items.BUCKET || is.getItem() == Items.LAVA_BUCKET || is.getItem() == Items.WATER_BUCKET || is.getItem() == new ItemStack(ForgeModContainer.getInstance().universalBucket).getItem();
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	
}
