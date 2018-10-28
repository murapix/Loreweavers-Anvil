package com.teamwizardry.loreweavers_anvil.proxy;

import com.teamwizardry.loreweavers_anvil.init.BlockRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public void onPreInit(FMLPreInitializationEvent event)
	{
		BlockRegistry.init();
	}
	
	public void onInit(FMLInitializationEvent event)
	{}
	
	public void onPostInit(FMLPostInitializationEvent event)
	{}
}
