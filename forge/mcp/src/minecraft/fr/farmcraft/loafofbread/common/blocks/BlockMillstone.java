package fr.farmcraft.loafofbread.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.client.ClientProxy;
import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.TileEntityBreadOven;
import fr.farmcraft.loafofbread.common.TileEntityMillstone;
import fr.farmcraft.loafofbread.common.items.ItemBreadDough;

public class BlockMillstone extends Block implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private Icon millstoneBottom;
    @SideOnly(Side.CLIENT)
    private Icon millstoneIn;
    @SideOnly(Side.CLIENT)
    private Icon millstoneSide;
    @SideOnly(Side.CLIENT)
    private Icon millstoneTop;
    @SideOnly(Side.CLIENT)
    private Icon cobble;

	public BlockMillstone(int id) {
        super(id, Material.rock);
        setHardness(3.5F);
        setStepSound(soundStoneFootstep);
        setUnlocalizedName("millstone");
        setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(this, "Millstone");
    }
	
	@SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
    	switch(side) {
    		case 1 : return millstoneBottom;
    		case 2 : return millstoneIn;
    		case 3 : return millstoneSide;
    		case 4 : return millstoneTop;
    	}
        return cobble;
    }
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    	millstoneBottom = par1IconRegister.registerIcon("loafofbread:millstone_bottom");
    	millstoneIn = par1IconRegister.registerIcon("loafofbread:millstone_in");
    	millstoneSide = par1IconRegister.registerIcon("loafofbread:millstone_side");
    	millstoneTop = par1IconRegister.registerIcon("loafofbread:millstone_top");
    	cobble = par1IconRegister.registerIcon("loafofbread:cobble");
    }
    
    public int getRenderType() { return ClientProxy.RENDERMILLSTONE; }

	@Override
	public TileEntity createNewTileEntity(World world) { return new TileEntityMillstone(); }
    public boolean isOpaqueCube() { return false; }
    public int idDropped(int par1, Random random, int y) { return blockID; }

    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float par6) {

		int meta = world.getBlockMetadata(x, y, z);
    	
        if( meta > 2 && meta < 15 ) {
	    	entity.attackEntityFrom(DamageSource.cactus, 1);
			entity.motionZ += (Math.random()-.5)*2;
			entity.motionX += (Math.random()-.5)*2;
		}
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
    	
		int meta = world.getBlockMetadata(x, y, z);
		
    	if ( meta > 2 && meta < 15 ) {
			dropItem(world, x, y, z, new ItemStack(Item.wheat, 1));
		} else if ( meta == 15 ) {
			dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.itemFlavor, 1));
			dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.blockHayCarpet, 1));
		}
        super.breakBlock(world, x, y, z, par5, par6);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {

        if( !world.isRemote ) {
			int meta = world.getBlockMetadata(x, y, z);
	        ItemStack currentItem = player.inventory.getCurrentItem();
	        
			if ( currentItem != null && currentItem.itemID == Block.cobblestone.blockID && ( meta == 0 || meta == 2 ) ) {
				meta ++;
		        world.setBlockMetadataWithNotify(x, y, z, meta, 4);
		    	world.scheduleBlockUpdate(x, y, z, blockID, 1);
	        	--currentItem.stackSize;
			} else if ( currentItem != null && currentItem.itemID == Item.wheat.itemID && ( meta == 0 || meta == 1 ) ) {
				meta += 2;
		        world.setBlockMetadataWithNotify(x, y, z, meta, 4);
		    	world.scheduleBlockUpdate(x, y, z, blockID, 1);
	        	--currentItem.stackSize;
			} else if ( meta > 2 && meta < 15 ) {
		        world.setBlockMetadataWithNotify(x, y, z, 0, 4);
				dropItem(world, x, y, z, new ItemStack(Item.wheat, 1));
			} else if ( meta == 15 ) {
		        world.setBlockMetadataWithNotify(x, y, z, 0, 4);
				dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.itemFlavor, 1));
				dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.blockHayCarpet, 1));
			}
        }
        return true;
    	
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
        int meta = world.getBlockMetadata(x, y, z);
        if( meta > 2 && meta < 15 ) {
            meta++;
	        world.setBlockMetadataWithNotify(x, y, z, meta, 4);
	    	world.markBlockForUpdate(x, y, z);
        }
    	world.scheduleBlockUpdate(x, y, z, blockID, 10);
        //System.err.println( "Millstone : "+meta );
	}
    
    private void dropItem(World world, int x, int y, int z, ItemStack itemStack ) {
        Random rand = new Random();
        float r = rand.nextFloat() * 0.8F + 0.1F;

        EntityItem entityItem = new EntityItem(world, x, y+r, z, itemStack);

        float factor = 0.05F;
        entityItem.motionX = rand.nextGaussian() * factor;
        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
        entityItem.motionZ = rand.nextGaussian() * factor;
        world.spawnEntityInWorld(entityItem);
    }
}
