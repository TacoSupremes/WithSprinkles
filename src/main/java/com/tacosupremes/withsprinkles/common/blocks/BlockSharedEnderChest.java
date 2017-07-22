package com.tacosupremes.withsprinkles.common.blocks;

import java.util.Random;

import com.tacosupremes.withsprinkles.WithSprinkles;
import com.tacosupremes.withsprinkles.common.blocks.tiles.TileSharedEnderChest;
import com.tacosupremes.withsprinkles.gui.GuiHandler;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSharedEnderChest extends BlockModContainer {

	 public static final PropertyDirection FACING = BlockHorizontal.FACING;
	    protected static final AxisAlignedBB ENDER_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

	    protected BlockSharedEnderChest()
	    {
	        super(Material.ROCK,"sharedEnderChest");
	        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	        this.setHardness(0.7F);
	    }

	    @Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        return ENDER_CHEST_AABB;
	    }

	    /**
	     * Used to determine ambient occlusion and culling when rebuilding chunks for render
	     */
	    @Override
		public boolean isOpaqueCube(IBlockState state)
	    {
	        return false;
	    }

	    @Override
		public boolean isFullCube(IBlockState state)
	    {
	        return false;
	    }

	    @Override
		@SideOnly(Side.CLIENT)
	    public boolean hasCustomBreakingProgress(IBlockState state)
	    {
	        return true;
	    }

	    /**
	     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	     */
	    @Override
		public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	    }

	    /**
	     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	     * IBlockstate
	     */
	    @Override
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    }

	    /**
	     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	     */
	    @Override
		public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	         ((TileSharedEnderChest)worldIn.getTileEntity(pos)).uuid = placer.getUniqueID();
	    }

	    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
			return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    	
	    }
	    /**
	     * Called when the block is right clicked by a player.
	     */
	    @Override
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {

	             	if(!worldIn.isRemote)
	             		playerIn.openGui(WithSprinkles.instance, GuiHandler.SHARED_ENDER_CHEST_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());            	               		
	             	else
	             		((TileSharedEnderChest)worldIn.getTileEntity(pos)).openChest();
	                
	             	return true;
	            
	    }
	    

	    /**
	     * Returns a new instance of a block's tile entity class. Called on placing the block.
	     */
	    @Override
		public TileEntity createNewTileEntity(World worldIn, int meta)
	    {
	        return new TileSharedEnderChest();
	    }

	    @Override
		@SideOnly(Side.CLIENT)
	    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	    {
	        for (int i = 0; i < 3; ++i)
	        {
	            int j = rand.nextInt(2) * 2 - 1;
	            int k = rand.nextInt(2) * 2 - 1;
	            double d0 = pos.getX() + 0.5D + 0.25D * j;
	            double d1 = pos.getY() + rand.nextFloat();
	            double d2 = pos.getZ() + 0.5D + 0.25D * k;
	            double d3 = rand.nextFloat() * j;
	            double d4 = (rand.nextFloat() - 0.5D) * 0.125D;
	            double d5 = rand.nextFloat() * k;
	            worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
	        }
	    }

	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    @Override
		public IBlockState getStateFromMeta(int meta)
	    {
	        EnumFacing enumfacing = EnumFacing.getFront(meta);

	        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
	        {
	            enumfacing = EnumFacing.NORTH;
	        }

	        return this.getDefaultState().withProperty(FACING, enumfacing);
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    @Override
		public int getMetaFromState(IBlockState state)
	    {
	        return state.getValue(FACING).getIndex();
	    }

	    /**
	     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     */
	    @Override
		public IBlockState withRotation(IBlockState state, Rotation rot)
	    {
	        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	    }

	    /**
	     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     */
	    @Override
		public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	    }

	    @Override
		protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {FACING});
	    }

	    @Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }

		@Override
		protected Class<? extends TileEntity> tile() {
		
			return TileSharedEnderChest.class;
		}
	
		
	
}
