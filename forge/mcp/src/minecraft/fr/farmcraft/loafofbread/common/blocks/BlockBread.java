package fr.farmcraft.loafofbread.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.bread.TileEntityBread;

public class BlockBread extends BlockContainer {
	
    public BlockBread(int id) {
        super(id, 32, Material.cake);
        setHardness(.5F);
        setBlockName("bread");
		setStepSound(this.soundClothFootstep);
		setCreativeTab(CreativeTabs.tabFood);
        setTickRandomly(true);
        disableStats();
        setRequiresSelfNotify();
		LanguageRegistry.addName(this, "Bread");
    }
	
	@Override
	public String getTextureFile () { return CommonProxy.BLOCK_PNG; }

    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
        if( meta == 0 ) meta = 8;
        
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
    public void addCollidingBlockToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity) {

		int meta = world.getBlockMetadata(x, y, z);
        if( meta == 0 ) meta = 8;
        
        float size = meta > 8 ? (float)(meta-8)*.125F+.0625F : (float)(meta-1)*.125F+.0625F;
        float top = meta > 8 ? 1 : .5F;
        float bottom = meta > 8 ? .5F : 0;
        AxisAlignedBB axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+1-size, y+bottom, z+.0625F, x+1-.0625F, y+top, z+1-.0625F);
        if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        
        if( meta > 8 ) {
	        axis = AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.0625F, y, z+.0625F, x+1-.0625F, y+.5F, z+1-.0625F);
	        if (axis != null && axisAlignedBB.intersectsWith(axis)) list.add(axis);
        }
       
    }
    
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
        float top = meta > 8 ? 1 : .5F;
        return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x+.0625F, y, z+.0625F, x+1-.0625F, y+top, z+1-.0625F);
    }

    public int getBlockTextureFromSideAndMetadata(int side, int meta) {
    	if ( side == 1 && meta < 15 && meta != 8 ) return 32;
    	if ( side == 0 && meta < 15 && meta != 8 ) return 35;
    	if ( meta != 8 && meta != 0 && side == 4 && meta < 15 ) return 34;
    	else if ( meta < 15 && meta != 8 ) return 33;
    	return 36;
    }

    public boolean renderAsNormalBlock() { return false; }
    public boolean isOpaqueCube() { return false; }

    public boolean onBlockActivated(World world, int x, int y, int z,
    									EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if( !world.isRemote ) {
    		
    		int meta = world.getBlockMetadata(x, y, z);
            if( meta == 0 ) meta = 8;
            
	        ItemStack currentItem = player.inventory.getCurrentItem();
	    	
	        if ( currentItem != null && currentItem.itemID == FarmcraftLoafOfBread.blockBread.blockID ) {
	            world.setBlockMetadataWithNotify(x, y, z, meta);
	        	if( newbread( world, x, y, z ) )
	        		--currentItem.stackSize;
	        	return true;
	        }
	        
	        dropSlice(world, x, y, z);
	        
    	}
        return true;
    }
    
    private boolean newbread(World world, int x, int y, int z ) {
    	
		int Y = y;
		while (   world.getBlockId(x, Y, z) == FarmcraftLoafOfBread.blockBread.blockID
				&& world.getBlockMetadata( x, Y, z ) > 14  ) Y += 1;

		if( world.getBlockId(x, Y, z) == FarmcraftLoafOfBread.blockBread.blockID ) {
			
			int meta = world.getBlockMetadata( x, Y, z );
            if( meta == 0 ) meta = 8;
			
			if( meta < 9 ) {
	    		meta += 7;
				world.setBlockMetadataWithNotify(x, Y, z, meta);
				return true;
	    	} else if( meta > 8 )  {
	    		world.setBlockMetadataWithNotify(x, Y, z, 15);
	    		int reste = 7-(15-meta);
	        	if( world.getBlockId(x, Y+1, z) == 0 ) {
		    		world.setBlockWithNotify(x, Y+1, z, FarmcraftLoafOfBread.blockBread.blockID);
		    		world.setBlockMetadataWithNotify(x, Y+1, z, reste+1);
					return true;
	        	} else return false;
	    	}
		
		} else {
        	if( world.getBlockId(x, Y, z) == 0 ) {
	    		world.setBlockWithNotify(x, Y, z, FarmcraftLoafOfBread.blockBread.blockID);
	    		world.setBlockMetadataWithNotify(x, Y, z, 8);
				return true;
        	} else return false;
		}
		return false;
	}

	private void dropSlice(World world, int x, int y, int z) {

		int Y = y;
		while ( world.getBlockId(x, Y+1, z) == FarmcraftLoafOfBread.blockBread.blockID ) Y += 1;

		int meta = world.getBlockMetadata( x, Y, z );
		meta--;
		
        if ( meta < 2 ) {
            world.setBlockWithNotify(x, Y, z, 0);
        }
        
        world.markBlockForRenderUpdate2(x, Y, z);
        
        Random rand = new Random();
        
        float rx = rand.nextFloat() * .8F + .1F;
        float ry = rand.nextFloat() * .8F + .1F;
        float rz = rand.nextFloat() * .8F + .1F;

        EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz,
                        new ItemStack(Item.bread, 1));

        float factor = .05F;
        entityItem.motionX = rand.nextGaussian() * factor;
        entityItem.motionY = rand.nextGaussian() * factor + .2F;
        entityItem.motionZ = rand.nextGaussian() * factor;
        world.spawnEntityInWorld(entityItem);
        
        world.setBlockMetadataWithNotify(x, Y, z, meta);
	        
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return !super.canPlaceBlockAt(world, x, y, z) ? false : this.canBlockStay(world, x, y, z);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockWithNotify(x, y, z, 0);
        }
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlockMaterial(x, y-1, z).isSolid();
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
}