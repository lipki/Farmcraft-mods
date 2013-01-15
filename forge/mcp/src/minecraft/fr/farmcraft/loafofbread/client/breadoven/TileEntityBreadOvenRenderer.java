package fr.farmcraft.loafofbread.client.breadoven;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.breadoven.TileEntityBreadOven;

public class TileEntityBreadOvenRenderer extends TileEntitySpecialRenderer {

    private ModelMillstone model = new ModelMillstone();
    private ModelBreadOven modelO = new ModelBreadOven();

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {

    	int meta = tileEntity.getBlockMetadata();
    	int burnTime = ((TileEntityBreadOven) tileEntity).burnTime;
    	ItemStack truc = ((TileEntityBreadOven) tileEntity).inv[1];
    	int itemID = Block.cobblestone.blockID;
		if( truc != null )
    		itemID = ((TileEntityBreadOven) tileEntity).inv[1].getItem().itemID;
		
    	//System.err.println( "---"+meta );
    	
    	int stackSize;
    	if( ((TileEntityBreadOven) tileEntity).inv[2] != null )
    		 stackSize = ((TileEntityBreadOven) tileEntity).inv[2].stackSize;
    	else stackSize = 0;
    	
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);

    	if( burnTime > 0 || stackSize > 0 ) {
	        this.bindTextureByName(CommonProxy.MILLSTONE_PNG);
	        this.model.render(burnTime, stackSize, itemID);
    	}
    	if( meta > 0 ) {
	        this.bindTextureByName(CommonProxy.BREADOVEN_PNG);
	        this.modelO.render(meta);
    	}
    	
        GL11.glPopMatrix();
    }
}
