package com.tacosupremes.withsprinkles.common.blocks.tiles;

import java.util.UUID;

import com.tacosupremes.withsprinkles.common.blocks.BlockEnderHopper;
import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.items.ModItems;
import com.tacosupremes.withsprinkles.common.utils.InventoryUtils;
import com.tacosupremes.withsprinkles.common.utils.OfflinePlayerUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TileEnderHopper extends TileSimpleInventory
{

	@Override
	public int getSizeInventory()
	{
		return 2;
	}

	public UUID uuid;

	int ticks = 0;
	
	
	public ItemStack[] cmpA = null;

	@Override
	public void update()
	{

		if (this.getWorld().isBlockPowered(pos))
			return;

		ticks++;

		if (ticks % 8 != 0)
			return;

		if (this.getWorld().isRemote)
			return;
		
		ItemStack cmp = this.getStackInSlot(1);
		
		if(cmp != ItemStack.EMPTY && cmp.getItem() == ModItems.filter && cmpA == null)
		{
			cmpA = new ItemStack[8];
		
			if(cmp.hasTagCompound())
			{
				NBTTagList var2 = cmp.getTagCompound().getTagList("Items", 10);
				
				for (int var3 = 0; var3 < var2.tagCount(); ++var3)
				{
					NBTTagCompound var4 = var2.getCompoundTagAt(var3);
						cmpA[var3] = new ItemStack(var4);
				}
			}
		}

		EnumFacing enumf = BlockEnderHopper.getFacing(this.getBlockMetadata());

		if (InventoryUtils.getInventory(getWorld(), getPos().up()) != null && (this.getWorld().getBlockState(getPos().up()).getBlock() != Blocks.ENDER_CHEST && this.getWorld().getBlockState(getPos().up()).getBlock() != ModBlocks.sharedEnderChest))
		{

			IInventory ii = InventoryUtils.getInventory(getWorld(), getPos().up());

			for (int i = 0; i < ii.getSizeInventory(); i++)
			{
				if (ii.getStackInSlot(i).isEmpty())
					continue;
				

				ItemStack is = ii.getStackInSlot(i);
				
				if(cmp != ItemStack.EMPTY)
				{	
					if(cmp.getItem() == ModItems.filter)
					{						
						boolean passed = false;
						
							for(ItemStack cmp2 : cmpA)
							{
								if(cmp2 == null || cmp2.isEmpty())
									continue;
								
								if(ItemStack.areItemsEqual(cmp2, is))
								{
									passed = true;
									break;
								}
							}
							
							if(!passed)
								continue;
					}
					else
					{
						if(!is.isItemEqual(cmp))
							continue;
					}
				}		
						
				if (this.getStackInSlot(0).isEmpty())
				{	
					this.setInventorySlotContents(0, ii.decrStackSize(i, 1));
				
					ii.markDirty();
					this.markDirty();
					break;
				}
			}

		}
		else if (this.getWorld().getBlockState(getPos().up()).getBlock() == Blocks.ENDER_CHEST)
		{
			EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(uuid);

			InventoryEnderChest ii = player == null ? OfflinePlayerUtils.getOfflineEnderChest(uuid) : player.getInventoryEnderChest();

			for (int i = 18; i < 27; i++)
			{
				if (ii.getStackInSlot(i).isEmpty())
					continue;
				
				ItemStack is = this.getStackInSlot(0);
				
				ItemStack is2 = ii.getStackInSlot(i);

				if(cmp != ItemStack.EMPTY)
				{
					
					if(cmp.getItem() == ModItems.filter)
					{						
						boolean passed = false;
						
							for(ItemStack cmp2 : cmpA)
							{
								if(cmp2 == null || cmp2.isEmpty())
									continue;
								
								if(ItemStack.areItemsEqual(cmp2, is2))
								{
									passed = true;
									break;
								}
							}
							
							if(!passed)
								continue;
						}
						else
						{
							if(!is.isItemEqual(cmp))
								continue;
						}
					
				}
				
				if (is == null || is.isEmpty())
				{
					this.setInventorySlotContents(0, ii.decrStackSize(i, 1));	
					ii.markDirty();
					this.markDirty();
					break;
				}

			}

		}
		else if (this.getWorld().getBlockState(getPos().up()).getBlock() == ModBlocks.sharedEnderChest)
		{
			
			UUID shared = ((TileSharedEnderChest) this.getWorld().getTileEntity(pos.up())).uuid;

			EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(shared);

			InventoryEnderChest ii = player == null ? OfflinePlayerUtils.getOfflineEnderChest(shared) : player.getInventoryEnderChest();

			for (int i = 18; i < 27; i++)
			{

				if (ii.getStackInSlot(i).isEmpty())
					continue;
				
				ItemStack is = this.getStackInSlot(0);
				
				ItemStack is2 = ii.getStackInSlot(i);

				if(cmp != ItemStack.EMPTY)
				{
					
					if(cmp.getItem() == ModItems.filter)
					{						
						boolean passed = false;
						
							for(ItemStack cmp2 : cmpA)
							{
								if(cmp2 == null || cmp2.isEmpty())
									continue;
								
								if(ItemStack.areItemsEqual(cmp2, is2))
								{
									passed = true;
									break;
								}
							}
							
							if(!passed)
								continue;
						}
						else
						{
							if(!is.isItemEqual(cmp))
								continue;
						}
				}
				
				if (is == null || is.isEmpty())
				{
					this.setInventorySlotContents(0, ii.decrStackSize(i, 1));
					ii.markDirty();
					this.markDirty();
					break;
				}

			}
			
		}
		
		if (this.getStackInSlot(0).isEmpty())
			return;
		
		if (InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())) != null)
		{
			TileEntityHopper.putStackInInventoryAllSlots(this, InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())), this.decrStackSize(0, 1), enumf.getOpposite());
			this.markDirty();
			InventoryUtils.getInventory(getWorld(), getPos().add(enumf.getDirectionVec())).markDirty();

		}
		else if (this.getWorld().getBlockState(getPos().add(enumf.getDirectionVec())).getBlock() == Blocks.ENDER_CHEST)
		{
			ItemStack is = this.getStackInSlot(0);

			EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(uuid);

			InventoryEnderChest ii = player == null ? OfflinePlayerUtils.getOfflineEnderChest(uuid) : player.getInventoryEnderChest();

			int slotChosen = -1;

			for (int i = 18; i < 27; i++)
			{

				if (!ii.getStackInSlot(i).isEmpty())
				{

					if (ii.getStackInSlot(i).getItem() == is.getItem() && ii.getStackInSlot(i).getItemDamage() == is.getItemDamage())
					{

						if (ii.getStackInSlot(i).getCount() == ii.getStackInSlot(i).getMaxStackSize())
							continue;

						if (is.getCount() + ii.getStackInSlot(i).getCount() <= is.getMaxStackSize())
						{

							ii.setInventorySlotContents(i, new ItemStack(is.getItem(), is.getCount() + ii.getStackInSlot(i).getCount(), is.getItemDamage()));
							this.setInventorySlotContents(0, ItemStack.EMPTY);
							ii.markDirty();
							this.markDirty();
							return;
						}
						else
						{

							ii.setInventorySlotContents(i, new ItemStack(is.getItem(), is.getMaxStackSize(), is.getItemDamage()));
							this.setInventorySlotContents(0, new ItemStack(is.getItem(), is.getCount() + ii.getStackInSlot(i).getCount() - is.getMaxStackSize(), is.getItemDamage()));
							ii.markDirty();
							this.markDirty();
							return;

						}

					}

				}
				else
				{
					slotChosen = i;
					break;
				}

			}

			if (slotChosen != -1)
			{
				ii.setInventorySlotContents(slotChosen, is);
				this.setInventorySlotContents(0, ItemStack.EMPTY);
				ii.markDirty();
				this.markDirty();
			}

		}
		else if (this.getWorld().getBlockState(getPos().add(enumf.getDirectionVec())).getBlock() == ModBlocks.sharedEnderChest)
		{
			ItemStack is = this.getStackInSlot(0);
			
			UUID shared = ((TileSharedEnderChest) this.getWorld().getTileEntity(getPos().add(enumf.getDirectionVec()))).uuid;


			EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(shared);

			InventoryEnderChest ii = player == null ? OfflinePlayerUtils.getOfflineEnderChest(shared) : player.getInventoryEnderChest();

			int slotChosen = -1;

			for (int i = 18; i < 27; i++)
			{

				if (!ii.getStackInSlot(i).isEmpty())
				{

					if (ii.getStackInSlot(i).getItem() == is.getItem() && ii.getStackInSlot(i).getItemDamage() == is.getItemDamage())
					{

						if (ii.getStackInSlot(i).getCount() == ii.getStackInSlot(i).getMaxStackSize())
							continue;

						if (is.getCount() + ii.getStackInSlot(i).getCount() <= is.getMaxStackSize())
						{

							ii.setInventorySlotContents(i, new ItemStack(is.getItem(), is.getCount() + ii.getStackInSlot(i).getCount(), is.getItemDamage()));
							this.setInventorySlotContents(0, ItemStack.EMPTY);
							ii.markDirty();
							this.markDirty();
							return;
						}
						else
						{

							ii.setInventorySlotContents(i, new ItemStack(is.getItem(), is.getMaxStackSize(), is.getItemDamage()));
							this.setInventorySlotContents(0, new ItemStack(is.getItem(), is.getCount() + ii.getStackInSlot(i).getCount() - is.getMaxStackSize(), is.getItemDamage()));
							ii.markDirty();
							this.markDirty();
							return;

						}

					}

				}
				else
				{
					slotChosen = i;
					break;
				}

			}

			if (slotChosen != -1)
			{
				ii.setInventorySlotContents(slotChosen, is);
				this.setInventorySlotContents(0, ItemStack.EMPTY);
				ii.markDirty();
				this.markDirty();
			}

		}

	}

	@Override
	public void readCustomNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.readCustomNBT(par1nbtTagCompound);

		this.uuid = UUID.fromString(par1nbtTagCompound.getString("PNAME"));

		this.ticks = par1nbtTagCompound.getInteger("TICKS");
		
		if(this.getStackInSlot(1) == null)
		{
			this.setInventorySlotContents(1, ItemStack.EMPTY);
		}
	}

	@Override
	public void writeCustomNBT(NBTTagCompound par1nbtTagCompound)
	{

		super.writeCustomNBT(par1nbtTagCompound);

		par1nbtTagCompound.setString("PNAME", this.uuid.toString());

		par1nbtTagCompound.setInteger("TICKS", this.ticks);
	}

}