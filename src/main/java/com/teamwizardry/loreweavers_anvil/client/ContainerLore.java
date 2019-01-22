package com.teamwizardry.loreweavers_anvil.client;

import java.util.stream.Stream;

import com.teamwizardry.librarianlib.features.container.ContainerBase;
import com.teamwizardry.librarianlib.features.container.GuiHandler;
import com.teamwizardry.librarianlib.features.container.InventoryWrapper;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers;
import com.teamwizardry.librarianlib.features.container.builtin.BaseWrappers.InventoryWrapperPlayer;
import com.teamwizardry.loreweavers_anvil.LoreweaversAnvil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ContainerLore extends ContainerBase
{
	public EntityPlayer player;
	public World world;
	public BlockPos pos;
	public final InventoryWrapperPlayer playerWrapper;
	public final InventoryWrapper anvilWrapper;
	public final IItemHandler anvilHandler;
	public static final ResourceLocation containerLoc;
	
	static
	{
		containerLoc = new ResourceLocation(LoreweaversAnvil.MOD_ID, "anvil");
		GuiHandler.registerRaw(containerLoc, 
				(player, world, pos) -> {
					ContainerLore container = new ContainerLore(player, world, pos);
					LoreweaversAnvil.proxy.playerMap.put(player.getUniqueID(), container);
					return container;
				},
				(player, world, pos) -> {
					ContainerLore container = new ContainerLore(player, world, pos);
					LoreweaversAnvil.proxy.playerMap.put(player.getUniqueID(), container);
					return new GuiLore(container);
				});
	}
	
	public ContainerLore(EntityPlayer player, World world, BlockPos pos)
	{
		super(player);
		this.player = player;
		this.world = world;
		this.pos = pos;
		this.anvilHandler = new AnvilInventoryHandler();
		this.anvilWrapper = BaseWrappers.INSTANCE.stacks(anvilHandler);
		this.playerWrapper = BaseWrappers.INSTANCE.player(player);
		this.addSlots(playerWrapper);
		this.addSlots(anvilWrapper);
		
		this.transferRule().from(playerWrapper.getMain()).deposit(anvilWrapper.getSlotArray().get(0));
		this.transferRule().from(playerWrapper.getHotbar()).deposit(anvilWrapper.getSlotArray().get(0));
		this.transferRule().from(anvilWrapper.getSlotArray()).deposit(playerWrapper.getHotbar()).deposit(playerWrapper.getMain());
	}
	
	@Override
	public void onClosed()
	{
		if (!anvilHandler.getStackInSlot(0).isEmpty())
			ItemHandlerHelper.giveItemToPlayer(player, anvilHandler.getStackInSlot(0));
		LoreweaversAnvil.proxy.playerMap.remove(player.getUniqueID());
	}
	
	public static void setLoreText(String text, ItemStack stack)
	{
		String[] lines = Stream.of(text.split("\n")).filter(str -> !str.isEmpty()).toArray(String[]::new);
		if (lines.length == 0)
			return;
		
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null)
			stack.setTagCompound(tag = new NBTTagCompound());
		
		NBTTagList lore = new NBTTagList();
		for (String line : lines)
			lore.appendTag(new NBTTagString(line));
		
		NBTTagCompound display = tag.getCompoundTag("display");
		display.setTag("lore", lore);
		tag.setTag("display", display);
	}
}
