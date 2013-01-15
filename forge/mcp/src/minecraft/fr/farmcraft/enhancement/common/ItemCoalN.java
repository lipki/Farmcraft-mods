package fr.farmcraft.enhancement.common;

import net.minecraft.item.ItemCoal;

public class ItemCoalN extends ItemCoal {

	public ItemCoalN(int id) {
		super(id);
		setIconIndex(128);
		setItemName("coal");
	}
	
	@Override
	public String getTextureFile() { return CommonProxy.BLOCK_PNG; }

	@Override
	public int getIconFromDamage(int par1) {
        if( par1 == 1 )
        	  return 129;
        else return 128;
    }

}
