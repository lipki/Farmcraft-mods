package fr.farmcraft.loafofbread.client.breadoven;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.farmcraft.loafofbread.client.ClientProxy;

public class RenderBreadOven implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

        Tessellator tessellator = Tessellator.instance;
        
        GL11.glTranslatef(-.5F, -.5F, -.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, -1, 0);
        renderer.renderBottomFace(block, 0, 0, 0, 2);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace(block, 0, 0, 0, 1);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, 0, 0, 0);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, 0, 0, 0);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, 0, 0, 0, 0);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, 0, 0, 0, 0);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, 0, 1-.0625F, 0);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, 0, -1+.0625F, 0);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, 1-.0625F, 0, 0, 0);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, -1+.0625F, 0, 0, 0);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace(block, 0, +.0625F*4, 0, 5);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, +.0625F*4, .0625F*4, 5);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, +.0625F*4, -.0625F*4, 5);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, .0625F*4, +.0625F*4, 0, 5);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, -.0625F*4, +.0625F*4, 0, 5);
        tessellator.draw();
        
        GL11.glTranslatef(.5F, .5F, .5F);
		        
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
	
        Tessellator tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
        renderer.renderSouthFace(block, x, y, z, 0);
        renderer.renderNorthFace(block, x, y, z, 0);
        renderer.renderWestFace (block, x, y, z, 0);
        renderer.renderEastFace (block, x, y, z, 0);
        
        renderer.renderSouthFace(block, x-1+.0625*2, y, z, 0);
        renderer.renderNorthFace(block, x+1-.0625*2, y, z, 0);
        renderer.renderWestFace (block, x, y, z-1+.0625*2, 0);
        renderer.renderEastFace (block, x, y, z+1-.0625*2, 0);
        
        renderer.renderTopFace   (block, x, y, z, 1);
        renderer.renderTopFace   (block, x, y-1+.0625*6.9, z, 3);
        renderer.renderBottomFace(block, x, y, z, 2);
        
	    return true;
	}

	@Override
	public boolean shouldRender3DInInventory() { return true; }

	@Override
	public int getRenderId() { return ClientProxy.RENDERMILLSTONE; }

}
