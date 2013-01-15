package fr.farmcraft.enhancement.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.enhancement.common.CommonProxy;
import fr.farmcraft.enhancement.common.blocks.BlockFenceDiag;

public class TileEntityFenceDiagRenderer extends TileEntitySpecialRenderer {

    private ModelFence model = new ModelFence();

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {
        
    	BlockFenceDiag fence = (BlockFenceDiag) tileEntity.getBlockType();
    	
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);
        this.bindTextureByName(fence.png);
        GL11.glPushMatrix();
        this.model.render((BlockFenceDiag) tileEntity.getBlockType(), tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
