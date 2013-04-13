package fr.farmcraft.enhancement.common.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import fr.farmcraft.enhancement.FarmcraftEnhancement;
import fr.farmcraft.enhancement.common.CommonProxy;
import fr.farmcraft.enhancement.common.TileEntityFenceDiag;

public class BlockFenceDiag extends Block implements ITileEntityProvider {
	
	@SideOnly(Side.CLIENT)
	private Icon icon;

    public BlockFenceDiag(int id, Material material, String unname, String name) {
        super(id, material);
        setCreativeTab(CreativeTabs.tabDecorations);
        setBlockBounds(.375F, 0, .375F, .625F, 1, .625F);
    	setUnlocalizedName(unname);
		LanguageRegistry.addName(this, unname);
        
               if ( material == Material.wood ) {
        	setHardness(2);
            setResistance(5);
    		setStepSound(soundWoodFootstep);
    		MinecraftForge.setBlockHarvestLevel(this, "haxe", 1);
        } else if ( material == Material.rock ) {
        	setHardness(2);
            setResistance(10);
    		setStepSound(soundStoneFootstep);
    		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
        } else if ( material == Material.iron ) {
        	setHardness(5);
            setResistance(10);
    		setStepSound(soundMetalFootstep);
    		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
        }
    }
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity) {
    	
    	boolean flag  = canConnectFenceTo(world, x, y, z - 1);
        boolean flag1 = canConnectFenceTo(world, x, y, z + 1);
        boolean flag2 = canConnectFenceTo(world, x - 1, y, z);
        boolean flag3 = canConnectFenceTo(world, x + 1, y, z);

        boolean flag4 = canConnectFenceTo(world, x - 1, y, z - 1);
        boolean flag5 = canConnectFenceTo(world, x + 1, y, z - 1);
        boolean flag6 = canConnectFenceTo(world, x - 1, y, z + 1);
        boolean flag7 = canConnectFenceTo(world, x + 1, y, z + 1);

        AxisAlignedBB axis = AxisAlignedBB.getAABBPool().getAABB(x+.625, y, z+.625, x+.375, y+1.5F, z+.375);
        if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        
        if( flag ) {
	        axis = AxisAlignedBB.getAABBPool().getAABB(x+.4375, y, z, x+.5625, y+1.26, z+.375);
	        if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag1 ) {
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.4375, y, z+.625, x+.5625, y+1.26, z+1 );
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag2 ) {
        	axis = AxisAlignedBB.getAABBPool().getAABB(x, y, z+.4375, x+.375, y+1.26, z+.5625 );
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag3 ) {
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.625, y, z+.4375, x+1, y+1.26, z+.5625 );
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        
        if( flag4  && !flag2  && !flag  ) {
        	axis = AxisAlignedBB.getAABBPool().getAABB(x, y, z, x+.125, y+1.26, z+.125);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.125, y, z+.125, x+.250, y+1.26, z+.250);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.250, y, z+.250, x+.375, y+1.26, z+.375);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag7  && !flag3  && !flag1 ) {
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.625, y, z+.625, x+.750, y+1.26, z+.750);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.750, y, z+.750, x+.875, y+1.26, z+.875);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }

        if( flag6  && !flag2  && !flag1 ) {
        	axis = AxisAlignedBB.getAABBPool().getAABB(x, y, z+.875, x+.125, y+1.26, z+1);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.125, y, z+.750, x+.250, y+1.26, z+.875);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.250, y, z+.625, x+.375, y+1.26, z+.750);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
        if( flag5  && !flag3  && !flag  ) {
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.625, y, z+.250, x+.750, y+1.26, z+.375);
        	if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        	axis = AxisAlignedBB.getAABBPool().getAABB(x+.750, y, z+.125, x+.875, y+1.26, z+.250);
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
