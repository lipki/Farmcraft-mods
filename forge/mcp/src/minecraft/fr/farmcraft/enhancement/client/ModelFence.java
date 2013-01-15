package fr.farmcraft.enhancement.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.world.World;
import fr.farmcraft.enhancement.common.blocks.BlockFenceDiag;

public class ModelFence extends ModelBase {
	
    public ModelRenderer  nPlanck = (new ModelRenderer(this,0, 0)).addBox(2,    -3, -1,  6, 3, 2);
    public ModelRenderer nePlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,-3, -1, 10, 3, 2);
    public ModelRenderer  ePlanck = (new ModelRenderer(this,0, 0)).addBox(2,    -3, -1,  6, 3, 2);
    public ModelRenderer sePlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,-3, -1, 10, 3, 2);
    public ModelRenderer  sPlanck = (new ModelRenderer(this,0, 0)).addBox(2,    -3, -1,  6, 3, 2);
    public ModelRenderer soPlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,-3, -1, 10, 3, 2);
    public ModelRenderer  oPlanck = (new ModelRenderer(this,0, 0)).addBox(2,    -3, -1,  6, 3, 2);
    public ModelRenderer noPlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,-3, -1, 10, 3, 2);
    
    public ModelRenderer  nbPlanck = (new ModelRenderer(this,0, 0)).addBox(2,    5, -1,  6, 3, 2);
    public ModelRenderer nebPlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,5, -1, 10, 3, 2);
    public ModelRenderer  ebPlanck = (new ModelRenderer(this,0, 0)).addBox(2,    5, -1,  6, 3, 2);
    public ModelRenderer sebPlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,5, -1, 10, 3, 2);
    public ModelRenderer  sbPlanck = (new ModelRenderer(this,0, 0)).addBox(2,    5, -1,  6, 3, 2);
    public ModelRenderer sobPlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,5, -1, 10, 3, 2);
    public ModelRenderer  obPlanck = (new ModelRenderer(this,0, 0)).addBox(2,    5, -1,  6, 3, 2);
    public ModelRenderer nobPlanck = (new ModelRenderer(this,0,10)).addBox(1.32F,5, -1, 10, 3, 2);

    public ModelFence() {
         this.nPlanck.rotateAngleX = (float) Math.PI;
        this.nePlanck.rotateAngleX = (float) Math.PI;
         this.ePlanck.rotateAngleX = (float) Math.PI;
        this.sePlanck.rotateAngleX = (float) Math.PI;
         this.sPlanck.rotateAngleX = (float) Math.PI;
        this.soPlanck.rotateAngleX = (float) Math.PI;
         this.oPlanck.rotateAngleX = (float) Math.PI;
        this.noPlanck.rotateAngleX = (float) Math.PI;
        
        this.nbPlanck.rotateAngleX = (float) Math.PI;
       this.nebPlanck.rotateAngleX = (float) Math.PI;
        this.ebPlanck.rotateAngleX = (float) Math.PI;
       this.sebPlanck.rotateAngleX = (float) Math.PI;
        this.sbPlanck.rotateAngleX = (float) Math.PI;
       this.sobPlanck.rotateAngleX = (float) Math.PI;
        this.obPlanck.rotateAngleX = (float) Math.PI;
       this.nobPlanck.rotateAngleX = (float) Math.PI;
        
        
         this.nPlanck.rotateAngleY = 0;
        this.nePlanck.rotateAngleY = .79F;
         this.ePlanck.rotateAngleY = 1.57F;
        this.sePlanck.rotateAngleY = 2.36F;
         this.sPlanck.rotateAngleY = 3.14F;
        this.soPlanck.rotateAngleY = -2.36F;
         this.oPlanck.rotateAngleY = -1.57F;
        this.noPlanck.rotateAngleY = -.79F;
        
        this.nbPlanck.rotateAngleY = 0;
       this.nebPlanck.rotateAngleY = .79F;
        this.ebPlanck.rotateAngleY = 1.57F;
       this.sebPlanck.rotateAngleY = 2.36F;
        this.sbPlanck.rotateAngleY = 3.14F;
       this.sobPlanck.rotateAngleY = -2.36F;
        this.obPlanck.rotateAngleY = -1.57F;
       this.nobPlanck.rotateAngleY = -.79F;
        
    }

    public void render(BlockFenceDiag fence, World world, int x, int y, int z) {
        
    	boolean flag  = fence.canConnectFenceTo(world, x, y, z - 1);
        boolean flag1 = fence.canConnectFenceTo(world, x, y, z + 1);
        boolean flag2 = fence.canConnectFenceTo(world, x - 1, y, z);
        boolean flag3 = fence.canConnectFenceTo(world, x + 1, y, z);

        boolean flag4 = fence.canConnectFenceTo(world, x - 1, y, z - 1);
        boolean flag5 = fence.canConnectFenceTo(world, x + 1, y, z - 1);
        boolean flag6 = fence.canConnectFenceTo(world, x - 1, y, z + 1);
        boolean flag7 = fence.canConnectFenceTo(world, x + 1, y, z + 1);

        if( flag                        )  this.ePlanck.render(0.0625F);
        if( flag4  && !flag2  && !flag  ) this.sePlanck.render(0.0625F);
        if( flag1                       )  this.oPlanck.render(0.0625F);
        if( flag5  && !flag3  && !flag  ) this.nePlanck.render(0.0625F);
        if( flag2                       )  this.sPlanck.render(0.0625F);
        if( flag6  && !flag2  && !flag1 ) this.soPlanck.render(0.0625F);
        if( flag3                       )  this.nPlanck.render(0.0625F);
        if( flag7  && !flag3  && !flag1 ) this.noPlanck.render(0.0625F);

        if( flag                        )  this.ebPlanck.render(0.0625F);
        if( flag4  && !flag2  && !flag  ) this.sebPlanck.render(0.0625F);
        if( flag1                       )  this.obPlanck.render(0.0625F);
        if( flag5  && !flag3  && !flag  ) this.nebPlanck.render(0.0625F);
        if( flag2                       )  this.sbPlanck.render(0.0625F);
        if( flag6  && !flag2  && !flag1 ) this.sobPlanck.render(0.0625F);
        if( flag3                       )  this.nbPlanck.render(0.0625F);
        if( flag7  && !flag3  && !flag1 ) this.nobPlanck.render(0.0625F);

    }
}