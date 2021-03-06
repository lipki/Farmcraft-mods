package fr.farmcraft.loafofbread.client.millstone;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.farmcraft.loafofbread.client.ClientProxy;

public class RenderMillstone implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-.5F, -.5F, -.5F);

        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace    (block, 0, 0-.0625*4, 0, block.getBlockTextureFromSide(4));
        tessellator.draw();
        
        

        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace  (block, 0, 0, 0, block.getBlockTextureFromSide(3));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace  (block, 0-1+.0625, 0, 0, block.getBlockTextureFromSide(3));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace  (block, 0, 0, 0, block.getBlockTextureFromSide(3));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace  (block, 0+1-.0625, 0, 0, block.getBlockTextureFromSide(3));
        tessellator.draw();

        

        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace   (block, 0, 0, 0, block.getBlockTextureFromSide(3));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace   (block, 0, 0, 0-1+.0625, block.getBlockTextureFromSide(3));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace   (block, 0, 0, 0, block.getBlockTextureFromSide(3));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace   (block, 0, 0, 0+1-.0625, block.getBlockTextureFromSide(3));
        tessellator.draw();
        
        
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace(block, 0, 0, 0, block.getBlockTextureFromSide(5));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, +.0625F*4, .0625F*4, block.getBlockTextureFromSide(5));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, +.0625F*4, -.0625F*4, block.getBlockTextureFromSide(5));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, .0625F*4, +.0625F*4, 0, block.getBlockTextureFromSide(5));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, -.0625F*4, +.0625F*4, 0, block.getBlockTextureFromSide(5));
        tessellator.draw();
        
        
        GL11.glTranslatef(.5F, .5F, .5F);
		        
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
	
        Tessellator tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f = 0.9F;
        int l = block.colorMultiplier(world, x, y, z);
        float f1 = (float)(l >> 16 & 255) / 255.0F;
        float f2 = (float)(l >> 8 & 255) / 255.0F;
        float f3 = (float)(l & 255) / 255.0F;
        tess.setColorOpaque_F(f * f1, f * f2, f * f3);
        
        renderer.renderTopFace    (block, x, y-.0625*4, z, block.getBlockTextureFromSide(4));
        renderer.renderSouthFace  (block, x, y, z, block.getBlockTextureFromSide(3));
        renderer.renderNorthFace  (block, x, y, z, block.getBlockTextureFromSide(3));
        renderer.renderWestFace   (block, x, y, z, block.getBlockTextureFromSide(3));
        renderer.renderEastFace   (block, x, y, z, block.getBlockTextureFromSide(3));

        f = .6F;
        f1 = (float)(l >> 16 & 255) / 255.0F;
        f2 = (float)(l >> 8 & 255) / 255.0F;
        f3 = (float)(l & 255) / 255.0F;
        tess.setColorOpaque_F(f * f1, f * f2, f * f3);

        renderer.renderTopFace    (block, x, y-1+.0625*2, z, block.getBlockTextureFromSide(2));
        renderer.renderBottomFace (block, x, y, z, block.getBlockTextureFromSide(1));
        
        renderer.renderSouthFace  (block, x-1+.0625*2, y, z, block.getBlockTextureFromSide(3));
        renderer.renderNorthFace  (block, x+1-.0625*2, y, z, block.getBlockTextureFromSide(3));
        renderer.renderWestFace   (block, x, y, z-1+.0625*2, block.getBlockTextureFromSide(3));
        renderer.renderEastFace   (block, x, y, z+1-.0625*2, block.getBlockTextureFromSide(3));
        
	    return true;
	}

	@Override
	public boolean shouldRender3DInInventory() { return true; }

	@Override
	public int getRenderId() { return ClientProxy.RENDERMILLSTONE; }

}
