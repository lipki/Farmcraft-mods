package fr.farmcraft.loafofbread.client.breadoven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelBreadOven extends ModelBase {
	
    public ModelRenderer pascuit = (new ModelRenderer(this, 0, 0)).addBox(-7, 0, -7, 14, 8, 14);

    public void render() {
        pascuit.rotateAngleX = (float) Math.PI;
    	pascuit.render(.0625F);
    }
}