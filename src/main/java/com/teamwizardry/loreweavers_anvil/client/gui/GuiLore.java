package com.teamwizardry.loreweavers_anvil.client.gui;

import java.util.stream.Stream;

import com.teamwizardry.librarianlib.features.container.InventoryWrapper;
import com.teamwizardry.librarianlib.features.gui.component.GuiComponentEvents;
import com.teamwizardry.librarianlib.features.gui.components.ComponentSprite;
import com.teamwizardry.librarianlib.features.gui.components.ComponentTextField;
import com.teamwizardry.librarianlib.features.guicontainer.ComponentSlot;
import com.teamwizardry.librarianlib.features.guicontainer.GuiContainerBase;
import com.teamwizardry.librarianlib.features.guicontainer.builtin.BaseLayouts.PlayerLayout;
import com.teamwizardry.librarianlib.features.math.Vec2d;
import com.teamwizardry.librarianlib.features.sprite.Sprite;
import com.teamwizardry.librarianlib.features.sprite.Texture;
import com.teamwizardry.loreweavers_anvil.LoreweaversAnvil;
import com.teamwizardry.loreweavers_anvil.client.container.ContainerLore;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;

public class GuiLore extends GuiContainerBase
{
	private static final int WIDTH = 176;
	private static final int HEIGHT = 166;
	
	private static final Texture TEXTURE = new Texture(new ResourceLocation(LoreweaversAnvil.MOD_ID, "textures/gui/anvil.png"));
	private static final Sprite BACKGROUND = TEXTURE.getSprite("background", WIDTH, HEIGHT);
	private static final Sprite SELECTED = TEXTURE.getSprite("selected", 110, 64);
	private static final Sprite UNSELECTED = TEXTURE.getSprite("unselected", 110, 64);
	
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
		
		ComponentSprite textBackground = new ComponentSprite(UNSELECTED, 59, 10);
		ComponentTextField text = new ComponentTextField(59, 10, 110, 64);
		textBackground.add(text);
		
//		text.BUS.hook(GuiComponentEvents.ComponentTickEvent.class, event -> {
//			textBackground.setSprite(text.isFocused() ? SELECTED : UNSELECTED);
//		});
		
		output.BUS.hook(GuiComponentEvents.MouseInEvent.class, event -> {
			if (anvilHandler.getStackInSlot(0).isEmpty())
				return;
			
			String[] strings = Stream.of(text.getText().split("\n")).filter(str -> !str.isEmpty()).toArray(String[]::new);
			if (strings.length == 0)
				return;
			
			ItemStack stack = anvilHandler.getStackInSlot(1);
			
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null)
				stack.setTagCompound(tag = new NBTTagCompound());
			
			NBTTagList lore = new NBTTagList();
			for (int i = 0; i < strings.length; i++)
				lore.appendTag(new NBTTagString(strings[i]));
			
			NBTTagCompound display = tag.getCompoundTag("display");
			display.setTag("Lore", lore);
			tag.setTag("display", display);
		});
		
		background.add(input, output, textBackground);
	}
}
