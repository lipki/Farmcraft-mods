package fr.farmcraft.loafofbread.client.millstone;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelWheat extends ModelBase {
	
    public ModelRenderer wheat  = (new ModelRenderer(this,0,16)).addBox(-7, 2, -7, 14, 2, 14);
    
    public void render( int meta ) {
        wheat.rotateAngleX = 3.15F+.05F;
        wheat.rotateAngleZ = .05F;
        
    	wheat.render(.0625F);
    }
}