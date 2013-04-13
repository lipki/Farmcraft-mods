package fr.farmcraft.trampoline;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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
import fr.farmcraft.trampoline.client.TileEntityTrampolineRenderer;
import fr.farmcraft.trampoline.common.BlockTrampoline;
import fr.farmcraft.trampoline.common.CommonProxy;
import fr.farmcraft.trampoline.common.TileEntityTrampoline;

@Mod(modid = "farmcrafttrampoline", name = "Farmcraft Trampoline", version = "1.0.55")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class FarmcraftTrampoline {

	@Instance("FarmcraftTrampoline")
	public static FarmcraftTrampoline instance;
	
	@SidedProxy(clientSide="fr.farmcraft.trampoline.client.ClientProxy", serverSide="fr.farmcraft.trampoline.common.CommonProxy", bukkitSide="fr.farmcraft.trampoline.common.CommonProxy")
	public static CommonProxy proxy;

	public static BlockTrampoline blockTrampoline = new BlockTrampoline(2248);

	@PreInit
	public void initConfig(FMLPreInitializationEvent event) {
		proxy.registerRender();
	}
	
	@Init
	public void load(FMLInitializationEvent event) {

		registerBlock();
		registerCraft();
		
		ClientRegistry.registerTileEntity(TileEntityTrampoline.class, "TileEntityTrampoline", new TileEntityTrampolineRenderer());
		
	}
	
	private void registerBlock() {
		GameRegistry.registerBlock( blockTrampoline, "blockTrampoline" );
	}
	
	private void registerCraft() {

		GameRegistry.addRecipe(new ItemStack(blockTrampoline, 1),
			new Object[] { "XXX", "XXX", "XXX", 'X', Item.slimeBall, });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 9),
			new Object[] { blockTrampoline });
	}
	
}