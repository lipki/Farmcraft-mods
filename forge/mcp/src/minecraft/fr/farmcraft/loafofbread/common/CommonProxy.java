package fr.farmcraft.loafofbread.common;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class CommonProxy {
	
	public static String BREAD_PNG = "/mods/loafofbread/textures/entity/bread.png";
	public static String MILLSTONE_PNG = "/mods/loafofbread/textures/entity/millstone.png";
	public static String BREADOVEN_PNG = "/mods/loafofbread/textures/entity/breadoven.png";
	public static String GUI_MILLSTONE_PNG = "/mods/loafofbread/textures/gui/millstone.png";

	public static int RENDERMILLSTONE = RenderingRegistry.getNextAvailableRenderId();
	public static int RENDERBREADOVEN = RenderingRegistry.getNextAvailableRenderId();
	 
	public void registerRender() {}
	
}