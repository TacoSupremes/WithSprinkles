package com.tacosupremes.withsprinkles.common.items;

import java.util.List;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemFilter extends ItemMod
{
	public ItemFilter()
	{
		super("filter");
	}

	@Override
	public void addInformation(ItemStack is, World w, List<String> tooltip, ITooltipFlag flagIn)
	{
		if(is.hasTagCompound())
		{
			NBTTagList var2 = is.getTagCompound().getTagList("Items", 10);
			
			for (int var3 = 0; var3 < var2.tagCount(); ++var3)
			{
				NBTTagCompound var4 = var2.getCompoundTagAt(var3);
					tooltip.add((new ItemStack(var4)).getDisplayName());
			}
		}
		else
		{
			tooltip.add(I18n.translateToLocal(LibMisc.MODID + ".empty"));	
		}
	}
	
	
	
}
