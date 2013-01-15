package fr.farmcraft.enhancement;

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
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.farmcraft.enhancement.client.RenderCauldron;
import fr.farmcraft.enhancement.client.TileEntityAppleRenderer;
import fr.farmcraft.enhancement.client.TileEntityFenceDiagRenderer;
import fr.farmcraft.enhancement.common.CommonProxy;
import fr.farmcraft.enhancement.common.FuelHandler;
import fr.farmcraft.enhancement.common.ItemCoalN;
import fr.farmcraft.enhancement.common.TileEntityApple;
import fr.farmcraft.enhancement.common.TileEntityFenceDiag;
import fr.farmcraft.enhancement.common.blocks.BlockCauldron;
import fr.farmcraft.enhancement.common.blocks.BlockCharCoal;
import fr.farmcraft.enhancement.common.blocks.BlockCoal;
import fr.farmcraft.enhancement.common.blocks.BlockFenceIron;
import fr.farmcraft.enhancement.common.blocks.BlockFenceNether;
import fr.farmcraft.enhancement.common.blocks.BlockFenceWood;
import fr.farmcraft.enhancement.common.blocks.BlockLeaves;
import fr.farmcraft.enhancement.common.blocks.BlockRedstone;

@Mod(modid = "farmcraftenhancement", name = "Farmcraft Enhancement", version = "1.0.8")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class FarmcraftEnhancement {

	public static BlockFenceIron blockFenceIron = new BlockFenceIron(150);
	public static BlockFenceWood blockFenceWood;
	public static BlockFenceNether blockFenceNether;
	public static BlockLeaves blockLeaves;
	public static BlockCauldron blockCauldron;
	public static BlockRedstone blockRedstone = new BlockRedstone(152);
	public static BlockCoal blockCoal = new BlockCoal(203);
	public static BlockCharCoal blockCharCoal = new BlockCharCoal(204);
	
	public static ItemCoalN coal;
	
	@Init
	public void load(FMLInitializationEvent event) {
		
		Block.blocksList[85] = null;
		Block.blocksList[113] = null;
		Block.blocksList[18] = null;
		Block.blocksList[118] = null;
		Item.itemsList[7] = null;
		
		blockFenceWood = new BlockFenceWood(85);
		blockFenceNether = new BlockFenceNether(113);
		blockLeaves = new BlockLeaves(18);
		blockCauldron = new BlockCauldron(118);
		coal = new ItemCoalN(7);
	    
		ClientRegistry.registerTileEntity(TileEntityFenceDiag.class, "TileEntityFenceDiag", new TileEntityFenceDiagRenderer());
		ClientRegistry.registerTileEntity(TileEntityApple.class, "TileEntityApple", new TileEntityAppleRenderer());
		
		GameRegistry.registerBlock(blockFenceIron, "fenceiron");
		GameRegistry.registerBlock(blockRedstone, "redstone");
		GameRegistry.registerBlock(blockCoal, "blockcoal");
		GameRegistry.registerBlock(blockCharCoal, "blockcharcoal");
		GameRegistry.registerBlock(blockCauldron, "blockcauldron");
		
		GameRegistry.addRecipe(new ItemStack(blockFenceIron, 3),
			new Object[] { "s  ", "sss", "sss", 's', Item.ingotIron });
		
		GameRegistry.addRecipe(new ItemStack(blockRedstone, 1),
			new Object[] { "XXX", "XXX", "XXX", 'X', Item.redstone });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.redstone, 9),
			new Object[] { blockRedstone });
		
		GameRegistry.addRecipe(new ItemStack(blockCoal, 1),
			new Object[] { "XXX", "XXX", "XXX", 'X', Item.coal });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 9),
			new Object[] { blockCoal });
			
		GameRegistry.addRecipe(new ItemStack(blockCharCoal, 1),
			new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(Item.coal, 1, 1) });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 9, 1),
			new Object[] { blockCharCoal });
		
		GameRegistry.addRecipe(new ItemStack(blockCauldron, 1),
				new Object[] { "X X", "X X", "XXX", 'X', Item.ingotIron });

		GameRegistry.registerFuelHandler(new FuelHandler());
		
		proxy.registerRenderers();
	}

    // The instance of your mod that Forge uses.
	@Instance("FarmcraftEnhancement")
	public static FarmcraftEnhancement instance;
	
	// Says where the client and enhancement 'proxy' code is loaded.
	@SidedProxy(clientSide="fr.farmcraft.enhancement.client.ClientProxy", serverSide="fr.farmcraft.enhancement.common.CommonProxy")
	public static CommonProxy proxy;
	
}