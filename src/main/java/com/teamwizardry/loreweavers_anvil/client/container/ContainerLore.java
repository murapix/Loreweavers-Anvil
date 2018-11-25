package com.teamwizardry.loreweavers_anvil.client.container;

import com.teamwizardry.librarianlib.features.container.ContainerBase;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers;
import com.teamwizardry.loreweavers_anvil.client.AnvilInventoryHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class ContainerLore extends ContainerBase
{
	public InventoryPlayer inventory;
	public World world;
	public BlockPos pos;
	public final IItemHandler anvilHandler;
	
	public ContainerLore(EntityPlayer player, World world, BlockPos pos)
	{
		super(player);
		this.inventory = player.inventory;
		this.world = world;
		this.pos = pos;
		this.anvilHandler = new AnvilInventoryHandler();
		this.addSlots(BaseWrappers.INSTANCE.player(player));
		this.addSlots(BaseWrappers.INSTANCE.stacks(anvilHandler));
	}
}
