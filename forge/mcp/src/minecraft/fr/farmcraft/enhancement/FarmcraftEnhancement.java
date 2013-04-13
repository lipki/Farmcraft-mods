package fr.farmcraft.enhancement;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import cpw.mods.fml.common.registry.LanguageRegistry;
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
import fr.farmcraft.enhancement.common.blocks.BlockFenceDiag;
import fr.farmcraft.enhancement.common.blocks.BlockLeavesPlus;

@Mod(modid = "farmcraftenhancement", name = "Farmcraft Enhancement", version = "1.2.7")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class FarmcraftEnhancement {

	@Instance("FarmcraftEnhancement")
	public static FarmcraftEnhancement instance;
	
	@SidedProxy(clientSide="fr.farmcraft.enhancement.client.ClientProxy", serverSide="fr.farmcraft.enhancement.common.CommonProxy", bukkitSide="fr.farmcraft.enhancement.common.CommonProxy")
	public static CommonProxy proxy;

	public static BlockFenceDiag blockFenceJungle = new BlockFenceDiag(2157, Material.wood, "wood_jungle", "Fence wood jungle");
	public static BlockFenceDiag blockFenceSpruce = new BlockFenceDiag(2156, Material.wood, "wood_spruce", "Fence wood spruce");
	public static BlockFenceDiag blockFenceBirch = new BlockFenceDiag(2255, Material.wood, "wood_birch", "Fence wood birch");
	public static BlockFenceDiag blockFenceIron = new BlockFenceDiag(2252, Material.iron, "blockIron", "Fence Iron");
	public static BlockFenceDiag blockFenceWood;
	public static BlockFenceDiag blockFenceNether;
	public static BlockLeavesPlus blockLeaves;
	public static BlockCauldron blockCauldron;
	public static BlockCoal blockCoal = new BlockCoal(2253);
	public static BlockCharCoal blockCharCoal = new BlockCharCoal(2254);
	
	public static ItemCoalN coal;

	@PreInit
	public void initConfig(FMLPreInitializationEvent event) {
		proxy.registerRender();
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		
		resetBlock();
		resetItem();
		registerBlock();
		registerItem();
		registerCraft();
		
		ClientRegistry.registerTileEntity(TileEntityFenceDiag.class, "TileEntityFenceDiag", new TileEntityFenceDiagRenderer());
		ClientRegistry.registerTileEntity(TileEntityApple.class, "TileEntityApple", new TileEntityAppleRenderer());
		
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	private void resetBlock() {
		Block.blocksList[85] = null;
		Block.blocksList[113] = null;
		Block.blocksList[18] = null;
		Block.blocksList[118] = null;
	}
	
	private void resetItem() {
		Item.itemsList[7] = null;
	}
	
	private void registerBlock() {
		blockFenceWood = new BlockFenceDiag(85, Material.wood, "wood", "Fence Wood");
		blockFenceNether = new BlockFenceDiag(113, Material.rock, "netherBrick", "Fence Nether");
		blockLeaves = new BlockLeavesPlus(18);
		blockCauldron = new BlockCauldron(118);
		GameRegistry.registerBlock(blockFenceIron, "fenceiron");
		GameRegistry.registerBlock(blockFenceJungle, "fencejungle");
		GameRegistry.registerBlock(blockFenceSpruce, "fencespruce");
		GameRegistry.registerBlock(blockFenceBirch, "fencebirch");
		GameRegistry.registerBlock(blockCoal, "blockcoal");
		GameRegistry.registerBlock(blockCharCoal, "blockcharcoal");
		GameRegistry.registerBlock(blockCauldron, "blockcauldron");
	}
	
	private void registerItem() {
		coal = new ItemCoalN(7);
	}
	
	private void registerCraft() {

		GameRegistry.addRecipe(new ItemStack(blockFenceIron, 3),
			new Object[] { "s  ", "sss", "sss", 's', Item.ingotIron });

		GameRegistry.addRecipe(new ItemStack(blockFenceJungle, 3),
			new Object[] { "s  ", "sss", "sss", 's', new ItemStack(Block.planks, 1, 3) });

		GameRegistry.addRecipe(new ItemStack(blockFenceSpruce, 3),
			new Object[] { "s  ", "sss", "sss", 's', new ItemStack(Block.planks, 1, 1) });
		
		GameRegistry.addRecipe(new ItemStack(blockFenceBirch, 3),
				new Object[] { "s  ", "sss", "sss", 's', new ItemStack(Block.planks, 1, 2) });
		
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
	}
	
}