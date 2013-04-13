package fr.farmcraft.enhancement.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.enhancement.FarmcraftEnhancement;
import fr.farmcraft.enhancement.common.CommonProxy;
import fr.farmcraft.enhancement.common.blocks.BlockFenceDiag;

public class TileEntityAppleRenderer extends TileEntitySpecialRenderer {

    private ModelApple model;

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {
        
	    	int meta = tileEntity.getBlockMetadata();
	    	
	    	if( meta == 12 ) {
	    	    model = new ModelApple();
		        GL11.glPushMatrix();
		        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
		        bindTextureByName(ClientProxy.APPLE_PNG);
		        model.render();
		        GL11.glPopMatrix();
	    	}
	}

}
