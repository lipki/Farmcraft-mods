package fr.farmcraft.loafofbread.common.breadoven;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMillstone extends Container {

	private TileEntityBreadOven millstone;
	private int lastMillstoneCookTime;
	private int lastMillstoneBurnTime;
	private int lastMillstoneItemBurnTime;

	public ContainerMillstone(InventoryPlayer inventoryPlayer, TileEntityBreadOven tileEntityMillstone) {
         lastMillstoneCookTime = 0;
         lastMillstoneBurnTime = 0;
         lastMillstoneItemBurnTime = 0;
         millstone = tileEntityMillstone;
         addSlotToContainer(new Slot(tileEntityMillstone, 0, 102, 17));
         addSlotToContainer(new Slot(tileEntityMillstone, 1, 56, 17));
         addSlotToContainer(new Slot(tileEntityMillstone, 2, 102, 53));
         addSlotToContainer(new Slot(tileEntityMillstone, 3, 56, 53));

         for (int i = 0; i < 3; i++)
             for (int k = 0; k < 9; k++)
                 addSlotToContainer(new Slot(inventoryPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));

         for (int j = 0; j < 9; j++)
             addSlotToContainer(new Slot(inventoryPlayer, j, 8 + j * 18, 142));
	}

	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        
        for (int var1 = 0; var1 < this.crafters.size(); ++var1) {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            
             if (this.lastMillstoneCookTime != this.millstone.cookTime)
                 var2.sendProgressBarUpdate(this, 0, this.millstone.cookTime);
             if (this.lastMillstoneBurnTime != this.millstone.burnTime)
                 var2.sendProgressBarUpdate(this, 1, this.millstone.burnTime);
             if (this.lastMillstoneItemBurnTime != this.millstone.itemBurnTime)
                 var2.sendProgressBarUpdate(this, 2, this.millstone.itemBurnTime);
         }
         this.lastMillstoneCookTime = this.millstone.cookTime;
         this.lastMillstoneBurnTime = this.millstone.burnTime;
         this.lastMillstoneItemBurnTime = this.millstone.itemBurnTime;
	}

	public void updateProgressBar(int par1, int par2) {
	     if (par1 == 0) millstone.cookTime = par2;
	     if (par1 == 1) millstone.burnTime = par2;
	     if (par1 == 2) millstone.itemBurnTime = par2;
	}

	public boolean canInteractWith(EntityPlayer entityPlayer) {
         return millstone.isUseableByPlayer(entityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber) {
         ItemStack itemstack = null;
         Slot slot = (Slot)inventorySlots.get(slotnumber);

         if (slot != null && slot.getHasStack()) {
    	 
             ItemStack itemstack1 = slot.getStack();
             itemstack = itemstack1.copy();

             if (slotnumber == 2) {
                 if (!mergeItemStack(itemstack1, 3, 39, true))
                         return null;

                 slot.onSlotChange(itemstack1, itemstack);
             } else if (slotnumber == 1 || slotnumber == 0) {
                 if (!mergeItemStack(itemstack1, 3, 39, false))
                     return null;
             } else if (RecipesMillstone.smelting().getSmeltingResult(itemstack1.getItem().itemID) != null) {
                 if (!mergeItemStack(itemstack1, 0, 1, false))
                     return null;
             } else if (TileEntityBreadOven.isItemFuel(itemstack1)) {
                 if (!mergeItemStack(itemstack1, 1, 2, false))
                     return null;
             } else if (slotnumber >= 3 && slotnumber < 30) {
                 if (!mergeItemStack(itemstack1, 30, 39, false))
                     return null;
             } else if (slotnumber >= 30 && slotnumber < 39 && !mergeItemStack(itemstack1, 3, 30, false))
                 return null;

             if (itemstack1.stackSize == 0)
                 slot.putStack(null);
             else slot.onSlotChanged();

             if (itemstack1.stackSize == itemstack.stackSize)
                 return null;
            
             slot.onPickupFromSlot(player, itemstack);
         }

         return itemstack;
	}
}
