package tuto.mesmods.trampoline;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelTrampoline extends ModelBase {
	
	public ModelRenderer cube = (new ModelRenderer(this, 0, 0)).addBox(-6, -6, -6, 12, 12, 12);
    
	public void render() {
    	this.cube.render(.0625F);
	}
}