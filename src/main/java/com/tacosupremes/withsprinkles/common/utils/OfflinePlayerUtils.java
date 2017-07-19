package com.tacosupremes.withsprinkles.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.logging.Level;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class OfflinePlayerUtils
{
	
	/**Writes an Offline Player's NBT**/
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
		}
		catch (Exception e) 
		{
			WithSprinkles.logger.log(Level.WARNING, "Player NOT found with UUID" + uuid.toString(), e);
		}
	
	}
	
	/**Gets an Offline Player's NBT**/
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
		catch (Exception e)
		{		
			WithSprinkles.logger.log(Level.WARNING, "Player NOT found with UUID" + uuid.toString(), e);
		}
		
		return null;
	}


	
	public static int requests = 0;
	
	private static boolean working = false;
	
	public static void work(){
		
		if(working)
			return;
		else
			working = true;
		while(requests > 0)
		{
			
		}
		
		
		
		
	}
	
	//TODO: Make queue

}
