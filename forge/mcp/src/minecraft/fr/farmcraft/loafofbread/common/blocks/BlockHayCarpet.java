package fr.farmcraft.loafofbread.common.blocks;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.client.ClientProxy;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class BlockHayCarpet extends Block {

	public BlockHayCarpet(int id) {
        super(id, 18, Material.leaves);
        setHardness(1);
        setResistance(1);
        setBlockName("haycarpet");
		setStepSound(soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		setBlockBounds(0, 0, 0, 1, .01F, 1);
		LanguageRegistry.addName(this, "Hay carpet");
    }
	
	@Override
	public String getTextureFile () { return CommonProxy.BLOCK_PNG; }

    public boolean isOpaqueCube() { return false; }
    public boolean renderAsNormalBlock() { return false; }
    public int getRenderType() { return ClientProxy.RENDERHAYCARPET; }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        int blockID = world.getBlockId(x, y - 1, z);
        Block block = Block.blocksList[blockID];
        return block.blockID != this.blockID && block != null && (block.blockID == blockID || block.isLeaves(world, x, y - 1, z) || Block.blocksList[blockID].isOpaqueCube()) ? world.getBlockMaterial(x, y - 1, z).blocksMovement() : false;
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        this.canSnowStay(par1World, par2, par3, par4);
    }

    private boolean canSnowStay(World par1World, int par2, int par3, int par4) {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4)) {
            par1World.setBlockWithNotify(par2, par3, par4, 0);
            return false;
        } else return true;
    }

}