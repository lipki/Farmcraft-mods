package fr.farmcraft.loafofbread.client.bread;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.loafofbread.common.bread.TileEntityBread;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class TileEntityBreadRenderer extends TileEntitySpecialRenderer {

    private ModelBread model = new ModelBread();

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {

    	int meta = tileEntity.getBlockMetadata();
    	float random1 = ((TileEntityBread) tileEntity).random1;
    	float random2 = ((TileEntityBread) tileEntity).random2;
    	//System.err.println( "---"+meta );
    	
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);

	    this.bindTextureByName( CommonProxy.BREAD_PNG );
	    this.model.render( meta, random1, random2 );
    	
        GL11.glPopMatrix();
    }
}