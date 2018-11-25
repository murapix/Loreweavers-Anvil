package com.teamwizardry.loreweavers_anvil.client.container;

import com.teamwizardry.librarianlib.features.container.ContainerBase;
import com.teamwizardry.librarianlib.features.container.InventoryWrapper;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers.InventoryWrapperPlayer;
import com.teamwizardry.loreweavers_anvil.client.AnvilInventoryHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class ContainerLore extends ContainerBase
{
	public World world;
	public BlockPos pos;
	public final InventoryWrapperPlayer playerWrapper;
	public final InventoryWrapper anvilWrapper;
	public final IItemHandler anvilHandler;
	
	public ContainerLore(EntityPlayer player, World world, BlockPos pos)
	{
		super(player);
		this.world = world;
		this.pos = pos;
		this.anvilHandler = new AnvilInventoryHandler();
		this.anvilWrapper = BaseWrappers.INSTANCE.stacks(anvilHandler);
		this.playerWrapper = BaseWrappers.INSTANCE.player(player);
		this.addSlots(playerWrapper);
		this.addSlots(anvilWrapper);
		
		this.transferRule().from(playerWrapper.getMain()).deposit(anvilWrapper.getSlotArray().get(0));
		this.transferRule().from(anvilWrapper.getSlotArray()).deposit(playerWrapper.getMain());
	}
}
