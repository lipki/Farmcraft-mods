package fr.farmcraft.loafofbread.client.bread;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBread extends ModelBase {
	
    public ModelRenderer cuit  = (new ModelRenderer(this, 0, 0)).addBox(-7, -0, -7, 14, 8, 14);
    public ModelRenderer cuit2 = (new ModelRenderer(this, 0, 0)).addBox(-7, -8, -7, 14, 8, 14);

    public void render( int meta, float random1, float random2 ) {
        this.cuit.rotateAngleX = (float) Math.PI;
        this.cuit.rotateAngleY = random1;
        
        this.cuit2.rotateAngleX = (float) Math.PI;
        this.cuit2.rotateAngleY = random2;
        
    	if(meta > 7) this.cuit.render(.0625F);
    	if(meta == 15) this.cuit2.render(.0625F);
    }
}