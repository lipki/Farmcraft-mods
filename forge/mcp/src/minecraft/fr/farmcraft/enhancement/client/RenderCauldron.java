package fr.farmcraft.enhancement.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class RenderCauldron implements ISimpleBlockRenderingHandler {
	
    @SideOnly(Side.CLIENT)
    public Icon inner;
    @SideOnly(Side.CLIENT)
    public Icon top;
    @SideOnly(Side.CLIENT)
    public Icon bottom;
    @SideOnly(Side.CLIENT)
    public Icon side;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
        Tessellator tessellator = Tessellator.instance;
        
        GL11.glTranslatef(-.5F, -.5F, -.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, -1, 0);
        renderer.renderBottomFace(block, 0, .0625F*8, 0, BlockCauldron.func_94375_b("cauldron_bottom"));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, -1, 0);
        renderer.renderBottomFace(block, 0, .0625F*3, 0, BlockCauldron.func_94375_b("cauldron_inner"));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace(block, 0, 0, 0, block.getBlockTextureFromSide(1));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        renderer.renderTopFace(block, 0, .0625F*4-1, 0, BlockCauldron.func_94375_b("cauldron_inner"));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, 0, 0, block.getBlockTextureFromSide(2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, 0, 0, block.getBlockTextureFromSide(2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, 0, 0, 0, block.getBlockTextureFromSide(2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, 0, 0, 0, block.getBlockTextureFromSide(2));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, -1);
        renderer.renderEastFace(block, 0, 0, 1-.0625F*2, block.getBlockTextureFromSide(2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 0, 1);
        renderer.renderWestFace(block, 0, 0, -1+.0625F*2, block.getBlockTextureFromSide(2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1, 0, 0);
        renderer.renderNorthFace(block, 1-.0625F*2, 0, 0, block.getBlockTextureFromSide(2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1, 0, 0);
        renderer.renderSouthFace(block, -1+.0625F*2, 0, 0, block.getBlockTextureFromSide(2));
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
	
    /*public void registerIcons(IconRegister iconRegister) {
        this.inner = iconRegister.registerIcon("cauldron_inner");
        this.top = iconRegister.registerIcon("cauldron_top");
        this.bottom = iconRegister.registerIcon("cauldron_bottom");
        this.side = iconRegister.registerIcon("cauldron_side");
    }*/
	
}
