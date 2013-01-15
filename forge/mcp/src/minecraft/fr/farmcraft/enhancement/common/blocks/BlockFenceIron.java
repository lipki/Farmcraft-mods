package fr.farmcraft.enhancement.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.enhancement.common.CommonProxy;

public class BlockFenceIron extends BlockFenceDiag {
	
    public BlockFenceIron(int id) {
        super(id, 22, Material.iron);
        setHardness(5);
        setResistance(10);
        setBlockName("fenceiron");
		setStepSound(soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(this, "Fence Iron");
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
    }

}
