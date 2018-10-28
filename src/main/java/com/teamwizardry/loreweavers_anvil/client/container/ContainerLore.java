package com.teamwizardry.loreweavers_anvil.client.container;

import com.teamwizardry.librarianlib.features.container.ContainerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerLore extends ContainerBase
{
	public InventoryPlayer inventory;
	public World world;
	public BlockPos pos;
	
	public ContainerLore(EntityPlayer player, World world, BlockPos pos)
	{
		super(player);
		this.inventory = player.inventory;
		this.world = world;
		this.pos = pos;
	}
}
