package fr.farmcraft.loafofbread;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.farmcraft.loafofbread.client.bread.TileEntityBreadRenderer;
import fr.farmcraft.loafofbread.client.breadoven.TileEntityBreadOvenRenderer;
import fr.farmcraft.loafofbread.client.millstone.TileEntityMillstoneRenderer;
import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.TileEntityBread;
import fr.farmcraft.loafofbread.common.TileEntityBreadOven;
import fr.farmcraft.loafofbread.common.TileEntityMillstone;
import fr.farmcraft.loafofbread.common.blocks.BlockBread;
import fr.farmcraft.loafofbread.common.blocks.BlockBreadOven;
import fr.farmcraft.loafofbread.common.blocks.BlockHay;
import fr.farmcraft.loafofbread.common.blocks.BlockHayCarpet;
import fr.farmcraft.loafofbread.common.blocks.BlockMillstone;
import fr.farmcraft.loafofbread.common.items.ItemBreadDough;
import fr.farmcraft.loafofbread.common.items.ItemFlavor;
import fr.farmcraft.loafofbread.common.items.ItemSliceOfBread;
import fr.farmcraft.loafofbread.common.items.ItemYeast;

@Mod(modid = "farmcraftloafofbread", name = "FarmcraftLoafOfBread", version = "1.0.71")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class FarmcraftLoafOfBread {

	@Instance("FarmcraftLoafOfBread")
	public static FarmcraftLoafOfBread instance;
	
	@SidedProxy(clientSide="fr.farmcraft.loafofbread.client.ClientProxy", serverSide="fr.farmcraft.loafofbread.common.CommonProxy", bukkitSide="fr.farmcraft.loafofbread.common.CommonProxy")
	public static CommonProxy proxy;

	public static ItemYeast itemYeast = new ItemYeast(2240);
	public static ItemFlavor itemFlavor = new ItemFlavor(2239);
	public static ItemBreadDough itemBreadDough = new ItemBreadDough(2238);
	
	public static BlockHay blockHay = new BlockHay(2240);
	public static BlockHayCarpet blockHayCarpet = new BlockHayCarpet(2239);
	public static BlockBreadOven blockBreadOven = new BlockBreadOven(2238);
	public static BlockMillstone blockMillstone = new BlockMillstone(2236);
	public static BlockBread blockBread = new BlockBread(2237);

	@PreInit
	public void initConfig(FMLPreInitializationEvent event) {
		proxy.registerRender();
	}
	
	@Init
	public void load(FMLInitializationEvent event) {

		resetItem();
		registerBlock();
		registerItem();
		registerCraft();
		
		ClientRegistry.registerTileEntity(TileEntityBreadOven.class, "TileEntityBreadOven", new TileEntityBreadOvenRenderer());
		ClientRegistry.registerTileEntity(TileEntityMillstone.class, "TileEntityMillstone", new TileEntityMillstoneRenderer());
		ClientRegistry.registerTileEntity(TileEntityBread.class, "TileEntityBread", new TileEntityBreadRenderer());
		
		instance = this;
	}
	
	private void resetItem() {
		Item.itemsList[41] = null;
	}
	
	private void registerBlock() {
		GameRegistry.registerBlock(blockHay, "blockHay");
		GameRegistry.registerBlock(blockHayCarpet, "blockHayCarpet");
		GameRegistry.registerBlock(blockBreadOven, "blockBreadOven");
		GameRegistry.registerBlock(blockMillstone, "blockMillstone");
		GameRegistry.registerBlock(blockBread, "blockBread");
	}
	
	private void registerItem() {
		Item.bread = new ItemSliceOfBread(41);
	}
	
	private void registerCraft() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemYeast, 1),
			new Object[] { Block.mushroomBrown });
		GameRegistry.addShapelessRecipe(new ItemStack(itemYeast, 1),
			new Object[] { Block.mushroomRed });
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemBreadDough, 1),
			new Object[] { Item.bucketWater, itemYeast, itemFlavor });
		
		GameRegistry.addShapelessRecipe(new ItemStack(blockHayCarpet, 9),
			new Object[] { blockHay });
		GameRegistry.addRecipe(new ItemStack(blockHay, 1),
			new Object[] { "XXX", "XXX", "XXX", 'X', blockHayCarpet });
		
		GameRegistry.addRecipe(new ItemStack(blockMillstone, 1),
			new Object[] { "sXs", "sss", 'X', Block.cobblestone, 's', Item.ingotIron });
		
		GameRegistry.addRecipe(new ItemStack(blockBreadOven, 1),
			new Object[] { "sss", "ss ", "sss", 's', Block.cobblestone });
	}
	
}
