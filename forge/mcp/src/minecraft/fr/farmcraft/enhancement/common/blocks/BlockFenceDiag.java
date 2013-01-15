package fr.farmcraft.enhancement.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import fr.farmcraft.enhancement.common.CommonProxy;
import fr.farmcraft.enhancement.common.TileEntityFenceDiag;

public class BlockFenceDiag extends BlockContainer {

    public String png;
    
    public BlockFenceDiag(int id, int tex, Material material) {
        super(id, tex, material);
        setCreativeTab(CreativeTabs.tabDecorations);
        setBlockBounds(.375F, 0, .375F, .625F, 1, .625F);
        
        png = CommonProxy.IRONFENCE_PNG;
    }

    @Override
    public void addCollidingBlockToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity) {
    	
    	boolean flag  = canConnectFenceTo(world, x, y, z - 1);
        boolean flag1 = canConnectFenceTo(world, x, y, z + 1);
        boolean flag2 = canConnectFenceTo(world, x - 1, y, z);
        boolean flag3 = canConnectFenceTo(world, x + 1, y, z);

        boolean flag4 = canConnectFenceTo(world, x - 1, y, z - 1);
        boolean flag5 = canConnectFenceTo(world, x + 1, y, z - 1);
        boolean flag6 = canConnectFenceTo(world, x - 1, y, z + 1);
        boolean flag7 = canConnectFenceTo(world, x + 1, y, z + 1);
        
        AxisAlignedBB axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.625, y, z+.625, x+.375, y+1.5F, z+.375);
        if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        
        if( flag ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.4375F, y, z, x+.5625F, y+1.26F, z+.375F );
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag1 ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.4375F, y, z+.625F, x+.5625F, y+1.26F, z+1 );
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag2 ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x, y, z+.4375F, x+.375F, y+1.26F, z+.5625F );
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag3 ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.625F, y, z+.4375F, x+1, y+1.26F, z+.5625F );
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        
        if( flag4  && !flag2  && !flag  ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x, y, z, x+.125F, y+1.26F, z+.125F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.125F, y, z+.125F, x+.250F, y+1.26F, z+.250F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.250F, y, z+.250F, x+.375F, y+1.26F, z+.375F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag7  && !flag3  && !flag1 ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.625F, y, z+.625F, x+.750F, y+1.26F, z+.750F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.750F, y, z+.750F, x+.875F, y+1.26F, z+.875F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }

        if( flag6  && !flag2  && !flag1 ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x, y, z+.875F, x+.125F, y+1.26F, z+1);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.125F, y, z+.750F, x+.250F, y+1.26F, z+.875F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.250F, y, z+.625F, x+.375F, y+1.26F, z+.750F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag5  && !flag3  && !flag  ) {
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.625F, y, z+.250F, x+.750F, y+1.26F, z+.375F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.750F, y, z+.125F, x+.875F, y+1.26F, z+.250F);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
       
    }
    
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
        
    	int i = world.getBlockId(x, y, z);
        
        if ( isFenceAt(world, x, y, z) ) return true;

        Block block = Block.blocksList[i];

        if (block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock())
             return block.blockMaterial != Material.pumpkin;
        else return false;
    }
    
    public boolean isFenceAt(IBlockAccess world, int x, int y, int z) {
    	int i = world.getBlockId(x, y, z);
        if (i == blockID) return true;
        if (i == Block.fenceGate.blockID) return true;
        else return false;
    }

    public static boolean isIdAFence(int par0) {
        return par0 == Block.fence.blockID || par0 == Block.netherFence.blockID;
    }
    
    public boolean renderAsNormalBlock() { return false; }
    public boolean isOpaqueCube() { return false; }
    public int getRenderType() { return 0; }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityFenceDiag();
    }
    
}
