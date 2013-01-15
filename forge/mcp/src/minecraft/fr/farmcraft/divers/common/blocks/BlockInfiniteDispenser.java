package fr.farmcraft.divers.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
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
    
    public BlockInfiniteDispenser(int id) { super(id); }
    public int getRenderColor(int i) { return 0x665555; }
    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z) { return 0x665555; }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote && (world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z)))
            dispenseI(world, x, y, z);
    }
    
	public  void dispenseI(World world, int x, int y, int z) {
    	
        BlockSourceImpl var5 = new BlockSourceImpl(world, x, y, z);
        TileEntityDispenser var6 = (TileEntityDispenser)var5.func_82619_j();

        if (var6 != null) {
        	
            int var7 = var6.getRandomStackFromInventory();

            if (var7 < 0)
                world.playAuxSFX(1001, x, y, z, 0);
            else {
                ItemStack var8 = var6.getStackInSlot(var7);
                IBehaviorDispenseItem var9 = (IBehaviorDispenseItem)dispenseBehaviorRegistry.func_82594_a(var8.getItem());

                if (var9 != IBehaviorDispenseItem.itemDispenseBehaviorProvider) {
                    ItemStack var10 = var9.dispense(var5, var8);
                    var10.stackSize += 1;
                    var6.setInventorySlotContents(var7, var10.stackSize == 0 ? null : var10);
                }
            }
        }
    }
}
