package com.tacosupremes.withsprinkles.common.blocks.tiles;

import java.util.UUID;

import com.tacosupremes.withsprinkles.common.blocks.ModBlocks;
import com.tacosupremes.withsprinkles.common.utils.OfflinePlayerUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TileSharedEnderChest extends TileSimpleInventory
{

	public UUID uuid = null;
	
	public float prevLidAngle;
	
	public float lidAngle;
	
	private int numPlayersUsing;
	
	private int ticksSinceSync;
	
	@Override
	public int getSizeInventory() 
	{		
		return 27;
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) 
	{
		this.uuid = UUID.fromString(nbt.getString("PLAYER"));
		super.readCustomNBT(nbt);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt)
	{
		nbt.setString("PLAYER", uuid.toString());
		super.writeCustomNBT(nbt);
	}



	public void openChest()
    {
        ++this.numPlayersUsing;
        this.world.addBlockEvent(this.pos, ModBlocks.sharedEnderChest, 1, this.numPlayersUsing);
    }

    public void closeChest()
    {
        --this.numPlayersUsing;
        this.world.addBlockEvent(this.pos, ModBlocks.sharedEnderChest, 1, this.numPlayersUsing);
    }
	
    public void update()
    {
        if (++this.ticksSinceSync % 20 * 4 == 0)
        {
            this.world.addBlockEvent(this.pos, ModBlocks.sharedEnderChest, 1, this.numPlayersUsing);
        }

        this.prevLidAngle = this.lidAngle;
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        float f = 0.1F;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
        {
            double d0 = (double)i + 0.5D;
            double d1 = (double)k + 0.5D;
            this.world.playSound((EntityPlayer)null, d0, (double)j + 0.5D, d1, SoundEvents.BLOCK_ENDERCHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f2 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f1 = 0.5F;

            if (this.lidAngle < 0.5F && f2 >= 0.5F)
            {
                double d3 = (double)i + 0.5D;
                double d2 = (double)k + 0.5D;
                this.world.playSound((EntityPlayer)null, d3, (double)j + 0.5D, d2, SoundEvents.BLOCK_ENDERCHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.numPlayersUsing = type;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, type);
        }
    }
}
