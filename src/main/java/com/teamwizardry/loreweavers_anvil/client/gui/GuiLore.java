package com.teamwizardry.loreweavers_anvil.client.gui;

import java.util.stream.Stream;

import com.teamwizardry.librarianlib.features.container.InventoryWrapper;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers;
import com.teamwizardry.librarianlib.features.gui.component.GuiComponentEvents;
import com.teamwizardry.librarianlib.features.gui.components.ComponentSprite;
import com.teamwizardry.librarianlib.features.gui.components.ComponentTextField;
import com.teamwizardry.librarianlib.features.gui.components.ComponentVoid;
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
import scala.actors.threadpool.Arrays;

public class GuiLore extends GuiContainerBase
{
	private static final int WIDTH = 176;
	private static final int HEIGHT = 166;
	
	private static final Texture TEXTURE = new Texture(new ResourceLocation(LoreweaversAnvil.MOD_ID, "textures/gui/anvil.png"));
	private static final Sprite BACKGROUND = TEXTURE.getSprite("background", WIDTH, HEIGHT);
	private static final Sprite SELECTED = TEXTURE.getSprite("selected", 110, 64);
	private static final Sprite UNSELECTED = TEXTURE.getSprite("unselected", 110, 64);
	
	private static final IItemHandler anvilHandler = new IItemHandler() {
		public ItemStack[] slots = new ItemStack[]{ ItemStack.EMPTY, ItemStack.EMPTY };
		
		@Override
		public int getSlots()
		{
			return slots.length;
		}

		@Override
		public ItemStack getStackInSlot(int slot)
		{
			return slots[slot];
		}

		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
		{
			if (slot != 0)
				return stack;
			
			slots[0] = stack.copy();
			slots[1] = stack.copy();
			return ItemStack.EMPTY;
		}

		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate)
		{
			if (slot >= slots.length || slot < 0)
				throw new ArrayIndexOutOfBoundsException();
			ItemStack stack = slots[slot];
			slots = new ItemStack[2];
			return stack;
		}

		@Override
		public int getSlotLimit(int slot)
		{
			return 1;
		}
	};
	
	public GuiLore(ContainerLore container)
	{
		super(container, WIDTH, HEIGHT);
		
		PlayerLayout playerInv = new PlayerLayout(BaseWrappers.INSTANCE.player(container.getPlayer()));
		
		ComponentSprite background = new ComponentSprite(BACKGROUND, 0, 0);
		getMainComponents().add(background);
		background.add(playerInv.getRoot());
		playerInv.getMain().setPos(new Vec2d(8, 84));
		
		InventoryWrapper anvilInv = BaseWrappers.INSTANCE.stacks(anvilHandler);
		
		ComponentSlot input = new ComponentSlot(anvilInv.getSlotArray().get(0), 25, 9);
		ComponentSlot output = new ComponentSlot(anvilInv.getSlotArray().get(1), 25, 59);
		
		ComponentTextField text = new ComponentTextField(59, 10, 110, 64);
		
		text.BUS.hook(GuiComponentEvents.ComponentTickEvent.class, event -> {
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
		
		background.add(input, output, text);
	}
}
