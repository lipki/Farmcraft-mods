package fr.farmcraft.enhancement.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.farmcraft.enhancement.FarmcraftEnhancement;
import fr.farmcraft.enhancement.common.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRender() {
		RenderingRegistry.registerBlockHandler(new RenderCauldron());
	}
	
}
