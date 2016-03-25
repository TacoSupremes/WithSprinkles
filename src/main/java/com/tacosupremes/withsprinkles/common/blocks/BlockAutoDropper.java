package com.tacosupremes.withsprinkles.common.blocks;

import com.tacosupremes.withsprinkles.common.blocks.tiles.TileAutoDropper;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAutoDropper extends BlockModContainer {

	public BlockAutoDropper() {
		super(Material.rock,"autoDropper");
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileAutoDropper();
	}

	@Override
	protected Class<? extends TileEntity> tile() {
		
		return TileAutoDropper.class;
	}

}
