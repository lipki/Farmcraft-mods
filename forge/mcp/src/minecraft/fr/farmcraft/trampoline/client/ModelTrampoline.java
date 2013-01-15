package fr.farmcraft.trampoline.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelTrampoline extends ModelBase {
	
    public ModelRenderer cube = (new ModelRenderer(this, 0, 0)).addBox(-4, -4, -4, 8, 8, 8);

    public void render( boolean rotation ) {
        this.cube.render(.1F);
    }
}