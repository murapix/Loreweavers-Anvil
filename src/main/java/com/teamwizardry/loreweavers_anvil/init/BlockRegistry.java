package com.teamwizardry.loreweavers_anvil.init;

import com.teamwizardry.loreweavers_anvil.common.blocks.BlockLoreAnvil;

public class BlockRegistry
{
	public static BlockLoreAnvil anvil;

	public static void init()
	{
		anvil = new BlockLoreAnvil();
	}
}
