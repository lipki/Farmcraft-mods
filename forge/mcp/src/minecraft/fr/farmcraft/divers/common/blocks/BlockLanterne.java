package fr.farmcraft.divers.common.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class BlockLanterne extends Block {
	
    public BlockLanterne (int id) {
    	super(id, Material.glass);
		
		setHardness(.3F);
		setResistance(56.34F);
		setLightValue(1);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundGlassFootstep);

		setUnlocalizedName("redstoneLight_lit");
		LanguageRegistry.addName(this, "Lanterne");
    }
    
    public int quantityDroppedWithBonus(int i, Random random) {
        return MathHelper.clamp_int(quantityDropped(random) + random.nextInt(i + 1), 1, 4);
    }
    
    public int quantityDropped(Random random) { return 2 + random.nextInt(3); }
    public int idDropped(int par1, Random random, int par3) { return Item.lightStoneDust.itemID; }

}

 
