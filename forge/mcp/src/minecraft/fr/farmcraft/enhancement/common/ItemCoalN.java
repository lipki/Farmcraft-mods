package fr.farmcraft.enhancement.common;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.farmcraft.enhancement.FarmcraftEnhancement;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemCoalN extends ItemCoal {
	
    public static final String[] textures = new String[] {"coal", "enhancement:charcoal"};
	@SideOnly(Side.CLIENT)
    private Icon[] icons = new Icon[2];

	public ItemCoalN(int id) {
		super(id);
		setUnlocalizedName("coal");
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int damage) {
        return this.icons[damage];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs creativeTabs, List list) {
        for (int j = 0; j < 2; ++j) list.add(new ItemStack(par1, 1, j));
    }

    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister) {
        this.icons = new Icon[textures.length];

        for (int i = 0; i < textures.length; ++i)
            this.icons[i] = iconRegister.registerIcon(textures[i]);
    }

}
