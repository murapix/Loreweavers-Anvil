package com.teamwizardry.loreweavers_anvil.proxy;

import java.util.HashMap;
import java.util.UUID;

import com.teamwizardry.loreweavers_anvil.client.ContainerLore;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	public final HashMap<UUID, ContainerLore> playerMap = new HashMap<>();
	
	@Override
    public void onPreInit(FMLPreInitializationEvent event) {
        super.onPreInit(event);
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
        super.onInit(event);
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        super.onPostInit(event);
    }
}
