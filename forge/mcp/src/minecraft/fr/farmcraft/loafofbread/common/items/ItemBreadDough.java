package fr.farmcraft.loafofbread.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class ItemBreadDough extends Item {
	
	public ItemBreadDough(int id) {
		super(id);
		setUnlocalizedName("loafofbread:bread_dough");
		setCreativeTab(CreativeTabs.tabFood);
		LanguageRegistry.addName(this, "Bread Dough");
	}

}