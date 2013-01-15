package fr.farmcraft.enhancement.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.enhancement.common.CommonProxy;

public class BlockCharCoal extends Block {
	
    public BlockCharCoal (int id) {
        super(id, 2, Material.rock);
        setHardness(3);
		setResistance(5);
        setBlockName("blockcharcoal");
		setStepSound(Block.soundSandFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(this, "Block Charcoal");
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 0);
    }
	
	@Override
	public String getTextureFile () { return CommonProxy.BLOCK_PNG; }

}
