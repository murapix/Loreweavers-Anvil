package com.teamwizardry.loreweavers_anvil.init;

import com.teamwizardry.loreweavers_anvil.common.BlockLoreAnvil;

public class BlockRegistry
{
	public static BlockLoreAnvil anvil;

	public static void init()
	{
		anvil = new BlockLoreAnvil();
	}
}
