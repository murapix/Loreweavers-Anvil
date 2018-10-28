package com.teamwizardry.loreweavers_anvil;

import org.apache.logging.log4j.Logger;

import com.teamwizardry.loreweavers_anvil.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LoreweaversAnvil.MOD_ID, name = LoreweaversAnvil.MOD_NAME, version = LoreweaversAnvil.MOD_VERSION, dependencies = LoreweaversAnvil.DEPENDENCIES)
public class LoreweaversAnvil
{
	public static final String MOD_ID =  "loreweavers_anvil";
	public static final String MOD_NAME = "Loreweaver's Anvil";
	public static final String MOD_VERSION = "GRADLE:VERSION";
	public static final String CLIENT_PROXY = "com.teamwizardry.magnetrings.proxy.ClientProxy";
	public static final String SERVER_PROXY = "com.teamwizardry.magnetrings.proxy.ServerProxy";
	public static final String DEPENDENCIES = "required-after:librarianlib"/*, required-after:quaeritum"*/;
	
	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
	public static CommonProxy proxy;
	@Mod.Instance(LoreweaversAnvil.MOD_ID)
	public static LoreweaversAnvil instance;
	
	public static Logger logger;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		proxy.onPreInit(event);
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		proxy.onInit(event);
	}

	@Mod.EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		proxy.onPostInit(event);
	}
}
