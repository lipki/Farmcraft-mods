package fr.farmcraft.loafofbread.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;

public class BlockHayCarpet extends Block {
	
	@SideOnly(Side.CLIENT)
	private Icon icon;
	
    public BlockHayCarpet (int id) {
        super(id, Material.leaves);
        setHardness(1);
        setResistance(1);
		setStepSound(soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		setBlockBounds(0, 0, 0, 1, .01F, 1);

		setUnlocalizedName("hay");
		LanguageRegistry.addName(this, "Hay carpet");
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("loafofbread:hay");
	}

    public boolean isOpaqueCube() { return false; }
    public boolean renderAsNormalBlock() { return false; }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        int blockID = world.getBlockId(x, y - 1, z);
        Block block = Block.blocksList[blockID];
        if( block == null ) return false;
        if( block.blockID == this.blockID ) return false;
        if( !Block.blocksList[blockID].isOpaqueCube() && !block.isLeaves(world, x, y - 1, z) ) return false;
        return true;
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	canBlockStay(par1World, par2, par3, par4);
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        if (!canPlaceBlockAt(world, x, y, z)) {
        	dropBlockAsItem_do(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.blockHayCarpet, 1));
            world.setBlockToAir(x, y, z);
            return false;
        } else return true;
    }

}