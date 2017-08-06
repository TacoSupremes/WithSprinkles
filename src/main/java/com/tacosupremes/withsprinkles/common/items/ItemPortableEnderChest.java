package com.tacosupremes.withsprinkles.common.items;

import java.util.List;
import java.util.UUID;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.gui.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPortableEnderChest extends ItemMod
{

	public ItemPortableEnderChest()
	{
		super("enderChest", 3);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
	{

		BlockPos pos = player.getPosition();

		if (player.getHeldItem(handIn) == null)
			return super.onItemRightClick(worldIn, player, handIn);

		if (player.getHeldItem(handIn).getItemDamage() == 0 || player.getHeldItem(handIn).getItemDamage() == 1)
			player.displayGUIChest(player.getInventoryEnderChest());
		else
		{

			if (!player.getHeldItem(handIn).hasTagCompound())
			{
				player.getHeldItem(handIn).setTagCompound(new NBTTagCompound());

				player.getHeldItem(handIn).getTagCompound().setString("PLAYER", player.getUniqueID().toString());
				player.getHeldItem(handIn).getTagCompound().setString("PLAYERNAME", player.getName());

			}

			if (!worldIn.isRemote)
				player.openGui(WithSprinkles.instance, GuiHandler.PORTABLE_ENDER_CHEST_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());

		}

		return super.onItemRightClick(worldIn, player, handIn);
	}

	public static UUID getUUID(ItemStack is)
	{

		if (is.hasTagCompound())
			return UUID.fromString(is.getTagCompound().getString("PLAYER"));

		return null;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	  {
		if (stack.getItemDamage() < 2)
			return;

		if (!stack.hasTagCompound())
			return;

		tooltip.add(I18n.translateToLocal(LibMisc.MODID + ".bound") + " " + stack.getTagCompound().getString("PLAYERNAME"));

		super.addInformation(stack, playerIn, tooltip, advanced);
	}

}
