package com.tacosupremes.withsprinkles.common.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ModItems
{

	public static List<ItemMod> items = new ArrayList<ItemMod>();

	public static List<Item> nitems = new ArrayList<Item>();

	public static Item refillGem;

	// public static Item disEnchanter;

	// public static Item oreCompass;

	// public static Item enchantBook;
	
	// public static Item fishingStuff; 

	public static Item portableEnderChest;

	public static Item oldPaper;



	public static void preInit()
	{

		refillGem = new ItemRefiller();

		portableEnderChest = new ItemPortableEnderChest();

		oldPaper = new ItemOldPaper();
	}
	
	public static void register()
	{
		for(ItemMod i : items)
		{		
			if(WithSprinkles.config.isItemEnabled(i))
				ProxyRegistry.register((Item)i);
		}
	}

	public static void registerRenders()
	{

		for (ItemMod i : items)
		{

			if (i.getColor() != null)
				Minecraft.getMinecraft().getItemColors().registerItemColorHandler(i.getColor(), i);

			if (i.meta != 0)
			{

				if (i.hasOneModel())
				{

					for (int i2 = 0; i2 < i.meta + 1; i2++)
						registerItemRenderSameModel(i, i2);

					continue;
				}

				ResourceLocation[] s = new ResourceLocation[i.meta + 1];

				for (int i2 = 0; i2 < i.meta + 1; i2++)
					s[i2] = new ResourceLocation(i.getRegistryName().toString() + (i2 == 0 ? "" : i2));

				ModelBakery.registerItemVariants(i, s);

				if (!i.skipVariants())
				{
					for (int i2 = 0; i2 <= i.meta; i2++)
						ModItems.registerItemRender(i, i2);

				}

			}

			if (i.meta == 0 || i.skipVariants())
				registerItemRender(i, 0);
		}

		for (Item i : nitems)
			registerItemRender(i, 0);

	}

	public static void registerItemRender(Item i, int meta)
	{

		if (i == null)
			return;

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, meta, new ModelResourceLocation(LibMisc.MODID+":"+i.getUnlocalizedName().substring(5)+ (meta == 0 ? "" : String.valueOf(meta)), "inventory"));
		  
	}

	public static void registerItemRenderSameModel(Item i, int meta)
	{

		if (i == null)
			return;

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, meta, new ModelResourceLocation(LibMisc.MODID+":"+i.getUnlocalizedName().substring(5), "inventory"));
	  	
	}

	

	public static void registerItemMetas(Item item, int maxExclusive, IntFunction<String> metaToName)
	{
		for (int i = 0; i < maxExclusive; i++)
		{
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(LibMisc.MODID + ":" + metaToName.apply(i), "inventory"));
		}
	}

}
