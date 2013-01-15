package fr.farmcraft.loafofbread.client.breadoven;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelMillstone extends ModelBase {
	
    public ModelRenderer cobble = (new ModelRenderer(this,  0, 0)).addBox(-4, -4, -4, 8, 8, 8);
    public ModelRenderer gravel = (new ModelRenderer(this, 32, 0)).addBox(-4, -4, -4, 8, 8, 8);
    public ModelRenderer wheat  = (new ModelRenderer(this,-14,16)).addBox(-7, 2, -7, 14, 2, 14);
    
    public void render( int burnTime, int stackSize, int itemID ) {
    	GL11.glTranslatef(0, -.2F, 0);
        this.wheat.rotateAngleX = 3.15F+.05F;
        this.wheat.rotateAngleZ = .05F;
        
    	if( burnTime > 0 ) {
    		if( itemID == Block.cobblestone.blockID ) {
		        this.cobble.rotateAngleY = this.cobble.rotateAngleY+.3F;
		        this.cobble.rotateAngleZ = this.cobble.rotateAngleZ+.3F;
		        this.cobble.render(.0625F);
            } else {
		        this.gravel.rotateAngleY = this.gravel.rotateAngleY+.3F;
		        this.gravel.rotateAngleZ = this.gravel.rotateAngleZ+.3F;
		        this.gravel.render(.0625F);
        	}
        }
    	if( stackSize > 0 ) this.wheat.render(.0625F);
    }
}