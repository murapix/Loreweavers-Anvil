package com.teamwizardry.loreweavers_anvil.common;

import com.teamwizardry.librarianlib.features.base.block.BlockModFalling;
import com.teamwizardry.librarianlib.features.container.GuiHandler;
import com.teamwizardry.loreweavers_anvil.client.ContainerLore;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLoreAnvil extends BlockModFalling
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	/**
	 *  Copy of the {@link BlockAnvil} values
	 */
	protected static final AxisAlignedBB X_AXIS_AABB = new AxisAlignedBB(0, 0, 0.125, 1, 1, 0.875);
	/**
	 *  Copy of the {@link BlockAnvil} values
	 */
	protected static final AxisAlignedBB Z_AXIS_AABB = new AxisAlignedBB(0.125, 0, 0, 0.875, 1, 1);
	
	/**
	 *  Copy of the {@link BlockAnvil} constructor
	 */
	public BlockLoreAnvil()
	{
		super("lore_anvil", Material.ANVIL);
		this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().rotateY());
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		GuiHandler.open(ContainerLore.containerLoc, player, pos);
		return true;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		EnumFacing facing = state.getValue(FACING);
		return facing.getAxis() == EnumFacing.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
	}
	
	@Override
	protected void onStartFalling(EntityFallingBlock entity)
	{
		entity.setHurtEntities(true);
	}
	
	@Override
	public void onEndFalling(World world, BlockPos pos, IBlockState fallingState, IBlockState hitState)
	{
		world.playEvent(1031, pos, 0);
	}
	
	@Override
	public void onBroken(World world, BlockPos pos)
	{
		world.playEvent(1029, pos, 0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side)
	{
		return true;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.getBlock() != this ? state : state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}
}
