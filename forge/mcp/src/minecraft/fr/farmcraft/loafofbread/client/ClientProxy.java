package fr.farmcraft.loafofbread.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import fr.farmcraft.loafofbread.FarmcraftLoafOfBread;
import fr.farmcraft.loafofbread.client.breadoven.RenderBreadOven;
import fr.farmcraft.loafofbread.client.millstone.RenderMillstone;
import fr.farmcraft.loafofbread.common.CommonProxy;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRender() {
		RenderingRegistry.registerBlockHandler(new RenderBreadOven());
		RenderingRegistry.registerBlockHandler(new RenderMillstone());
	}
	
}
