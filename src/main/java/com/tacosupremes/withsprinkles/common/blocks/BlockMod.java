package com.tacosupremes.withsprinkles.common.blocks;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMod extends Block {

	public BlockMod(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setCreativeTab(WithSprinkles.tab);
		ModBlocks.blocks.add(this);
	}

	
	@Override
	public Block setUnlocalizedName(String name) {
		super.setUnlocalizedName(name);
		setRegistryName(LibMisc.MODID + ":" + name);
		ProxyRegistry.register(this);
		ProxyRegistry.register(new ItemBlock(this).setRegistryName(LibMisc.MODID + ":" + name));
		return this;
	}
	
}