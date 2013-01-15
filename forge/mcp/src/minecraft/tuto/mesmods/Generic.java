package tuto.mesmods;

import tuto.mesmods.trampoline.TileEntityTrampoline;
import tuto.mesmods.trampoline.TileEntityTrampolineRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="generic", name="Generic", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Generic {
	
	public static String terrain = "/tuto/mesmods/assets/block.png";
	
	//public static BlockTrampoline blockTrampoline = new BlockTrampoline(501);
	
	@Init
	public void load(FMLInitializationEvent event) {
		
		MinecraftForgeClient.preloadTexture(this.terrain);
		
		ClientRegistry.registerTileEntity(TileEntityTrampoline.class, "TileEntitytrampoline", new TileEntityTrampolineRenderer());

		//GameRegistry.registerBlock( blockTrampoline, "blockTrampoline" );

		//GameRegistry.addRecipe(new ItemStack(blockTrampoline, 1),
		//	new Object[] { "XXX", "XXX", "XXX", 'X', Item.slimeBall, });
		//GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall, 9),
		//	new Object[] { blockTrampoline });
		
	}
}