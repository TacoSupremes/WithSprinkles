package com.tacosupremes.withsprinkles.common.blocks;

import com.tacosupremes.withsprinkles.common.blocks.tiles.TileEntityRainDetector;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRainDetector extends BlockModContainer {

	public BlockRainDetector() {
		super(Material.WOOD,"rainDetector");
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, Integer.valueOf(0)));
        this.setHardness(0.2F);
        this.setSoundType(SoundType.WOOD);
	}
	
	  public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
	  protected static final AxisAlignedBB DAYLIGHT_DETECTOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileEntityRainDetector();
	}
	
	
	public void updatePower(World w, BlockPos pos)
	{
	
		 IBlockState state = w.getBlockState(pos);
		
		if(w.isRaining() && w.getBiome(pos).canRain() && w.canBlockSeeSky(pos)){
			
			if(w.isThundering()){
				
				if((Integer)state.getValue(POWER).intValue() != 8)	
					w.setBlockState(pos, state.withProperty(POWER, 8), 3);
				return;
			}
			
			if((Integer)state.getValue(POWER).intValue() != 4){
				
				w.setBlockState(pos, state.withProperty(POWER, 4), 3);
				return;
			}			
			
		}else if((Integer)state.getValue(POWER).intValue() != 0)
			w.setBlockState(pos, state.withProperty(POWER, 0));
	}
	

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return DAYLIGHT_DETECTOR_AABB;
    }

    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return ((Integer)blockState.getValue(POWER)).intValue();
    }

	@Override
	protected Class<? extends TileEntity> tile() {
		
		return TileEntityRainDetector.class;
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

	    /**
	     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	     */
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }

	    public BlockRenderLayer getBlockLayer()
		{
	        return BlockRenderLayer.CUTOUT_MIPPED;
	    }

	    /**
	     * Can this block provide power. Only wire currently seems to have this change based on its state.
	     */
	    public boolean canProvidePower(IBlockState state)
	    {
	        return true;
	    }

	 

	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(POWER, Integer.valueOf(meta));
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    public int getMetaFromState(IBlockState state)
	    {
	        return ((Integer)state.getValue(POWER)).intValue();
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {POWER});
	    }


}
