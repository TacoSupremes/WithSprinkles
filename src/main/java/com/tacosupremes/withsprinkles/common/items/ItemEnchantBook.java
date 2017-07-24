package com.tacosupremes.withsprinkles.common.items;

import java.util.List;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemEnchantBook extends ItemMod
{

	public ItemEnchantBook()
	{
		super("enchantBook");

	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{

		super.addInformation(stack, worldIn, tooltip, flagIn);

		if (!stack.hasTagCompound() || (!stack.getTagCompound().getCompoundTag("display").hasKey("Name")))
			tooltip.add(I18n.translateToLocal(LibMisc.MODID + ".anvil"));
		else
		{
			tooltip.add(I18n.translateToLocal(LibMisc.MODID + ".enchantadd"));
		}
	}

}
