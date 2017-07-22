package com.tacosupremes.withsprinkles.common.items;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.tacosupremes.withsprinkles.common.lib.LibMisc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPortableEnderChest extends ItemMod {

	public ItemPortableEnderChest() {
		super("enderChest", 3);
		
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		
		
		if(player.getHeldItem(handIn) == null)
			 return super.onItemRightClick(worldIn, player, handIn);
		
		if(!(player.getHeldItem(handIn).getItem() instanceof ItemPortableEnderChest))
			return super.onItemRightClick(worldIn, player, handIn);
		
		
		EntityPlayer playerIn;
		if(player.getHeldItem(handIn).getItemDamage() == 0 || player.getHeldItem(handIn).getItemDamage() == 1)
		{
			playerIn = player;
	//		playerIn.getHeldItem(handIn).setItemDamage(1);
		}else{
			
		
			
			if(!player.getHeldItem(handIn).hasTagCompound()){
				player.getHeldItem(handIn).setTagCompound(new NBTTagCompound());
				
				player.getHeldItem(handIn).getTagCompound().setString("PLAYER", player.getUniqueID().toString());
				player.getHeldItem(handIn).getTagCompound().setString("PLAYERNAME", player.getName());
		
				
			}
			
			
			if (!worldIn.isRemote)
			playerIn = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(UUID.fromString(player.getHeldItem(handIn).getTagCompound().getString("PLAYER")));
			else
				return super.onItemRightClick(worldIn, player, handIn);
			
		//	player.getHeldItem(handIn).setItemDamage(3);
			
			
		}
			
		 
		 		 
		
		if (worldIn.isRemote)
            return super.onItemRightClick(worldIn, player, handIn);
        		
		
		
		if(playerIn != null)
		 player.displayGUIChest(playerIn.getInventoryEnderChest());
		
			
		//TODO: Figure how to make player affect inventory	meme(UUID.fromString(player.getHeldItem(handIn).getTagCompound().getString("PLAYER")), player);
			
		//	FMLCommonHandler.instance().getMinecraftServerInstance().sendMessage(new ITextComponent(){});
			
		
		 
		
		
		 
		return super.onItemRightClick(worldIn, player, handIn);
	}
	
	public void meme(UUID uuid, EntityPlayer player){
		
		
		
		SaveHandler saveHandler = (SaveHandler)FMLCommonHandler.instance().getMinecraftServerInstance().worlds[0].getSaveHandler();
		NBTTagCompound playerNbt = getPlayerNBT(saveHandler, uuid);
	
		if (playerNbt.hasKey("EnderItems", 9))
        {
            NBTTagList nbttaglist1 = playerNbt.getTagList("EnderItems", 10);
            
            InventoryEnderChest e = new InventoryEnderChest();
                   
            e.loadInventoryFromNBT(nbttaglist1);
            
            //TODO: Figure Out Solution to chest not syncing FIX THIS SOMEDAY
            
            player.displayGUIChest(e);
            
            player.getInventoryEnderChest().markDirty(); 
        
            playerNbt.setTag("EnderItems", e.saveInventoryToNBT());
           
           
        }
		
		
		
		
		try {
			
			File playersDirectory = new File(saveHandler.getWorldDirectory(), "playerdata");

		    File temp = new File(playersDirectory, uuid.toString() + ".dat.tmp");
		    File playerFile = new File(playersDirectory, uuid.toString() + ".dat");
		    CompressedStreamTools.writeCompressed(playerNbt, new FileOutputStream(temp));

		   if (playerFile.exists()) {
		        playerFile.delete();
		    }
		    temp.renameTo(playerFile);
		} catch (Exception e) {
			
			throw new NullPointerException("Player Does NOT EXIST RIP");
		  //  logger.warning("Failed to save player data for " + username);
		}
		
		
		
		
	}
	
	
	
	
	 public NBTTagCompound getPlayerNBT(SaveHandler saveHandler,UUID uuid)
	    {
		 
		  File playersDirectory = new File(saveHandler.getWorldDirectory(), "playerdata");
		 
	        try
	        {
	            File file1 = new File(playersDirectory, uuid.toString() + ".dat");

	            if (file1.exists() && file1.isFile())
	            {
	                return CompressedStreamTools.readCompressed(new FileInputStream(file1));
	            }
	        }
	        catch (Exception exception)
	        {
	          //  LOGGER.warn("Failed to load player data for " + player.getName());
	        }
	        return null;
	    }
	

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if((stack.getItemDamage() != 2 && stack.getItemDamage() != 3) || !stack.hasTagCompound())
			return;
		
		if(worldIn.isRemote)
			return;
		
		if(worldIn.getWorldTime() % 5 != 0)
			return;
		
	
		
		EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(UUID.fromString(stack.getTagCompound().getString("PLAYER")));
		
		if(player == null){
			
			if(stack.getItemDamage() != 2)
				stack.setItemDamage(2);
			
		}else{
			
			if(stack.getItemDamage() != 3)
				stack.setItemDamage(3);
			
		}
		
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {	
		if(stack.getItemDamage() < 2)
			return;
		
		if(!stack.hasTagCompound())
			return;
		
		tooltip.add(I18n.translateToLocal(LibMisc.MODID+".bound") + " "+ stack.getTagCompound().getString("PLAYERNAME"));
		
	
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}


	
	
	

}
