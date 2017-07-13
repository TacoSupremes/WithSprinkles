package com.tacosupremes.withsprinkles.common.items;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.lib.LibMisc;
import com.tacosupremes.withsprinkles.common.utils.ProxyRegistry;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemMod extends Item{
	
	public int meta;
	
	
	
	public ItemMod(String s, int meta){	
		super();
		this.setUnlocalizedName(s);
		this.setCreativeTab(WithSprinkles.tab);
		if(meta > 0)
			this.setHasSubtypes(true);
		ModItems.items.add(this);
		this.meta = meta;
	}

	
	public ItemMod(String s){
		this(s,0);
		
	}
	
	
	@Override
	public Item setUnlocalizedName(String name) {
		super.setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(LibMisc.MODID + ":" + name));
		ProxyRegistry.register(this);

		return this;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		
		if(stack.getItemDamage() > 0 && this.meta > 0)
			return super.getUnlocalizedName()+stack.getItemDamage();
		
		
		return super.getUnlocalizedName(stack);
	}



	@Override
	public String getItemStackDisplayName(ItemStack stack) 
	{
		
		if(this.meta == 0 || needsDifferentNames())
			return super.getItemStackDisplayName(stack);
		
		return I18n.translateToLocal(this.getUnlocalizedName(stack).replace(String.valueOf(stack.getMetadata()), "")+".name");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		
		if(!needsDifferentNames())
			super.getSubItems(tab, items);
			else{
				
				for(int i = 0; i<=meta; i++){
					items.add(new ItemStack(this,1,i));
				}
				
				
			}
	
	}

	public boolean needsDifferentNames()
	{		
		return false;
	}


	public boolean skipVariants()
	{
		return false;
	}
	
	public boolean hasOneModel()
	{
		return false;
	}
	
	
	public IItemColor getColor()
	{	
		return null;
	}

}
