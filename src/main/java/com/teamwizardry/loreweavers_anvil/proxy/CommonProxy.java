package com.teamwizardry.loreweavers_anvil.proxy;

import java.util.HashMap;
import java.util.UUID;

import com.teamwizardry.loreweavers_anvil.client.ContainerLore;
import com.teamwizardry.loreweavers_anvil.init.BlockRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public final HashMap<UUID, ContainerLore> playerMap = new HashMap<>();
	
	public void onPreInit(FMLPreInitializationEvent event)
	{
		BlockRegistry.init();
	}
	
	public void onInit(FMLInitializationEvent event)
	{}
	
	public void onPostInit(FMLPostInitializationEvent event)
	{}
}
