package fr.farmcraft.loafofbread.client.millstone;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.loafofbread.client.ClientProxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMillstoneRenderer extends TileEntitySpecialRenderer {
	
    private ModelCobble cobble = new ModelCobble();
    private ModelWheat wheat = new ModelWheat();
    private ModelFlavor flavor = new ModelFlavor();
	private float angle;

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {

    	int meta = tileEntity.getBlockMetadata();

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
        
        bindTextureByName(ClientProxy.MILLSTONE_PNG);

        GL11.glTranslatef(0, .0625F*14 - (float)meta/25, 0);
    	if( meta > 1 ) wheat.render(meta);
        GL11.glTranslatef(0, -.0625F*10 + (float)meta/13, 0);
    	if( meta > 1 ) flavor.render(meta);
        GL11.glTranslatef(0, -.0625F*11 - (float)meta/30, 0);

		angle += 100;
        GL11.glTranslatef(0, 1+.0625F*2, 0);
    	GL11.glScaled(1-(float)(meta)/15, 1-(float)(meta)/15, 1-(float)(meta)/15);
    	if( meta > 2 && meta < 15 ) GL11.glRotatef(angle, 1, 0, 1);
    	if( meta == 1 || meta > 2 ) cobble.render();
    	
        GL11.glPopMatrix();
	}

}
