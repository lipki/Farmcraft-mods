package fr.farmcraft.loafofbread;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.BaseMod;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.loafofbread.client.GuiHandler;
import fr.farmcraft.loafofbread.client.bread.TileEntityBreadRenderer;
import fr.farmcraft.loafofbread.client.breadoven.TileEntityBreadOvenRenderer;
import fr.farmcraft.loafofbread.common.CommonProxy;
import fr.farmcraft.loafofbread.common.blocks.BlockBread;
import fr.farmcraft.loafofbread.common.blocks.BlockBreadOven;
import fr.farmcraft.loafofbread.common.blocks.BlockHay;
import fr.farmcraft.loafofbread.common.blocks.BlockHayCarpet;
import fr.farmcraft.loafofbread.common.bread.TileEntityBread;
import fr.farmcraft.loafofbread.common.breadoven.TileEntityBreadOven;
import fr.farmcraft.loafofbread.common.items.ItemBreadDough;
import fr.farmcraft.loafofbread.common.items.ItemFlavor;
import fr.farmcraft.loafofbread.common.items.ItemSliceOfBread;
import fr.farmcraft.loafofbread.common.items.ItemYeast;

@Mod(modid = "farmcraftloafofbread", name = "FarmcraftLoafOfBread", version = "1.0.64")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class FarmcraftLoafOfBread {

	public static ItemYeast itemYeast = new ItemYeast(200);
	public static ItemFlavor itemFlavor = new ItemFlavor(201);
	public static ItemBreadDough itemBreadDough = new ItemBreadDough(203);
	
	public static BlockHay blockHay = new BlockHay(156);
	public static BlockHayCarpet blockHayCarpet = new BlockHayCarpet(200);
	public static BlockBreadOven blockBreadOven = new BlockBreadOven(201);
	public static BlockBread blockBread = new BlockBread(202);
	
	private GuiHandler guiHandler = new GuiHandler();
	
	@Init
	public void load(FMLInitializationEvent event) {
		
		Item.itemsList[41] = null;
		Item.bread = new ItemSliceOfBread(41);
	    
		GameRegistry.registerBlock(blockHay, "blockHay");
		GameRegistry.registerBlock(blockHayCarpet, "blockHayCarpet");
		GameRegistry.registerBlock(blockBreadOven, "blockBreadOven");
		GameRegistry.registerBlock(blockBread, "blockBread");
		
		ClientRegistry.registerTileEntity(TileEntityBreadOven.class, "TileEntityBreadOven", new TileEntityBreadOvenRenderer());
		ClientRegistry.registerTileEntity(TileEntityBread.class, "TileEntityBread", new TileEntityBreadRenderer());
		
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
		
		GameRegistry.addRecipe(new ItemStack(blockBreadOven, 1),
			new Object[] { "sXs", "sss", 'X', Block.cobblestone, 's', Item.ingotIron });
		GameRegistry.addRecipe(new ItemStack(blockBreadOven, 1),
			new Object[] { "sXs", "sss", 'X', itemBreadDough, 's', Item.ingotIron });
		
		instance = this;
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		
		proxy.registerRenderers();
	}

    // The instance of your mod that Forge uses.
	@Instance("FarmcraftLoafOfBread")
	public static FarmcraftLoafOfBread instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="fr.farmcraft.loafofbread.client.ClientProxy", serverSide="fr.farmcraft.loafofbread.common.CommonProxy")
	public static CommonProxy proxy;
	
}
