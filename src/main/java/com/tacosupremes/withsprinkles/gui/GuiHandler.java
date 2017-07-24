package com.tacosupremes.withsprinkles.gui;

import com.tacosupremes.withsprinkles.common.blocks.tiles.TileSharedEnderChest;
import com.tacosupremes.withsprinkles.gui.container.ContainerPortableEnderChest;
import com.tacosupremes.withsprinkles.gui.container.ContainerSharedEnderChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	public static int SHARED_ENDER_CHEST_ID = 0;

	public static int PORTABLE_ENDER_CHEST_ID = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{

		if (ID == SHARED_ENDER_CHEST_ID)
			return new ContainerSharedEnderChest(player.inventory, (TileSharedEnderChest) world.getTileEntity(new BlockPos(x, y, z)), player);

		if (ID == PORTABLE_ENDER_CHEST_ID)
			return new ContainerPortableEnderChest(player.inventory, player.getHeldItem(player.getActiveHand()), player);

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{

		if (ID == SHARED_ENDER_CHEST_ID)
			return new GuiSharedEnderChest(player.inventory, (TileSharedEnderChest) world.getTileEntity(new BlockPos(x, y, z)));

		if (ID == PORTABLE_ENDER_CHEST_ID)
			return new GuiPortableEnderChest(player.inventory, new InventoryEnderChest());

		return null;
	}

}
