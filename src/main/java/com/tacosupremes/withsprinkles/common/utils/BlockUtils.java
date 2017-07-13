package com.tacosupremes.withsprinkles.common.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {
	
public static IBlockState fromItemStack(ItemStack is)
{
	
	Block b = Block.getBlockFromItem(is.getItem());
	
	if(b == null)
		return null;
	
	return b.getStateFromMeta(is.getItemDamage());
	
}

public static ItemStack toItemStack(IBlockState s)
{
	return new ItemStack(s.getBlock(),1,s.getBlock().getMetaFromState(s));
}

public static int getMeta(World w, BlockPos pos)
{	
	return w.getBlockState(pos).getBlock().getMetaFromState(w.getBlockState(pos));	
}

public static boolean equals(IBlockState i, IBlockState i2)
{	
	return i.getBlock() == i2.getBlock() && i.getBlock().getMetaFromState(i) == i2.getBlock().getMetaFromState(i2);	
}

public static List<BlockPos> getConnectedBlocks(World w, BlockPos start)
{
	List<BlockPos> l = new ArrayList<BlockPos>();
	
	List<String> checked = new ArrayList<String>();
	
	List<BlockPos> toCheck = new ArrayList<BlockPos>();
	
	IBlockState ib = w.getBlockState(start);
	
	l.add(start);
	
	toCheck.add(start);
	
	checked.add(start.toString());
	
	while(!toCheck.isEmpty())
	{
		
		BlockPos pos = toCheck.remove(0);

		for(EnumFacing f : EnumFacing.VALUES)
		{
		
			BlockPos pos_ = pos.add(f.getDirectionVec());
		
			IBlockState ib_ = w.getBlockState(pos_);	
		
			if(BlockUtils.equals(ib, ib_) && !checked.contains(pos_.toString()))
			{
			
				l.add(pos_);
				
				checked.add(pos_.toString());
				
				toCheck.add(pos_);
			
			}
		
			
		}
	}
	
	return l;
}

public static List<BlockPos> getConnectedLogs(World w, BlockPos start)
{
	
	List<BlockPos> l = new ArrayList<BlockPos>();
	
	List<String> checked = new ArrayList<String>();
	
	List<BlockPos> toCheck = new ArrayList<BlockPos>();
	
	IBlockState ib = w.getBlockState(start);
	
	l.add(start);
	
	toCheck.add(start);
	
	checked.add(start.toString());
	
	while(!toCheck.isEmpty())
	{
		
		BlockPos pos = toCheck.remove(0);

		for(int x = -2; x <= 2; x++)
		{
			
			for(int y = -2; y <= 2; y++)
			{
				
				for(int z = -2; z <= 2; z++)
				{		
		
					BlockPos pos_ = pos.add(x,y,z);
		
					IBlockState ib_ = w.getBlockState(pos_);	
		
					if(ib_.getBlock().isWood(w, pos_) && !checked.contains(pos_.toString()))
					{
			
						l.add(pos_);
						
						checked.add(pos_.toString());
						
						toCheck.add(pos_);
			
					}
				}
			}
		}
	}
	
	return l;
}


}
