package fr.farmcraft.divers.common.blocks;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.divers.FarmcraftDivers;
import fr.farmcraft.divers.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockSpeedy extends Block {
	
	@SideOnly(Side.CLIENT)
	private Icon autoway1;
	@SideOnly(Side.CLIENT)
	private Icon autoway2;
	@SideOnly(Side.CLIENT)
	private Icon autoway3;
	
    public BlockSpeedy(int id) {
        super(id, Material.sand);
	    slipperiness = 10F;
		
		setResistance(6000000);
		setLightValue(1);
		setBlockUnbreakable();
		setStepSound(soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	    
		setUnlocalizedName("auto_way");
		LanguageRegistry.addName(this, "Auto Way");
    }
    
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
    	switch ( meta ) {
			case 0 : case 1 : return side < 2 ? autoway3 : autoway2; // dessous - dessus
			case 2 : case 3 : return side < 4 ? ( side < 2 ? autoway2 : autoway3 ) : autoway1; // nord - sud
			case 4 : case 5 : return side < 4 ? autoway1 : autoway3; // ouest - est
    	}
		// 0 : dessous , 1 : dessus , 2 : nord , 3 : sud , 4 : ouest , 5 : est
        return autoway1;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
    	
        int l = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
        
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		autoway1 = iconRegister.registerIcon("divers:autoway_1");
		autoway2 = iconRegister.registerIcon("divers:autoway_2");
		autoway3 = iconRegister.registerIcon("divers:autoway_3");
	}
    
}