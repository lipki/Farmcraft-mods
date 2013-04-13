package fr.farmcraft.loafofbread.client.millstone;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelFlavor extends ModelBase {
	
    public ModelRenderer flavor  = (new ModelRenderer(this,-14,16)).addBox(-7, 2, -7, 14, 2, 14);
    
    public void render( int meta ) {
    	flavor.rotateAngleX = 3.15F-.05F;
    	flavor.rotateAngleZ = -.05F;
        
    	flavor.render(.0625F);
    }
}