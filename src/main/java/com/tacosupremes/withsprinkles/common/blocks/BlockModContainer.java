package com.tacosupremes.withsprinkles.common.blocks;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BlockModContainer extends BlockContainer {

	public BlockModContainer(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setCreativeTab(WithSprinkles.tab);
		ModBlocks.blocks.add(this);
		this.setRegistryName(s);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(s));
		GameRegistry.registerTileEntity(tile(), s);
	}
	
	protected abstract Class<? extends TileEntity> tile();

	

	 public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }
	
	

}
