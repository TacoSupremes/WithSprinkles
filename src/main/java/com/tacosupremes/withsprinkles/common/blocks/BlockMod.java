package com.tacosupremes.withsprinkles.common.blocks;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMod extends Block {

	public BlockMod(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setCreativeTab(WithSprinkles.tab);
		GameRegistry.registerBlock(this, s);
		ModBlocks.blocks.add(this);
	}

	
	
	
	
}