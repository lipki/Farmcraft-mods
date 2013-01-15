package fr.farmcraft.enhancement.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class RenderCauldron implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
        Tessellator tessellator = Tessellator.instance;
        
        GL11.glTranslatef(-.5F, -.5F, -.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, -1, 0);
        renderer.renderBottomFace(block, 0, 0, 0, 154+1);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, -1, 0);
        renderer.renderBottomFace(block, 0, .0625F*3, 0, 154-15);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace(block, 0, 0, 0, 154-16);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace(block, 0, .0625F*4-1, 0, 154-15);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, 0, 0, 154);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, 0, 0, 154);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, 0, 0, 0, 154);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, 0, 0, 0, 154);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, 0, 1-.0625F*2, 154);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, 0, -1+.0625F*2, 154);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, 1-.0625F*2, 0, 0, 154);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, -1+.0625F*2, 0, 0, 154);
        tessellator.draw();
        
        GL11.glTranslatef(.5F, .5F, .5F);
        
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return renderer.renderBlockCauldron((BlockCauldron) block, x, y, z);
	}

	@Override
	public boolean shouldRender3DInInventory() { return true; }

	@Override
	public int getRenderId() { return ClientProxy.RENDERCAULDRON; }
	
}
