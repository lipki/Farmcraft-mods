package fr.farmcraft.enhancement.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.farmcraft.enhancement.common.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	public static final int RENDERCAULDRON = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		
		RenderingRegistry.registerBlockHandler(new RenderCauldron());
	}
	
}
