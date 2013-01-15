package fr.farmcraft.loafofbread.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class ItemBreadDough extends Item {
	
	public ItemBreadDough(int id) {
		super(id);
		setIconIndex(130);
		setItemName("breaddough");
		setCreativeTab(CreativeTabs.tabFood);
		LanguageRegistry.addName(this, "Bread Dough");
	}
	
	@Override
	public String getTextureFile() { return CommonProxy.BLOCK_PNG; }

}