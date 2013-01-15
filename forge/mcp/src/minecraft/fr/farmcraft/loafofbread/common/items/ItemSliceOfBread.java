package fr.farmcraft.loafofbread.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class ItemSliceOfBread extends ItemFood {

	public ItemSliceOfBread(int id) {
		super(id, 5, 0.6F, false);
		setIconIndex(131);
		setItemName("sliceofbread");
		setCreativeTab(CreativeTabs.tabFood);
		LanguageRegistry.addName(this, "Slice Of Bread");
	}
	
	@Override
	public String getTextureFile() { return CommonProxy.BLOCK_PNG; }
}