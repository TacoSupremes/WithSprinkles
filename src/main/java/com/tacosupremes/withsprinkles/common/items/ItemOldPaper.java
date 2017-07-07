package com.tacosupremes.withsprinkles.common.items;

import java.util.Random;

import com.tacosupremes.withsprinkles.WithSprinkles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class ItemOldPaper extends ItemMod {

	public ItemOldPaper() {
		super("oldPaper");
	}
	
	
	
	
	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		
		IBlockState iblockstate = Blocks.CHEST.getDefaultState();
		world.setBlockState(pos, Blocks.CHEST.correctFacing(world, pos, iblockstate), 2);
        TileEntity tileentity = world.getTileEntity(pos);
        
        Random random = new Random();

        if (tileentity instanceof TileEntityChest)   
        {
        	/**Our Custom Loot**/
        	ResourceLocation location = WithSprinkles.oldPagesLoot;
        	
        	if(player.isSneaking()){
        		/**Our modified Spawn Chest Loot**/
        		location = LootTableList.CHESTS_SPAWN_BONUS_CHEST;
        	}        	
        	
            ((TileEntityChest)tileentity).setLootTable(location, random.nextLong());
        }
		
		
		return EnumActionResult.SUCCESS;
	}

}
