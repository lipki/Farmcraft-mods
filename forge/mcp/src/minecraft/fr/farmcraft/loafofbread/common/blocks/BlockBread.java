package fr.farmcraft.loafofbread.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.TileEntityBread;

public class BlockBread extends Block implements ITileEntityProvider {

	@SideOnly(Side.CLIENT)
	private Icon breadBottom;
	@SideOnly(Side.CLIENT)
	private Icon breadSide;
	@SideOnly(Side.CLIENT)
	private Icon breadSideCut;
	@SideOnly(Side.CLIENT)
	private Icon breadTop;
	@SideOnly(Side.CLIENT)
	private Icon vide;
	
    public BlockBread(int id) {
        super(id, Material.cake);
        setHardness(.5F);
		setStepSound(this.soundClothFootstep);
		setCreativeTab(CreativeTabs.tabFood);
        setTickRandomly(true);
        disableStats();
        setUnlocalizedName("bread");
		LanguageRegistry.addName(this, "Bread");
    }
	
    public void onBlockAdded(World world, int x, int y, int z) {
    	int meta = world.getBlockMetadata(x, y, z);
        if( meta == 0 ) meta = 8;
        world.setBlockMetadataWithNotify(x, y, z, meta, 4);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
        
        float size = meta > 8 ? (float)(meta-8)*.125F+.0625F : (float)(meta-1)*.125F+.0625F;
        float top = meta > 8 ? 1 : .5F;
        float bottom = meta > 8 ? .5F : 0;
        bottom = meta == 15 ? 0 : bottom;
        this.setBlockBounds(1-size, bottom, .0625F, 1-.0625F, top, 1-.0625F);
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(.0625F, 0, .0625F, 1-.0625F, .5F, 1-.0625F);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity) {

		int meta = world.getBlockMetadata(x, y, z);
        
        float size = meta > 8 ? (float)(meta-8)*.125F+.0625F : (float)(meta-1)*.125F+.0625F;
        float top = meta > 8 ? 1 : .5F;
        float bottom = meta > 8 ? .5F : 0;
        AxisAlignedBB axis = AxisAlignedBB.getAABBPool().getAABB(x+1-size, y+bottom, z+.0625F, x+1-.0625F, y+top, z+1-.0625F);
        if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        
        if( meta > 8 ) {
	        axis = AxisAlignedBB.getAABBPool().getAABB(x+.0625F, y, z+.0625F, x+1-.0625F, y+.5F, z+1-.0625F);
	        if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
       
    }
    
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
        float top = meta > 8 ? 1 : .5F;
        return AxisAlignedBB.getAABBPool().getAABB(x+.0625F, y, z+.0625F, x+1-.0625F, y+top, z+1-.0625F);
    }

    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
    	if ( side == 1 && meta < 15 && meta != 8 ) return breadTop;
    	if ( side == 0 && meta < 15 && meta != 8 ) return breadBottom;
    	if ( meta != 8 && meta != 0 && side == 4 && meta < 15 ) return breadSideCut;
    	else if ( meta < 15 && meta != 8 ) return breadSide;
    	return vide;
    }

    public boolean renderAsNormalBlock() { return false; }
    public boolean isOpaqueCube() { return false; }

    public boolean onBlockActivated(World world, int x, int y, int z,
    									EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if( !world.isRemote ) {

	        ItemStack currentItem = player.inventory.getCurrentItem();
    		int meta = world.getBlockMetadata(x, y, z);
            
	        if ( currentItem != null && currentItem.itemID == FarmcraftLoafOfBread.blockBread.blockID ) {
	            world.setBlockMetadataWithNotify(x, y, z, meta, 4);
	            if( newbread( world, x, y, z ) ) --currentItem.stackSize;
	            return true;
	        }
	        
	        dropSlice(world, x, y, z);
	        
    		meta = world.getBlockMetadata(x, y, z);
	        if ( meta == 1 ) world.setBlock(x, y, z, 0);
    		
    	}
        return true;
    }
    
    private boolean newbread(World world, int x, int y, int z ) {
    	
    	int Y = y;
		while ( world.getBlockId(x, Y+1, z) == FarmcraftLoafOfBread.blockBread.blockID ) Y += 1;
		
		int meta = world.getBlockMetadata( x, Y, z );
		
		if( meta < 9 ) {
			meta += 7;
			world.setBlockMetadataWithNotify(x, Y, z, meta, 4);
			world.markBlockForUpdate(x, Y, z);
			return true;
		} else if( meta > 8 )  {
			if( world.getBlockId(x, Y+1, z) == 0 ) {
				int reste = meta+7-14;
		        System.err.println( "plus "+reste );
	    		world.setBlockMetadataWithNotify(x, Y, z, 15, 4);
				world.markBlockForUpdate(x, Y, z);
	    		world.setBlock(x, Y+1, z, FarmcraftLoafOfBread.blockBread.blockID);
	    		world.setBlockMetadataWithNotify(x, Y+1, z, reste, 4);
	    		return true;
	    	} else return false;
    	}
		
		return false;
	}

	private void dropSlice(World world, int x, int y, int z) {

		int Y = y;
		while ( world.getBlockId(x, Y+1, z) == FarmcraftLoafOfBread.blockBread.blockID ) Y += 1;
		
		int meta = world.getBlockMetadata( x, Y, z );
        System.err.println( meta );
		meta--;
		
        if ( meta < 2 ) world.setBlock(x, Y, z, 0);

        dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.bread, 1));
        
        world.setBlockMetadataWithNotify(x, Y, z, meta, 4);
		world.markBlockForUpdate(x, Y, z);
	        
    }
    
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        int blockID = world.getBlockId(x, y - 1, z);
        Block block = Block.blocksList[blockID];
        if( block == null ) return false;
        return true;
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	canBlockStay(par1World, par2, par3, par4);
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        if (!canPlaceBlockAt(world, x, y, z)) {
    		int meta = world.getBlockMetadata( x, y, z );
        	if( meta > 7 )  dropBlockAsItem_do(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.blockBread, 1));
        	if( meta > 14 ) dropBlockAsItem_do(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.blockBread, 1));
            world.setBlockToAir(x, y, z);
            return false;
        } else return true;
    }

    public int quantityDropped(Random random) {
    	return 1;
    }
    public int idDropped(int par1, Random xRandom, int y) { return FarmcraftLoafOfBread.blockBread.blockID; }
    public int idPicked(World world, int x, int y, int z) { return FarmcraftLoafOfBread.blockBread.blockID; }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityBread();
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
    	breadBottom = iconRegister.registerIcon("loafofbread:bread_bottom");
    	breadSide = iconRegister.registerIcon("loafofbread:bread_side");
    	breadSideCut = iconRegister.registerIcon("loafofbread:bread_side_cut");
    	breadTop = iconRegister.registerIcon("loafofbread:bread_top");
    	vide = iconRegister.registerIcon("loafofbread:vide");
	}
}