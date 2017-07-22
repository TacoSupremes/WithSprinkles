package com.tacosupremes.withsprinkles.common.blocks.tiles;

import com.tacosupremes.withsprinkles.common.blocks.BlockRainDetector;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityRainDetector extends TileEntity implements ITickable{
	
	@Override
	 public void update()
	    {
	        if (this.world != null && !this.world.isRemote && this.world.getTotalWorldTime() % 20L == 0L)
	        {
	            this.blockType = this.getBlockType();

	            if (this.blockType instanceof BlockRainDetector)
	            {
	                ((BlockRainDetector)this.blockType).updatePower(this.world, this.pos);
	            }
	        }
	    }
	}


