package fr.farmcraft.loafofbread.client.millstone;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelCobble extends ModelBase {
	
    public ModelRenderer cobble = (new ModelRenderer(this,  0, 0)).addBox(-4, -4, -4, 8, 8, 8);
    
    public void render() {
    	cobble.render(.0625F);
    }
}