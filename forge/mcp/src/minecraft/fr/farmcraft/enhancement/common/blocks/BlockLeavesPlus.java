package fr.farmcraft.enhancement.common.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.enhancement.common.TileEntityApple;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLeavesPlus extends BlockLeaves implements ITileEntityProvider {

	private boolean apple;
    private int[] adjacentTreeBlocks;

	public BlockLeavesPlus(int par1) {
		super(par1);
		setHardness(0.2F);
		setLightOpacity(1);
		setStepSound(soundGrassFootstep);
		setUnlocalizedName("leaves");
	}
	
    public boolean isOpaqueCube() { return false; }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int meta) {
        return true;
    }
	
    public void onBlockAdded(World world, int x, int y, int z) {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if( meta == 0 && world.rand.nextInt(200) == 0 )
        	world.setBlockMetadataWithNotify(x, y, z, 12, 4);
    	
    }
	
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7) {
    	
        if (!world.isRemote) {
        	
            int j1 = 20;

            if ((par5 & 3) == 3) j1 = 40;

            if (par7 > 0) {
                j1 -= 2 << par7;
                if (j1 < 10) j1 = 10;
            }

            if (world.rand.nextInt(j1) == 0) {
                int k1 = this.idDropped(par5, world.rand, par7);
                this.dropBlockAsItem_do(world, x, y, z, new ItemStack(k1, 1, this.damageDropped(par5)));
            }

            j1 = 200;

            if (par7 > 0) {
                j1 -= 10 << par7;
                if (j1 < 40) j1 = 40;
            }
            
            if( par5 == 12 )
            //if( apple )
        	    this.dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.appleRed, 1, 0));

            //if ((par5 & 3) == 0 && world.rand.nextInt(j1) == 0)
            //    this.dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.appleRed, 1, 0));
        }
    }

	@Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		
        if (!par1World.isRemote) {
        	
            int l = par1World.getBlockMetadata(par2, par3, par4);

            if (( (l & 8) != 0 && (l & 4) == 0 ) || l == 12 ) {
            	
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.adjacentTreeBlocks == null)
                    this.adjacentTreeBlocks = new int[b1 * b1 * b1];

                int l1;

                if (par1World.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1)) {
                    int i2;
                    int j2;
                    int k2;

                    for (l1 = -b0; l1 <= b0; ++l1)
                        for (i2 = -b0; i2 <= b0; ++i2)
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                k2 = par1World.getBlockId(par2 + l1, par3 + i2, par4 + j2);

                                Block block = Block.blocksList[k2];

                                if (block != null && block.canSustainLeaves(par1World, par2 + l1, par3 + i2, par4 + j2))
                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                else if (block != null && block.isLeaves(par1World, par2 + l1, par3 + i2, par4 + j2))
                                	this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                else this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                            }

                    for (l1 = 1; l1 <= 4; ++l1)
                        for (i2 = -b0; i2 <= b0; ++i2)
                            for (j2 = -b0; j2 <= b0; ++j2)
                                for (k2 = -b0; k2 <= b0; ++k2)
                                    if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
                                        if (this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                            this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;

                                        if (this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                            this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                    }
                }

                l1 = this.adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

                if (l1 >= 0) par1World.setBlockMetadataWithNotify(par2, par3, par4, l & -9, 4);
                else this.removeLeaves(par1World, par2, par3, par4);
            }
        }
    }

    private void removeLeaves(World par1World, int par2, int par3, int par4) {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlockToAir(par2, par3, par4);
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
        return new TileEntityApple();
	}
	
}