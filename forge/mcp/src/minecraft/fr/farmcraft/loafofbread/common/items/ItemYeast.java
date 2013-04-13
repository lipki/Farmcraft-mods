package fr.farmcraft.loafofbread.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class ItemYeast extends Item {

	public ItemYeast(int id) {
		super(id);
    	setCreativeTab(CreativeTabs.tabFood);
    	setUnlocalizedName("loafofbread:yeast");
		LanguageRegistry.addName(this, "Yeast");
	}

}