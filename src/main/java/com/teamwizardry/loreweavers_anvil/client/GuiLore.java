package com.teamwizardry.loreweavers_anvil.client;

import java.util.UUID;

import com.teamwizardry.librarianlib.features.container.InventoryWrapper;
import com.teamwizardry.librarianlib.features.gui.component.GuiComponentEvents;
import com.teamwizardry.librarianlib.features.gui.components.ComponentSprite;
import com.teamwizardry.librarianlib.features.gui.components.ComponentTextField;
import com.teamwizardry.librarianlib.features.guicontainer.ComponentSlot;
import com.teamwizardry.librarianlib.features.guicontainer.GuiContainerBase;
import com.teamwizardry.librarianlib.features.guicontainer.builtin.BaseLayouts.PlayerLayout;
import com.teamwizardry.librarianlib.features.math.Vec2d;
import com.teamwizardry.librarianlib.features.network.PacketHandler;
import com.teamwizardry.librarianlib.features.sprite.Sprite;
import com.teamwizardry.librarianlib.features.sprite.Texture;
import com.teamwizardry.loreweavers_anvil.LoreweaversAnvil;
import com.teamwizardry.loreweavers_anvil.common.PacketSetLore;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;

public class GuiLore extends GuiContainerBase
{
	private static final int WIDTH = 176;
	private static final int HEIGHT = 166;
	
	private static final Texture TEXTURE = new Texture(new ResourceLocation(LoreweaversAnvil.MOD_ID, "textures/gui/anvil.png"));
	private static final Sprite BACKGROUND = TEXTURE.getSprite("background", WIDTH, HEIGHT);
//	private static final Sprite SELECTED = TEXTURE.getSprite("selected", 110, 64);
//	private static final Sprite UNSELECTED = TEXTURE.getSprite("unselected", 110, 64);
	
	public GuiLore(ContainerLore container)
	{
		super(container, WIDTH, HEIGHT);
		
		PlayerLayout playerInv = new PlayerLayout(container.playerWrapper);
		IItemHandler anvilHandler = container.anvilHandler;

		ComponentSprite background = new ComponentSprite(BACKGROUND, 0, 0);
		getMainComponents().add(background);
		background.add(playerInv.getRoot());
		playerInv.getMain().setPos(new Vec2d(8, 84));
		
		InventoryWrapper anvilInv = container.anvilWrapper;
		
		ComponentSlot input = new ComponentSlot(anvilInv.getSlotArray().get(0), 26, 10);
		ComponentSlot output = new ComponentSlot(anvilInv.getSlotArray().get(1), 26, 60);
		
		ComponentTextField text = new ComponentTextField(59, 10, 110, 64);
		
		text.BUS.hook(GuiComponentEvents.KeyUpEvent.class, event -> {
			if (anvilHandler.getStackInSlot(0).isEmpty())
				return;
			
			UUID playerID = container.player.getUniqueID();
			ContainerLore.setLoreText(text.getText(), playerID);
			PacketHandler.NETWORK.sendToServer(new PacketSetLore(text.getText(), playerID));
		});
		
		background.add(input, output, text);
	}
}
