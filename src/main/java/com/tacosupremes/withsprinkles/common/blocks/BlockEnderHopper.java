package com.tacosupremes.withsprinkles.common.blocks;

import java.util.List;

import com.google.common.base.Predicate;
import com.tacosupremes.withsprinkles.common.blocks.tiles.TileEnderHopper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnderHopper extends BlockModContainer {
	
	 public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
	    {
	        public boolean apply(EnumFacing p_apply_1_)
	        {
	            return p_apply_1_ != EnumFacing.UP;
	        }
	    });
	 
	 	protected static final AxisAlignedBB field_185571_c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
	    protected static final AxisAlignedBB field_185572_d = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	    protected static final AxisAlignedBB field_185573_e = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	    protected static final AxisAlignedBB field_185574_f = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	    protected static final AxisAlignedBB field_185575_g = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

	//TODO: Make GUI TO SELECT TARGET SLOT 
	public BlockEnderHopper() {
		super(Material.rock, "enderHopper");
		this.setHardness(0.1F);
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileEnderHopper();
	}

	@Override
	protected Class<? extends TileEntity> tile() {
		
		return TileEnderHopper.class;
	}

	

	@Override
	public void onBlockPlacedBy(World w, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		
		((TileEnderHopper)w.getTileEntity(pos)).pName = placer.getName();
	}
	
	
	  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        return FULL_BLOCK_AABB;
	    }

	    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB p_185477_4_, List<AxisAlignedBB> p_185477_5_, Entity p_185477_6_)
	    {
	        addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, field_185571_c);
	        addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, field_185575_g);
	        addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, field_185574_f);
	        addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, field_185572_d);
	        addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, field_185573_e);
	    }

	    /**
	     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	     * IBlockstate
	     */
	    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        EnumFacing enumfacing = facing.getOpposite();

	        if (enumfacing == EnumFacing.UP)
	        {
	            enumfacing = EnumFacing.DOWN;
	        }

	        return this.getDefaultState().withProperty(FACING, enumfacing);
	    }

	  

	    /**
	     * Checks if an IBlockState represents a block that is opaque and a full cube.
	     *  
	     * @param state The block state to check.
	     */
	    public boolean isFullyOpaque(IBlockState state)
	    {
	        return true;
	    }

	    

	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	    {
	        if (worldIn.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityHopper)
	            {
	                playerIn.displayGUIChest((TileEntityHopper)tileentity);
	                playerIn.addStat(StatList.hopperInspected);
	            }

	            return true;
	        }
	    }

	    /**
	     * Called when a neighboring block changes.
	     */
	   

	    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        TileEntity tileentity = worldIn.getTileEntity(pos);

	        if (tileentity instanceof TileEntityHopper)
	        {
	            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityHopper)tileentity);
	            worldIn.updateComparatorOutputLevel(pos, this);
	        }

	        super.breakBlock(worldIn, pos, state);
	    }

	    /**
	     * The type of render function called. 3 for standard block models, 2 for TESR's, 1 for liquids, -1 is no render
	     */
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }

	    public boolean isFullCube(IBlockState state)
	    {
	        return false;
	    }

	    /**
	     * Used to determine ambient occlusion and culling when rebuilding chunks for render
	     */
	    public boolean isOpaqueCube(IBlockState state)
	    {
	        return false;
	    }

	    @SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	    {
	        return true;
	    }

	    public static EnumFacing getFacing(int meta)
	    {
	        return EnumFacing.getFront(meta & 7);
	    }

	    /**
	     * Get's the hopper's active status from the 8-bit of the metadata. Note that the metadata stores whether the block
	     * is powered, so this returns true when that bit is 0.
	     */
	    public static boolean isEnabled(int meta)
	    {
	        return (meta & 8) != 8;
	    }

	    public boolean hasComparatorInputOverride(IBlockState state)
	    {
	        return true;
	    }

	    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	    {
	        return Container.calcRedstone(worldIn.getTileEntity(pos));
	    }

	    @SideOnly(Side.CLIENT)
	    public BlockRenderLayer getBlockLayer()
	    {
	        return BlockRenderLayer.CUTOUT_MIPPED;
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

}
