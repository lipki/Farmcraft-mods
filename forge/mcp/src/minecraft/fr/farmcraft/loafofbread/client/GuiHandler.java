package fr.farmcraft.loafofbread.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.IGuiHandler;
import fr.farmcraft.loafofbread.common.breadoven.ContainerMillstone;
import fr.farmcraft.loafofbread.common.breadoven.GuiMillstone;
import fr.farmcraft.loafofbread.common.breadoven.TileEntityBreadOven;

public class GuiHandler implements IGuiHandler {
	
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityBreadOven){
            return new ContainerMillstone(player.inventory, (TileEntityBreadOven) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityBreadOven){
            return new GuiMillstone(player.inventory, (TileEntityBreadOven) tileEntity);
        }
        return null;
    }
    
}