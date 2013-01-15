package fr.farmcraft.trampoline.common;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.trampoline.client.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockTrampoline extends BlockContainer {

	private Entity tentity;
	private float tblock;

	public BlockTrampoline(int id) {
        super(id, 0, Material.cake);
        setHardness(.5F);
		setStepSound(Block.soundSnowFootstep);
        setBlockName("trampoline");
		setCreativeTab(CreativeTabs.tabBlock);
        LanguageRegistry.addName(this, "Trampoline");
        
        slipperiness = 1.05F;
    }
	
	@Override
	public String getTextureFile () { return ClientProxy.BLOCK_PNG; }
    
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float par6) {
    	if( tentity == null ) {
    		tentity = entity;
    		tblock = entity.fallDistance;
    	} else world.scheduleBlockUpdate(x, y, z, blockID, 1);
    	entity.fallDistance = 0;
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
    	if( tentity != null ) {
    		float step = 0;
    		if( tentity.isSneaking() ) step = tblock/6;
    		if( step > 1 ) step = tblock/10;
    		if( step > 3 ) step = tblock/15;
    		if( step > 6 ) step = tblock/20;
        	tentity.motionY = 1 + step;
	    	tentity = null;
    	}
	}
    
    public boolean isOpaqueCube() { return false; }
    public int getRenderBlockPass() { return 1; }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityTrampoline();
	}

}