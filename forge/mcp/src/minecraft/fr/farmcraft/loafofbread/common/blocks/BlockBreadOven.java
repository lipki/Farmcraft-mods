package fr.farmcraft.loafofbread.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.client.ClientProxy;
import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.breadoven.TileEntityBreadOven;

public class BlockBreadOven extends BlockContainer {

    private boolean isActive;

	public BlockBreadOven(int id) {
        super(id, 3, Material.iron);
        setHardness(2);
        setBlockName("millstone");
		setStepSound(soundGravelFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
        setBlockBounds(0, 0, 0, 1, .75F, 1);
        setRequiresSelfNotify();
        setTickRandomly(false);
        isActive = false;
		LanguageRegistry.addName(this, "Millstone");
    }
	
	@Override
	public String getTextureFile () { return CommonProxy.BLOCK_PNG; }
    
    public int tickRate() { return 1; }
    public boolean isOpaqueCube() { return false; }
    public boolean renderAsNormalBlock() { return false; }
    public int getRenderType() { return ClientProxy.RENDERMILLSTONE; }
    public int idDropped(int par1, Random random, int y) { return blockID; }
    
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float par6) {
    	
    	TileEntityBreadOven tileEntity = (TileEntityBreadOven) world.getBlockTileEntity(x, y, z);
    	
        if( tileEntity.isBurning() ) {
	    	entity.attackEntityFrom(DamageSource.cactus, 1);
			entity.motionZ += (Math.random()-.5)*2;
			entity.motionX += (Math.random()-.5)*2;
		}
    }
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, 
    									EntityPlayer player, int idk, float what, float these, float are) {
        
    	if( !world.isRemote ) {
	        int meta = world.getBlockMetadata(x, y, z);
	        ItemStack currentItem = player.inventory.getCurrentItem();
	        TileEntityBreadOven tileEntity = (TileEntityBreadOven) world.getBlockTileEntity(x, y, z);
	    	
	        if (    currentItem != null
	        	 && currentItem.itemID == FarmcraftLoafOfBread.itemBreadDough.itemID
	        	 && meta == 0
	        	 && !tileEntity.isBurning() ) {
	        	world.scheduleBlockUpdate(x, y, z, this.blockID, 1);
	        	--currentItem.stackSize;
	        	dropItems(world, x, y, z);
	        } else if ( meta == 15 ) {
		    	dropBread(world, x, y, z);
	        } else if ( meta == 0 ) {
		        if (tileEntity == null || player.isSneaking()) return false;
		        player.openGui(FarmcraftLoafOfBread.instance, 0, world, x, y, z);
	        }
    	}
        return true;
    	
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
    	System.err.println("update");
        int meta = world.getBlockMetadata(x, y, z);
        meta++;
        if( meta < 16 ) {
	        world.setBlockAndMetadataWithNotify(x, y, z, FarmcraftLoafOfBread.blockBreadOven.blockID, meta);
	    	world.scheduleBlockUpdate(x, y, z, this.blockID, 50);
        }
	}
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
   
    private void dropItems(World world, int x, int y, int z) {
	
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) return;
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz,
                                new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound())
                    entityItem.func_92014_d().setTagCompound((NBTTagCompound) item.getTagCompound().copy());

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }
   
    private void dropBread(World world, int x, int y, int z){
        Random rand = new Random();
        int meta = world.getBlockMetadata(x, y, z);

        if( meta == 15) {
	        float rx = rand.nextFloat() * 0.8F + 0.1F;
	        float ry = rand.nextFloat() * 0.8F + 0.1F;
	        float rz = rand.nextFloat() * 0.8F + 0.1F;
	
	        EntityItem entityItem = new EntityItem(world,
	                        x + rx, y + ry, z + rz,
	                        new ItemStack(FarmcraftLoafOfBread.blockBread, 1));
	
	        float factor = 0.05F;
	        entityItem.motionX = rand.nextGaussian() * factor;
	        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
	        entityItem.motionZ = rand.nextGaussian() * factor;
	        world.spawnEntityInWorld(entityItem);
	        
	        world.setBlockAndMetadataWithNotify(x, y, z, FarmcraftLoafOfBread.blockBreadOven.blockID, 0);
	    	System.err.println( world.getBlockMetadata(x, y, z) );
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityBreadOven();
    }
}
