package fr.farmcraft.loafofbread.common.breadoven;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import fr.farmcraft.loafofbread.common.CommonProxy;

public class GuiMillstone extends GuiContainer {

    private TileEntityBreadOven tileEntityM;

    public GuiMillstone (InventoryPlayer inventoryPlayer, TileEntityBreadOven tileEntity) {
        super(new ContainerMillstone(inventoryPlayer, tileEntity));
        tileEntityM = tileEntity;
    }

    protected void drawGuiContainerForegroundLayer() {
        fontRenderer.drawString("Millstone", 8, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
	}

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = mc.renderEngine.getTexture( CommonProxy.GUI_MILLSTONE_PNG );
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

        if (tileEntityM.isBurning()) {
            int burn = tileEntityM.getBurnTimeRemainingScaled(29);
            drawTexturedModalRect(j+75, k+11+burn, 185, 0+burn, 12, 29-burn);
        }
        
        int update = tileEntityM.getCookProgressScaled(28);
        drawTexturedModalRect(j+87, k+11, 176, 0, 8, update);
	}
}