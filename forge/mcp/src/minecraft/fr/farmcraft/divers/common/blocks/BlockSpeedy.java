package fr.farmcraft.divers.common.blocks;

import fr.farmcraft.divers.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockSpeedy extends Block {
	
    public BlockSpeedy(int id) {
        super(id, 0, Material.sand);
	    slipperiness = 10F;
    }
	
	@Override
	public String getTextureFile () { return CommonProxy.BLOCK_PNG; }
    
    public int getBlockTextureFromSideAndMetadata(int side, int meta) {
    	switch ( side ) {
			case 0 : return meta == 0 ? 1 : 0;
			case 1 : return meta == 0 ? 1 : 0;
			case 2 : return meta == 0 ? 1 : 0;
			case 3 : return meta == 0 ? 1 : 0;
			case 4 : return meta == 1 ? 1 : 0;
			case 5 : return meta == 1 ? 1 : 0;
    	}
        return 2;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving) {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

        if (l == 0) world.setBlockMetadataWithNotify(x, y, z, 0);
        if (l == 1) world.setBlockMetadataWithNotify(x, y, z, 1);
        if (l == 2) world.setBlockMetadataWithNotify(x, y, z, 0);
        if (l == 3) world.setBlockMetadataWithNotify(x, y, z, 1);
    }
    
}