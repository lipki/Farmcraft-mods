package fr.farmcraft.loafofbread.client.breadoven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelBreadOven extends ModelBase {
	
    public ModelRenderer pascuit = (new ModelRenderer(this, 0, 0)).addBox(-6, -8, -6, 12, 4, 12);
    public ModelRenderer cuit    = (new ModelRenderer(this, 0,16)).addBox(-6, -8, -6, 12, 4, 12);

    public void render( int meta ) {
    	
        this.pascuit.rotateAngleX = (float) Math.PI;
        this.cuit.rotateAngleX    = (float) Math.PI;

        float h = (float)(meta)/2-12.5F;
    	GL11.glTranslatef(0, h/16, 0);
        
    	if( meta > 0 && meta < 15 ) this.pascuit.render(.0625F);
    	if( meta == 15 ) this.cuit.render(.0625F);
    }
}