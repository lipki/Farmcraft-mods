package fr.farmcraft.enhancement.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.enhancement.FarmcraftEnhancement;
import fr.farmcraft.enhancement.common.CommonProxy;

public class BlockCharCoal extends Block {
	
	@SideOnly(Side.CLIENT)
	private Icon icon;
	
    public BlockCharCoal (int id) {
        super(id, Material.rock);
        setHardness(3);
		setResistance(5);
		setStepSound(Block.soundSandFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 0);
		
    	setUnlocalizedName("charcoal");
		LanguageRegistry.addName(this, "Block Charcoal");
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("enhancement:charcoal");
	}

}
