package com.teamwizardry.loreweavers_anvil.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

public class AnvilInventoryHandler implements IItemHandlerModifiable
{
	public ItemStack[] slots = new ItemStack[2];
	
	public AnvilInventoryHandler()
	{
		for (int i = 0; i < slots.length; i++)
			slots[i] = ItemStack.EMPTY;
	}
	
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
		stack.shrink(1);
		if (stack.isEmpty())
			stack = ItemStack.EMPTY;
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		if (slot >= slots.length || slot < 0)
			throw new ArrayIndexOutOfBoundsException();
		ItemStack stack = slots[slot];
		for (int i = 0; i < slots.length; i++)
			slots[i] = ItemStack.EMPTY;
		return stack;
	}

	@Override
	public int getSlotLimit(int slot)
	{
		return 1;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack)
	{
		insertItem(slot, stack, false);
	}

}
