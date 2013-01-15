package fr.farmcraft.enhancement.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.enhancement.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class BlockRedstone extends Block {
	
    public BlockRedstone (int id) {
        super(id, 0, Material.rock);
        setHardness(3);
		setResistance(5);
        setBlockName("redstone");
		setStepSound(Block.soundSandFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(this, "Redstone");
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 0);
    }
	
	@Override
	public String getTextureFile () { return CommonProxy.BLOCK_PNG; }
    
}