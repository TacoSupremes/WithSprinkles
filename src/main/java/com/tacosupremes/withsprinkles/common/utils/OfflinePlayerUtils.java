package com.tacosupremes.withsprinkles.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class OfflinePlayerUtils {
	
	
	public static void writeOfflinePlayerNBT(UUID uuid, NBTTagCompound playerNBT)
	{
		
		
		
		SaveHandler saveHandler = (SaveHandler)FMLCommonHandler.instance().getMinecraftServerInstance().worlds[0].getSaveHandler();
		
		
		
		
		try {
			
			File playersDirectory = new File(saveHandler.getWorldDirectory(), "playerdata");

		    File temp = new File(playersDirectory, uuid.toString() + ".dat.tmp");
		    File playerFile = new File(playersDirectory, uuid.toString() + ".dat");
		    CompressedStreamTools.writeCompressed(playerNBT, new FileOutputStream(temp));

		   if (playerFile.exists()) {
		        playerFile.delete();
		    }
		    temp.renameTo(playerFile);
		} catch (Exception e) {
			
			throw new NullPointerException("Player Does NOT EXIST RIP");
		  //  logger.warning("Failed to save player data for " + username);
		}
		
		
		
		
	}

	public static NBTTagCompound getOfflinePlayerNBT(UUID uuid)
	{
	
		SaveHandler saveHandler = (SaveHandler)FMLCommonHandler.instance().getMinecraftServerInstance().worlds[0].getSaveHandler();
 
		File playersDirectory = new File(saveHandler.getWorldDirectory(), "playerdata");
 
		try
		{
			File file1 = new File(playersDirectory, uuid.toString() + ".dat");

			if (file1.exists() && file1.isFile())	
				return CompressedStreamTools.readCompressed(new FileInputStream(file1));
			
		}
		catch (Exception exception)
		{
      //  LOGGER.warn("Failed to load player data for " + player.getName());
			
			throw new NullPointerException("Player Does NOT EXIST RIP");
		}
    return null;
}



}
