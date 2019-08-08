package com.tacosupremes.withsprinkles.common.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.tacosupremes.withsprinkles.common.blocks.tiles.TileEnderHopper;
import com.tacosupremes.withsprinkles.common.blocks.tiles.TileSimpleInventory;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
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

public class BlockEnderHopper extends BlockModContainer
{

	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
	{
		@Override
		public boolean apply(EnumFacing p_apply_1_)
		{
			return p_apply_1_ != EnumFacing.UP;
		}
	});

	// TODO: Make GUI TO SELECT TARGET SLOT
	public BlockEnderHopper()
	{
		super(Material.ROCK, "enderHopper");
		this.setHardness(0.6F);

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{

		return new TileEnderHopper();
	}

	@Override
	protected Class<? extends TileEntity> tile()
	{

		return TileEnderHopper.class;
	}

	@Override
	public void onBlockPlacedBy(World w, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{

		((TileEnderHopper) w.getTileEntity(pos)).uuid = placer.getUniqueID();
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return FULL_BLOCK_AABB;
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
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
	 * Checks if an IBlockState represents a block that is opaque and a full
	 * cube.
	 * 
	 * @param state
	 *            The block state to check.
	 */
	public boolean isFullyOpaque(IBlockState state)
	{
		return true;
	}

	/**
	 * Called when a neighboring block changes.
	 */

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileSimpleInventory.breakBlock(worldIn, pos, state);

		super.breakBlock(worldIn, pos, state);
	}

	/**
	 * Get's the hopper's active status from the 8-bit of the metadata. Note
	 * that the metadata stores whether the block is powered, so this returns
	 * true when that bit is 0.
	 */

	protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
	{
		addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		EnumFacing enumfacing = facing.getOpposite();

		if (enumfacing == EnumFacing.UP)
		{
			enumfacing = EnumFacing.DOWN;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	/**
	 * Called when the block is right clicked by a player.
	 */
	@Override
	public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!player.getHeldItem(hand).isEmpty())
		{
			if(((TileEnderHopper) w.getTileEntity(pos)).getStackInSlot(1) == ItemStack.EMPTY)
			{
				((TileEnderHopper) w.getTileEntity(pos)).setInventorySlotContents(1, player.getHeldItem(hand).splitStack(1));		
				return true;
			}	
		}
		else if(player.isSneaking())
		{
			if(((TileEnderHopper) w.getTileEntity(pos)).getStackInSlot(1) != ItemStack.EMPTY)
			{		
				player.setHeldItem(hand, ((TileEnderHopper) w.getTileEntity(pos)).getStackInSlot(1).splitStack(1));
				((TileEnderHopper) w.getTileEntity(pos)).setInventorySlotContents(1, ItemStack.EMPTY);
				((TileEnderHopper) w.getTileEntity(pos)).cmpA = null;
				((TileEnderHopper) w.getTileEntity(pos)).markDirty();
				return true;
			}
		}
		
		return false;
	}

	/**
	 * The type of render function called. MODEL for mixed tesr and static
	 * model, MODELBLOCK_ANIMATED for TESR-only, LIQUID for vanilla liquids,
	 * INVISIBLE to skip all rendering
	 */
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
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
	 * Get's the hopper's active status from the 8-bit of the metadata. Note
	 * that the metadata stores whether the block is powered, so this returns
	 * true when that bit is 0.
	 */
	public static boolean isEnabled(int meta)
	{
		return (meta & 8) != 8;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, getFacing(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | state.getValue(FACING).getIndex();

		return i;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

}
