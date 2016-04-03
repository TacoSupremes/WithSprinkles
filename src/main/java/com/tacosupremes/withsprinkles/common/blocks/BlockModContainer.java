package com.tacosupremes.withsprinkles.common.blocks;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BlockModContainer extends BlockContainer {

	public BlockModContainer(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(WithSprinkles.MODID+":"+s);
		this.setRegistryName(s);
		this.setCreativeTab(WithSprinkles.tab);
		ModBlocks.blocks.add(this);
		GameRegistry.registerBlock(this, s);
		GameRegistry.registerTileEntity(tile(), s);
	}
	
	protected abstract Class<? extends TileEntity> tile();

	

	
	

}
