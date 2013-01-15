package fr.farmcraft.enhancement.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import fr.farmcraft.enhancement.common.blocks.BlockFenceDiag;

public class ModelApple extends ModelBase {

    public ModelRenderer  apple = (new ModelRenderer(this,0, 0)).addBox(-8, -8, 0, 16, 16, 0);
    public ModelRenderer apple2 = (new ModelRenderer(this,0, 0)).addBox(-8, -8, 0, 16, 16, 0);

    public ModelApple() {
          this.apple.rotateAngleX = (float) Math.PI;
         this.apple2.rotateAngleX = (float) Math.PI;
          this.apple.rotateAngleY = (float) Math.PI/4;
         this.apple2.rotateAngleY = (float) Math.PI/4 + (float) Math.PI/2;
    }

    public void render() {
         this.apple.render(0.03125F);
        this.apple2.render(0.03125F);
    }
}
