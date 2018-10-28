package com.teamwizardry.loreweavers_anvil.client.gui;

import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers.InventoryWrapperPlayer;
import com.teamwizardry.librarianlib.features.container.internal.SlotBase;
import com.teamwizardry.librarianlib.features.gui.components.ComponentSprite;
import com.teamwizardry.librarianlib.features.guicontainer.ComponentSlot;
import com.teamwizardry.librarianlib.features.guicontainer.GuiContainerBase;
import com.teamwizardry.librarianlib.features.guicontainer.builtin.BaseLayouts.PlayerLayout;
import com.teamwizardry.librarianlib.features.math.Vec2d;
import com.teamwizardry.librarianlib.features.sprite.Sprite;
import com.teamwizardry.librarianlib.features.sprite.Texture;
import com.teamwizardry.loreweavers_anvil.LoreweaversAnvil;
import com.teamwizardry.loreweavers_anvil.client.container.ContainerLore;

import net.minecraft.util.ResourceLocation;

public class GuiLore extends GuiContainerBase
{
	private static final int WIDTH = 176;
	private static final int HEIGHT = 166;
	private static final int SLOT_SIZE = 18;
	
	private static final Texture TEXTURE = new Texture(new ResourceLocation(LoreweaversAnvil.MOD_ID, "textures/gui/anvil"));
	private static final Sprite BACKGROUND = TEXTURE.getSprite("background", WIDTH, HEIGHT);
	private static final Sprite SLOT = TEXTURE.getSprite("slot", SLOT_SIZE, SLOT_SIZE);
	
	public GuiLore(ContainerLore container)
	{
		super(container, WIDTH, HEIGHT);
		
		PlayerLayout playerInv = new PlayerLayout(new InventoryWrapperPlayer(null, container.getPlayer()));
		
		ComponentSprite background = new ComponentSprite(BACKGROUND, 0, 0);
		getMainComponents().add(background);
		background.add(playerInv.getRoot());
		playerInv.getMain().setPos(new Vec2d(8, 84));
		
		background.add(new ComponentSprite(SLOT, 50, 33));
		background.add(new ComponentSprite(SLOT, 100, 33));
		
		ComponentSlot input = new ComponentSlot(new SlotBase(null, 0), 51, 34);
		ComponentSlot output = new ComponentSlot(new SlotBase(null, 1), 101, 34);
		
		background.add(input, output);
	}
}
