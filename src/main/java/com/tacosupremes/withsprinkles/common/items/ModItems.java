package com.tacosupremes.withsprinkles.common.items;

import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ModItems {
	

	public static List<ItemMod> items = new ArrayList<ItemMod>();
	
	public static List<Item> nitems = new ArrayList<Item>();

	public static void preInit() {
		
		
	}
	

	public static void registerRenders(){
		
		
		for(ItemMod i : items){
			
			if(i.meta !=0){
				
				ResourceLocation[] s = new ResourceLocation[i.meta+1];
				
				for(int i2 = 0; i2<i.meta+1;i2++){
					
					s[i2] = new ResourceLocation("runomancy:" + i.getUnlocalizedName().substring(5) +(i2 == 0 ? "" : i2));
					
				}
				
				
				ModelBakery.registerItemVariants(i, s);
				
				
				for(int i2 = 0; i2<=i.meta;i2++){
					ModItems.registerItemRender(i, i2);
					
				}
				
				
			}
			
			if(i.meta == 0)
				ModItems.registerItemRender(i, 0);
		}
		
		
		for(Item i : nitems){
			registerItemRender(i, 0);
		}
		
		
		
	}
	
	public static void registerItemRender(Item i, int meta){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, meta, new ModelResourceLocation(WithSprinkles.MODID+":"+i.getUnlocalizedName().substring(5)+ (meta == 0 ? "" : String.valueOf(meta)), "inventory"));
	}
	

}
