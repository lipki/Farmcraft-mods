package fr.farmcraft.enhancement.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import fr.farmcraft.enhancement.FarmcraftEnhancement;

public class FuelHandler implements IFuelHandler {
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		
		       if( fuel.itemID == FarmcraftEnhancement.blockCharCoal.blockID ){
			return 14400;
		} else if( fuel.itemID == FarmcraftEnhancement.blockCoal.blockID ){
			return 14400;
		}
		
		return 0;
	}
}