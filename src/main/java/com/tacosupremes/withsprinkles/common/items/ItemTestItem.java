package com.tacosupremes.withsprinkles.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTestItem extends ItemMod
{

	public ItemTestItem()
	{
		super("testItem", 3);
		this.setMaxDamage(0);

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{

		if (worldIn.getWorldTime() % 40 == 0)

			if (stack.getItemDamage() < 3)
				stack.setItemDamage(stack.getItemDamage() + 1);
			else
				stack.setItemDamage(0);
	}

}
