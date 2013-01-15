package fr.farmcraft.loafofbread.common.breadoven;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBreadOven extends TileEntity implements IInventory {

	public ItemStack inv[];
	public int burnTime;
	public int itemBurnTime;
	public int cookTime;
	public boolean isActive;
	
    public TileEntityBreadOven(){
        inv = new ItemStack[4];
        burnTime = 0;
        itemBurnTime = 0;
        cookTime = 0;
    }

	@Override
    public String getInvName() { return "Millstone"; }
	public int getSizeInventory() { return inv.length; }
	public ItemStack getStackInSlot(int par1) { return inv[par1]; }
	
	public ItemStack decrStackSize(int par1, int par2) {
         if (inv[par1] != null) {
             if (inv[par1].stackSize <= par2) {
                 ItemStack itemstack = inv[par1];
                 inv[par1] = null;
                 return itemstack;
             }

             ItemStack itemstack1 = inv[par1].splitStack(par2);

             if (inv[par1].stackSize == 0)
                 inv[par1] = null;

             return itemstack1;
         } else return null;
	}

	public ItemStack getStackInSlotOnClosing(int par1) {
         if (inv[par1] != null) {
                 ItemStack itemstack = inv[par1];
                 inv[par1] = null;
                 return itemstack;
         } else return null;
	}

	public void setInventorySlotContents(int par1, ItemStack itemStack) {
         inv[par1] = itemStack;

         if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
             itemStack.stackSize = getInventoryStackLimit();
         }
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
         super.readFromNBT(par1NBTTagCompound);
         NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
         inv = new ItemStack[getSizeInventory()];

         for (int i = 0; i < nbttaglist.tagCount(); i++) {
             NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
             byte byte0 = nbttagcompound.getByte("Slot");

             if (byte0 >= 0 && byte0 < inv.length)
                 inv[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
         }
        
         burnTime = par1NBTTagCompound.getShort("BurnTime");
         cookTime = par1NBTTagCompound.getShort("CookTime");
         itemBurnTime = getItemBurnTime(inv[1]);
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
         super.writeToNBT(par1NBTTagCompound);
         par1NBTTagCompound.setShort("BurnTime", (short)burnTime);
         par1NBTTagCompound.setShort("CookTime", (short)cookTime);
         NBTTagList nbttaglist = new NBTTagList();

         for (int i = 0; i < inv.length; i++) {
             if (inv[i] != null) {
                 NBTTagCompound nbttagcompound = new NBTTagCompound();
                 nbttagcompound.setByte("Slot", (byte)i);
                 inv[i].writeToNBT(nbttagcompound);
                 nbttaglist.appendTag(nbttagcompound);
             }
         }

         par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	public int getInventoryStackLimit() { return 64; }
	public int getCookProgressScaled(int par1) { return (cookTime * par1) / 200; }

	public int getBurnTimeRemainingScaled(int par1) {
         if (itemBurnTime == 0)
             itemBurnTime = 200;

         return (burnTime * par1) / itemBurnTime;
	}

	public boolean isBurning() { return burnTime > 0; }

	public void updateEntity() {
         boolean var1 = this.burnTime > 0;
         boolean var2 = false;
         if (this.burnTime > 0)
                 --this.burnTime;
         if (!this.worldObj.isRemote) {
             if (this.burnTime == 0 && this.canSmelt()) {
                 this.itemBurnTime = this.burnTime = getItemBurnTime(this.inv[1]);
                 if (this.burnTime > 0) {
                     var2 = true;
                     if (this.inv[1] != null) {
                         --this.inv[1].stackSize;
                         if (this.inv[1].stackSize == 0) {
                             Item var3 = this.inv[1].getItem().getContainerItem();
                             this.inv[1] = var3 == null ? null : new ItemStack(var3);
                         }
                     }
                 }
             }
             if (this.isBurning() && this.canSmelt()) {
                 ++this.cookTime;
                 if (this.cookTime == 200) {
                     this.cookTime = 0;
                     this.smeltItem();
                     var2 = true;
                 }
             }
             else this.cookTime = 0;
             if (var1 != this.burnTime > 0) {
                 var2 = true;
                 this.validate();
             }
         }
         boolean check = isActive;
         isActive = isBurning();
         if(isActive != check) this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
         if (var2) this.onInventoryChanged();
	}

	private boolean canSmelt() {
         if (inv[0] == null) return false;

         ItemStack[] itemstack = RecipesMillstone.smelting().getSmeltingResult(inv[0].getItem().itemID);

         if ( itemstack[0] == null || itemstack[1] == null ) return false;

         if (inv[2] == null || inv[3] == null) return true;

         if (!inv[2].isItemEqual(itemstack[0]) || !inv[3].isItemEqual(itemstack[1]))
             return false;

         if (    inv[2].stackSize < getInventoryStackLimit() && inv[2].stackSize < inv[2].getMaxStackSize()
        	  || inv[3].stackSize < getInventoryStackLimit() && inv[3].stackSize < inv[3].getMaxStackSize() )
             return true;

         return inv[2].stackSize < itemstack[0].getMaxStackSize() && inv[3].stackSize < itemstack[1].getMaxStackSize();
	}

	public void smeltItem() {
         if (this.canSmelt()) {
             ItemStack[] var1 = RecipesMillstone.smelting().getSmeltingResult(this.inv[0].getItem().itemID);
             
        	 boolean inv2 = false, inv3 = false;
        	 
             if (this.inv[2] == null) {
            	 inv2 = true;
            	 this.inv[2] = var1[0].copy();
             }
             if (this.inv[3] == null) {
            	 inv3 = true;
            	 this.inv[3] = var1[1].copy();
             }
             
             if ( this.inv[2].itemID == var1[0].itemID && this.inv[3].itemID == var1[1].itemID )
                 if( !inv2 ) ++this.inv[2].stackSize;
             	 if( !inv3 ) ++this.inv[3].stackSize;
	             --this.inv[0].stackSize;
	             if (this.inv[0].stackSize == 0) {
                 Item var2 = this.inv[0].getItem().getContainerItem();
                 this.inv[0] = var2 == null ? null : new ItemStack(var2);
             }
         }
	}

	public static boolean isItemFuel(ItemStack par0ItemStack) {
         return getItemBurnTime(par0ItemStack) > 0;
	}

	public static int getItemBurnTime(ItemStack itemStack) {
         if (itemStack == null) return 0;
         int i = itemStack.getItem().itemID;
         if (i == Block.gravel.blockID) return 800; 
         if (i == Block.cobblestone.blockID) return 1600; 
         else return 0; 
	}
	
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
         if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
             return false;
         return entityPlayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}
	
	public void openChest() {}
	public void closeChest() {}
	public boolean isActive() { return this.isActive; }
}
