package fr.farmcraft.loafofbread.common.breadoven;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;

public class RecipesMillstone {
	
	private static final RecipesMillstone millstoneBase = new RecipesMillstone();
	
	private Map millstoneList = new HashMap();
	private Map millstoneExperience = new HashMap();
	
	public static final RecipesMillstone smelting() {
		return millstoneBase;
	}
	
	private RecipesMillstone() {
		addSmelting(Item.wheat.itemID, new ItemStack(FarmcraftLoafOfBread.itemFlavor, 1, 0), new ItemStack(FarmcraftLoafOfBread.blockHayCarpet, 1, 0), 0.7F);
	}
	
	public void addSmelting(int id, ItemStack itemStack, ItemStack itemStack2, float experience) {
		ItemStack[] l = {itemStack, itemStack2};
		millstoneList.put(Integer.valueOf(id), l);
		millstoneExperience.put(Integer.valueOf(itemStack.itemID), Float.valueOf(experience));
		millstoneExperience.put(Integer.valueOf(itemStack2.itemID), Float.valueOf(experience));
	}
	
	public ItemStack[] getSmeltingResult(int id) {
		return (ItemStack[]) millstoneList.get(Integer.valueOf(id));
	}
	
	public Map getSmeltingList() {
		return millstoneList;
	}
	public float getExperience(int par1) {
		return this.millstoneExperience.containsKey(Integer.valueOf(par1)) ? ((Float)this.millstoneExperience.get(Integer.valueOf(par1))).floatValue() : 0.0F;
	}
}