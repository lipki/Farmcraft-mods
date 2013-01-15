package fr.farmcraft.loafofbread.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class ItemFlavor extends Item {
	
	public ItemFlavor(int id) {
		super(id);
		setIconIndex(129);
		setItemName("flavor");
		setCreativeTab(CreativeTabs.tabFood);
		LanguageRegistry.addName(this, "Flavor");
	}
	
	@Override
	public String getTextureFile() { return CommonProxy.BLOCK_PNG; }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return false;
    }
}