package fr.farmcraft.enhancement.common.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import fr.farmcraft.enhancement.FarmcraftEnhancement;
import fr.farmcraft.enhancement.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.world.World;

public class BlockCauldron extends net.minecraft.block.BlockCauldron {

	public BlockCauldron(int id) {
		super(id);
        setHardness(2);
		setCreativeTab(CreativeTabs.tabBrewing);
		
    	setUnlocalizedName("cauldron");
		LanguageRegistry.addName(this, "Cauldron");
	}
	
    public int getRenderType() {
		return ClientProxy.RENDERCAULDRON;
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return FarmcraftEnhancement.blockCauldron.blockID;
    }

    @SideOnly(Side.CLIENT)
    
    public int idPicked(World par1World, int par2, int par3, int par4) {
    	return FarmcraftEnhancement.blockCauldron.blockID;
    }

}
