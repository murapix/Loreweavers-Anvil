package com.teamwizardry.loreweavers_anvil.client;

import com.teamwizardry.loreweavers_anvil.client.container.ContainerLore;
import com.teamwizardry.loreweavers_anvil.client.gui.GuiLore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerLore(player, world, new BlockPos(x, y, z));
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiLore(new ContainerLore(player, world, new BlockPos(x, y, z)));
	}
}
