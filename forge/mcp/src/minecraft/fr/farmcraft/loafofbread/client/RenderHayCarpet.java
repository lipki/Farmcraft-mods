package fr.farmcraft.loafofbread.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderHayCarpet implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
	
        Tessellator tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        
        renderer.renderTopFace (block, x, y, z, 18);
        
	    return true;
	}

	@Override
	public boolean shouldRender3DInInventory() { return false; }

	@Override
	public int getRenderId() { return ClientProxy.RENDERHAYCARPET; }

}
