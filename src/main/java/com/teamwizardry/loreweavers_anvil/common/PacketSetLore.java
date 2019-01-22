package com.teamwizardry.loreweavers_anvil.common;

import com.teamwizardry.librarianlib.features.autoregister.PacketRegister;
import com.teamwizardry.librarianlib.features.network.PacketBase;
import com.teamwizardry.librarianlib.features.saving.Save;
import com.teamwizardry.loreweavers_anvil.client.ContainerLore;

import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

@PacketRegister(Side.SERVER)
public class PacketSetLore extends PacketBase
{
	@Save
	public String text;
	
	public PacketSetLore(){}
	
	public PacketSetLore(String text)
	{
		this.text = text;
	}
	
	@Override
	public void handle(MessageContext context)
	{
		Container container = context.getServerHandler().player.openContainer;
		ContainerLore.setLoreText(text, container.inventoryItemStacks.get(container.inventoryItemStacks.size()-1));
	}
}
