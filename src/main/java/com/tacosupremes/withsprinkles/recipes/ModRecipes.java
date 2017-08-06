package com.tacosupremes.withsprinkles.recipes;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class ModRecipes
{

	public static void preInit()
	{

		EnchantedBookRecipe r;
		
		if(WithSprinkles.config.isItemEnabled(ModItems.oldPaper))
			r = new EnchantedBookRecipe();
	}

}
