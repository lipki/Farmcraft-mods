package fr.farmcraft.enhancement.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.enhancement.common.CommonProxy;
import fr.farmcraft.enhancement.common.blocks.BlockFenceDiag;

public class TileEntityAppleRenderer extends TileEntitySpecialRenderer {

    private ModelApple model = new ModelApple();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {
        
	    	int meta = tileEntity.getBlockMetadata();
	    	
	        GL11.glPushMatrix();
	        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
	        this.bindTextureByName(CommonProxy.APPLE_PNG);
	        GL11.glPushMatrix();
	        if( meta == 12 ) this.model.render();
	        GL11.glPopMatrix();
	        GL11.glPopMatrix();
	}

}
