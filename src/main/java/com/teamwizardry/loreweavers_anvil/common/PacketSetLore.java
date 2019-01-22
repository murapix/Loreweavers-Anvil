package com.teamwizardry.loreweavers_anvil.common;

import java.util.UUID;

import com.teamwizardry.librarianlib.features.network.PacketBase;
import com.teamwizardry.librarianlib.features.saving.Save;
import com.teamwizardry.loreweavers_anvil.client.ContainerLore;

import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetLore extends PacketBase
{
	@Save
	public String text;
	@Save
	public UUID playerID;
	
	public PacketSetLore(){}
	
	public PacketSetLore(String text, UUID playerID)
	{
		this.text = text;
		this.playerID = playerID;
	}
	
	@Override
	public void handle(MessageContext context)
	{
		if (context.side.isClient()) return;
		ContainerLore.setLoreText(text, playerID);
	}

}
