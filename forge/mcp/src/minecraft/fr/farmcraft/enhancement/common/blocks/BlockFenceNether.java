package fr.farmcraft.enhancement.common.blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import fr.farmcraft.enhancement.common.CommonProxy;

public class BlockFenceNether extends BlockFenceDiag {

    public BlockFenceNether(int id) {
        super(id, 224, Material.rock);
        setHardness(2);
        setResistance(10);
        setBlockName("netherfence");
		setStepSound(soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(this, "Fence Nether");
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);

        png = CommonProxy.NETHERFENCE_PNG;
    }
	   
}
