package fr.farmcraft.loafofbread.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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
import cpw.mods.fml.common.Mod.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.client.ClientProxy;
import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.TileEntityBreadOven;
import fr.farmcraft.loafofbread.common.items.ItemBreadDough;

public class BlockBreadOven extends Block implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private Icon breadovenFace;
    @SideOnly(Side.CLIENT)
    private Icon breadovenFaceInner;
    @SideOnly(Side.CLIENT)
    private Icon breadovenInner;
    @SideOnly(Side.CLIENT)
    private Icon breadovenSide;
    @SideOnly(Side.CLIENT)
    private Icon breadovenSide2;
    @SideOnly(Side.CLIENT)
    private Icon breadovenTop;
    @SideOnly(Side.CLIENT)
    private Icon breadSide;

	public BlockBreadOven(int id) {
        super(id, Material.rock);
        setHardness(3.5F);
        setStepSound(soundStoneFootstep);
        setUnlocalizedName("breadOven");
        setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(this, "Bread Oven");
    }
	
	@SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
    	switch(side) {
    		case 1 : return breadovenFace;
    		case 2 : return breadovenFaceInner;
    		case 3 : return breadovenInner;
    		case 4 : return breadovenSide;
    		case 5 : return breadovenSide2;
    		case 6 : return breadovenTop;
    	}
        return breadSide;
    }
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    	breadovenFace = par1IconRegister.registerIcon("loafofbread:breadoven_face");
    	breadovenFaceInner = par1IconRegister.registerIcon("loafofbread:breadoven_face_inner");
    	breadovenInner = par1IconRegister.registerIcon("loafofbread:breadoven_inner");
    	breadovenSide = par1IconRegister.registerIcon("loafofbread:breadoven_side");
    	breadovenSide2 = par1IconRegister.registerIcon("loafofbread:breadoven_side2");
    	breadovenTop = par1IconRegister.registerIcon("loafofbread:breadoven_top");
    	breadSide = par1IconRegister.registerIcon("loafofbread:bread_side");
    }
    
    public int getRenderType() { return ClientProxy.RENDERBREADOVEN; }

	@Override
	public TileEntity createNewTileEntity(World world) { return new TileEntityBreadOven(); }
    public boolean isOpaqueCube() { return false; }
    public int idDropped(int par1, Random random, int y) { return blockID; }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
    	
		int meta = world.getBlockMetadata(x, y, z);
		
    	if ( meta > 0 && meta < 15 ) {
			dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.itemBreadDough, 1));
		} else if ( meta == 15 ) {
			dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.blockBread, 1));
		}
        super.breakBlock(world, x, y, z, par5, par6);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {

        if( !world.isRemote ) {
			int meta = world.getBlockMetadata(x, y, z);
	        ItemStack currentItem = player.inventory.getCurrentItem();
			
			if ( currentItem != null && currentItem.itemID == FarmcraftLoafOfBread.itemBreadDough.itemID && meta == 0 ) {
		        world.setBlockMetadataWithNotify(x, y, z, 1, 4);
		    	world.scheduleBlockUpdate(x, y, z, blockID, 1);
	        	--currentItem.stackSize;
			} else if ( meta > 0 && meta < 15 ) {
				dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.itemBreadDough, 1));
		        world.setBlockMetadataWithNotify(x, y, z, 0, 4);
		    	world.scheduleBlockUpdate(x, y, z, blockID, 1);
			} else if ( meta == 15 ) {
				dropItem(world, x, y, z, new ItemStack(FarmcraftLoafOfBread.blockBread, 1));
		        world.setBlockMetadataWithNotify(x, y, z, 0, 4);
		    	world.scheduleBlockUpdate(x, y, z, blockID, 1);
			}
        }
        return true;
    	
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
        int meta = world.getBlockMetadata(x, y, z);
        int furnaceid = world.getBlockId(x, y-1, z);
        TileEntityFurnace furnace = (TileEntityFurnace) world.getBlockTileEntity(x, y-1, z);
        if( furnaceid == 62 && meta > 0 && meta < 15 && furnace.isBurning() ) {
            meta++;
	        world.setBlockMetadataWithNotify(x, y, z, meta, 4);
	    	world.markBlockForUpdate(x, y, z);
        }
    	world.scheduleBlockUpdate(x, y, z, this.blockID, 200);
        //System.err.println( "four à pain : "+meta );
	}
    
    private void dropItem(World world, int x, int y, int z, ItemStack itemStack ) {
        Random rand = new Random();
        float rz = rand.nextFloat() * 0.8F + 0.1F;

        EntityItem entityItem = new EntityItem(world, x, y, z+rz, itemStack);

        float factor = 0.05F;
        entityItem.motionX = rand.nextGaussian() * factor;
        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
        entityItem.motionZ = rand.nextGaussian() * factor;
        world.spawnEntityInWorld(entityItem);
    }
}
