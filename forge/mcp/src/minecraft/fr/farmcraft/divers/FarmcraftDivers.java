package fr.farmcraft.divers;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.divers.common.CommonProxy;
import fr.farmcraft.divers.common.blocks.BlockInfiniteDispenser;
import fr.farmcraft.divers.common.blocks.BlockLanterne;
import fr.farmcraft.divers.common.blocks.BlockSpeedy;

@Mod(modid = "farmcraftdivers", name = "Farmcraft Divers", version = "1.0.93")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class FarmcraftDivers {

	@Instance("Mod")
	public static FarmcraftDivers modInstance;
	
	@SidedProxy(clientSide="fr.farmcraft.divers.client.ClientProxy", serverSide="fr.farmcraft.divers.common.CommonProxy", bukkitSide="fr.farmcraft.divers.common.CommonProxy")
	public static CommonProxy proxy;

	public static BlockSpeedy blockSpeedy = new BlockSpeedy(2249);
	public static BlockLanterne blockLanterne = new BlockLanterne(2250);
	public static BlockInfiniteDispenser blockInfiniteDispenser = new BlockInfiniteDispenser(2251);

	@PreInit
	public void initConfig(FMLPreInitializationEvent event) {
		proxy.registerRender();
	}
	
	@Init
	public void load(FMLInitializationEvent event) {

		registerBlock();
		registerCraft();
		
	}
	
	private void registerBlock() {
		GameRegistry.registerBlock( blockSpeedy, "blockSpeedy" );
		GameRegistry.registerBlock( blockLanterne, "blockLanterne" );
		GameRegistry.registerBlock( blockInfiniteDispenser, "blockInfiniteDispenser" );
	}
	
	private void registerCraft() {

		GameRegistry.addShapelessRecipe(new ItemStack(blockLanterne, 1), 
			new Object[] { Block.torchRedstoneActive, Block.redstoneLampIdle });
	}
	
}