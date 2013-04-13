package fr.farmcraft.loafofbread.client.breadoven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelBreadOvenCuit extends ModelBase {
	
    public ModelRenderer cuit    = (new ModelRenderer(this, 0, 0)).addBox(-7, 0, -7, 14, 8, 14);

    public void render() {
        cuit.rotateAngleX    = (float) Math.PI;
    	cuit.render(.0625F);
    }
}