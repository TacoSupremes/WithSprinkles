package com.tacosupremes.withsprinkles.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.world.WorldEvent.Unload;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OfflinePlayerUtils
{
	
	private static Map<UUID, NBTTagCompound> map = new HashMap<UUID, NBTTagCompound>(); 
	
	private static Map<UUID, InventoryEnderChest> mapEnder = new HashMap<UUID, InventoryEnderChest>(); 
	
	private static Map<UUID, String> UUIDtoName = new HashMap<UUID, String>(); 
	

	private static void writeOfflinePlayerNBT(UUID uuid)
	{

		SaveHandler saveHandler = (SaveHandler)FMLCommonHandler.instance().getMinecraftServerInstance().worlds[0].getSaveHandler();
		
		try {
			
			File playersDirectory = new File(saveHandler.getWorldDirectory(), "playerdata");

		    File temp = new File(playersDirectory, uuid.toString() + ".dat.tmp");
		    File playerFile = new File(playersDirectory, uuid.toString() + ".dat");
		    CompressedStreamTools.writeCompressed(map.get(uuid), new FileOutputStream(temp));

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
	private static NBTTagCompound getOfflinePlayerNBT(UUID uuid)
	{
	
		if(map.containsKey(uuid))
			return map.get(uuid);		
		
		SaveHandler saveHandler = (SaveHandler)FMLCommonHandler.instance().getMinecraftServerInstance().worlds[0].getSaveHandler();
 
		File playersDirectory = new File(saveHandler.getWorldDirectory(), "playerdata");
 
		try
		{
			File file1 = new File(playersDirectory, uuid.toString() + ".dat");

			if (file1.exists() && file1.isFile())
			{	
				map.put(uuid, CompressedStreamTools.readCompressed(new FileInputStream(file1)));

				return map.get(uuid);
			}
		}
		catch (Exception e)
		{		
			WithSprinkles.logger.log(Level.WARNING, "Player NOT found with UUID" + uuid.toString(), e);
		}
		
		return null;
	}

	public static InventoryEnderChest getOfflineEnderChest(UUID uuid)
	{
		
		if(mapEnder.containsKey(uuid))
			return mapEnder.get(uuid);	
		
		NBTTagCompound nbt = getOfflinePlayerNBT(uuid);
		
		InventoryEnderChest ii = new InventoryEnderChest();
		
		ii.loadInventoryFromNBT(nbt.getTagList("EnderItems", 10));
		
		mapEnder.put(uuid, ii);
		
		return ii;
		
	}
		
	private static void saveOfflineEnderChest(UUID uuid)
	{
		NBTTagCompound nbt = OfflinePlayerUtils.getOfflinePlayerNBT(uuid);
		
		nbt.setTag("EnderItems", OfflinePlayerUtils.getOfflineEnderChest(uuid).saveInventoryToNBT());
		
		map.put(uuid, nbt);
		
		
		
	}
	
	private static void saveOfflineNBT(UUID uuid)
	{
		
		NBTTagCompound nbt = OfflinePlayerUtils.getOfflinePlayerNBT(uuid);
		
		map.put(uuid, nbt);
		
		writeOfflinePlayerNBT(uuid);
	
	}
	
	@SubscribeEvent
	public static void onPlayerJoinWorld(LoadFromFile event) {
		
		UUID uuid = event.getEntityPlayer().getUniqueID();		
		
			if(map.containsKey(uuid))
			{
				if(mapEnder.containsKey(uuid))
				{
					event.getEntityPlayer().getInventoryEnderChest().loadInventoryFromNBT(mapEnder.get(uuid).saveInventoryToNBT());
					mapEnder.remove(uuid);
				}
				map.remove(uuid);
			}
	}
	
	@SubscribeEvent
	public static void onWorldClose(Unload event)
	{
		
		for(UUID uuid : map.keySet())
		{
			
			if(map.containsKey(uuid))
			{
				if(mapEnder.containsKey(uuid))
					OfflinePlayerUtils.saveOfflineEnderChest(uuid);
				
				OfflinePlayerUtils.saveOfflineNBT(uuid);
				
			}
		}
	}

}
