package com.tacosupremes.withsprinkles.common.blocks;

import com.tacosupremes.withsprinkles.common.blocks.tiles.TileAutoDropper;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAutoDropper extends BlockModContainer {
	
	  public static final PropertyDirection FACING = BlockDirectional.FACING;
	   

	public BlockAutoDropper() {
		super(Material.rock,"autoDropper");
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		 
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileAutoDropper();
	}

	@Override
	protected Class<? extends TileEntity> tile() {
		
		return TileAutoDropper.class;
	}
	
	  public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	    {
	        super.onBlockAdded(worldIn, pos, state);
	        this.setDefaultDirection(worldIn, pos, state);
	        System.out.println(getUnlocalizedName()+">>>>>>"+this.getLocalizedName());
	    }

	    private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state)
	    {
	        if (!worldIn.isRemote)
	        {
	            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
	            boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
	            boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();

	            if (enumfacing == EnumFacing.NORTH && flag && !flag1)
	            {
	                enumfacing = EnumFacing.SOUTH;
	            }
	            else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag)
	            {
	                enumfacing = EnumFacing.NORTH;
	            }
	            else
	            {
	                boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
	                boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();

	                if (enumfacing == EnumFacing.WEST && flag2 && !flag3)
	                {
	                    enumfacing = EnumFacing.EAST;
	                }
	                else if (enumfacing == EnumFacing.EAST && flag3 && !flag2)
	                {
	                    enumfacing = EnumFacing.WEST;
	                }
	            }

	            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
	            
	        }
	    }
	    
	    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        TileEntity tileentity = worldIn.getTileEntity(pos);

	        if (tileentity instanceof TileAutoDropper)
	        {
	            InventoryHelper.dropInventoryItems(worldIn, pos, (TileAutoDropper)tileentity);
	            worldIn.updateComparatorOutputLevel(pos, this);
	        }

	        super.breakBlock(worldIn, pos, state);
	    }
	    
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }
	    
	    public static EnumFacing getFacing(int meta)
	    {
	        return EnumFacing.getFront(meta & 7);
	    }

	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(FACING, getFacing(meta));
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;
	        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

	      

	        return i;
	    }

	    /**
	     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     */
	    public IBlockState withRotation(IBlockState state, Rotation rot)
	    {
	        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	    }

	    /**
	     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     */
	    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {FACING});
	    }
	    
	    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, BlockPistonBase.getFacingFromEntity(pos, placer));
	    }

	    /**
	     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	     */
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FACING, BlockPistonBase.getFacingFromEntity(pos, placer)), 2);

	        
	    }

		@Override
		public String getLocalizedName() {
			// TODO Auto-generated method stub
			return super.getLocalizedName();
		}



}
