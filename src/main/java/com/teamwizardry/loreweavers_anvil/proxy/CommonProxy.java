package com.teamwizardry.loreweavers_anvil.proxy;

import com.teamwizardry.loreweavers_anvil.LoreweaversAnvil;
import com.teamwizardry.loreweavers_anvil.client.GuiHandler;
import com.teamwizardry.loreweavers_anvil.init.BlockRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy
{
	public void onPreInit(FMLPreInitializationEvent event)
	{
		BlockRegistry.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(LoreweaversAnvil.instance, new GuiHandler());
	}
	
	public void onInit(FMLInitializationEvent event)
	{}
	
	public void onPostInit(FMLPostInitializationEvent event)
	{}
}
