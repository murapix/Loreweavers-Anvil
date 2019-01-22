package com.teamwizardry.loreweavers_anvil.proxy;

import java.util.HashMap;
import java.util.UUID;

import com.teamwizardry.librarianlib.features.network.PacketHandler;
import com.teamwizardry.loreweavers_anvil.client.ContainerLore;
import com.teamwizardry.loreweavers_anvil.common.PacketSetLore;
import com.teamwizardry.loreweavers_anvil.init.BlockRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
	public final HashMap<UUID, ContainerLore> playerMap = new HashMap<>();
	
	public void onPreInit(FMLPreInitializationEvent event)
	{
		BlockRegistry.init();
		PacketHandler.register(PacketSetLore.class, Side.SERVER);
	}
	
	public void onInit(FMLInitializationEvent event)
	{}
	
	public void onPostInit(FMLPostInitializationEvent event)
	{}
}
