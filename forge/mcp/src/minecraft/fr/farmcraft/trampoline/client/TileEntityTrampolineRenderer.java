package fr.farmcraft.trampoline.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.trampoline.common.CommonProxy;

public class TileEntityTrampolineRenderer extends TileEntitySpecialRenderer {

    private ModelTrampoline model = new ModelTrampoline();

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x+.5F, (float)y+.5F, (float)z+.5F);
        this.bindTextureByName(CommonProxy.NOYO_PNG);
        this.model.render(true);
        GL11.glPopMatrix();
    }
}