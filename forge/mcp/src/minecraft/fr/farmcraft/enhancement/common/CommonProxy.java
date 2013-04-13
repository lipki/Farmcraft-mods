package fr.farmcraft.enhancement.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.farmcraft.enhancement.client.RenderCauldron;

public class CommonProxy {
	public static String APPLE_PNG = "/mods/enhancement/textures/entity/apple.png";
	public static String FENCE_PNG = "/mods/enhancement/textures/entity/";

	public static final int RENDERCAULDRON = RenderingRegistry.getNextAvailableRenderId();
	
	public void registerRender() {}
}
