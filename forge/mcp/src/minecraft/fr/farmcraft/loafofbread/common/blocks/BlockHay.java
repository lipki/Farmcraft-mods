package fr.farmcraft.loafofbread.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class BlockHay extends Block {

	@SideOnly(Side.CLIENT)
	private Icon hay1;
	@SideOnly(Side.CLIENT)
	private Icon hay2;
	@SideOnly(Side.CLIENT)
	private Icon hay3;
	
	public BlockHay(int id) {
        super(id, Material.leaves);
        setHardness(1);
        setResistance(1);
		setStepSound(this.soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		setUnlocalizedName("hay");
		LanguageRegistry.addName(this, "Hay");
    }
    
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
    	switch ( meta ) {
			case 0 : case 1 : return side < 2 ? hay3 : hay2; // dessous - dessus
			case 2 : case 3 : return side < 4 ? ( side < 2 ? hay2 : hay3 ) : hay1; // nord - sud
			case 4 : case 5 : return side < 4 ? hay1 : hay3; // ouest - est
    	}
		// 0 : dessous , 1 : dessus , 2 : nord , 3 : sud , 4 : ouest , 5 : est
        return hay1;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
    	
        int l = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
        
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int i) {
        return null;
    }
    
    public boolean isOpaqueCube() { return false; }
    public boolean renderAsNormalBlock() { return false; }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    	entity.fallDistance = 0;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		hay1 = iconRegister.registerIcon("loafofbread:hay_1");
		hay2 = iconRegister.registerIcon("loafofbread:hay_2");
		hay3 = iconRegister.registerIcon("loafofbread:hay_3");
	}
}
