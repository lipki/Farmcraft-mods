package fr.farmcraft.enhancement.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.enhancement.common.CommonProxy;

public class BlockFenceWood extends BlockFenceDiag {
	
    public BlockFenceWood(int id) {
        super(id, 4, Material.wood);
        setHardness(2);
        setResistance(5);
        setBlockName("fencewood");
		setStepSound(soundWoodFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(this, "Fence Wood");
		MinecraftForge.setBlockHarvestLevel(this, "haxe", 1);
		
        png = CommonProxy.WOODFENCE_PNG;
    }

}
