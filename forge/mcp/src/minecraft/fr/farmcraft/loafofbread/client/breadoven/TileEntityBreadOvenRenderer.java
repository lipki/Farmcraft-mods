package fr.farmcraft.loafofbread.client.breadoven;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.loafofbread.client.ClientProxy;
import fr.farmcraft.loafofbread.common.TileEntityBreadOven;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityBreadOvenRenderer extends TileEntitySpecialRenderer {

    private ModelBreadOven pascuit = new ModelBreadOven();
    private ModelBreadOvenCuit cuit = new ModelBreadOvenCuit();

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {

    	int meta = tileEntity.getBlockMetadata();

    	if( meta > 0 ) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x+.5F, (float)y+.0625F, (float)z+.5F);
	        
	    	GL11.glTranslatef(0, (float)(meta)/30, 0);
	    	GL11.glScaled((float)(meta)/15, (float)(meta)/15, (float)(meta)/15);
	    	
	    	if( meta > 0 && meta < 15 ) {
		        bindTextureByName(ClientProxy.BREADOVEN_PNG);
	    		pascuit.render();
	    	}
	    	if( meta == 15 ) {
		        bindTextureByName(ClientProxy.BREAD_PNG);
	    		cuit.render();
	    	}
	    	
	        GL11.glPopMatrix();
    	}
    }
}
