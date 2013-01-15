package fr.farmcraft.loafofbread.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class BlockHay extends Block {

	public BlockHay(int id) {
        super(id, 16, Material.leaves);
        setHardness(1);
        setResistance(1);
        setBlockName("Hay");
		setStepSound(this.soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(this, "Hay");
    }
	
	@Override
	public String getTextureFile () { return CommonProxy.BLOCK_PNG; }

    public int getBlockTextureFromSide(int side) {
    	switch (side) {
    		case 4 : return this.blockIndexInTexture+1;
    		case 5 : return this.blockIndexInTexture+1;
    	}
        return this.blockIndexInTexture;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int i) {
        return null;
    }
    
    public boolean isOpaqueCube() { return false; }
    public boolean renderAsNormalBlock() { return false; }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    	entity.fallDistance = 0;
    }
}
