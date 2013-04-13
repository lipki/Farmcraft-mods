package fr.farmcraft.divers.common.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IRegistry;
import net.minecraft.dispenser.RegistryDefaulted;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInfiniteDispenser extends BlockDispenser {

    public static final IRegistry dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
    
    public BlockInfiniteDispenser(int id) {
    	super(id);
    	
		setResistance(6000000);
		setBlockUnbreakable();
		setStepSound(soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabRedstone);
		
		LanguageRegistry.addName(this, "Infinite Dispenser");
    }
    public int getRenderColor(int i) { return 0x665555; }
    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z) { return 0x665555; }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if ( !world.isRemote )
            dispenseI(world, x, y, z);
    }
    
	public  void dispenseI(World world, int x, int y, int z) {
    	
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(world, x, y, z);
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)blocksourceimpl.getBlockTileEntity();

        if (tileentitydispenser != null) {
        	
            int l = tileentitydispenser.getRandomStackFromInventory();

            if (l < 0)
                world.playAuxSFX(1001, x, y, z, 0);
            else {
                ItemStack itemstack = tileentitydispenser.getStackInSlot(l);
                IBehaviorDispenseItem ibehaviordispenseitem = this.func_96472_a(itemstack);

                if (ibehaviordispenseitem != IBehaviorDispenseItem.itemDispenseBehaviorProvider) {
                    ItemStack itemstack1 = ibehaviordispenseitem.dispense(blocksourceimpl, itemstack);
                    itemstack1.stackSize += 1;
                    tileentitydispenser.setInventorySlotContents(l, itemstack1.stackSize == 0 ? null : itemstack1);
                }
            }
        }
    }
}
