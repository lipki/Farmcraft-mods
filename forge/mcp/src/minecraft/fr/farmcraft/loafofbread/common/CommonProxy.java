package fr.farmcraft.loafofbread.common;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class CommonProxy {
	
	public static String BLOCK_PNG = "/fr/farmcraft/loafofbread/assets/terrain.png";
	public static String BREAD_PNG = "/fr/farmcraft/loafofbread/assets/bread.png";
	public static String MILLSTONE_PNG = "/fr/farmcraft/loafofbread/assets/millstone.png";
	public static String BREADOVEN_PNG = "/fr/farmcraft/loafofbread/assets/breadoven.png";
	public static String GUI_MILLSTONE_PNG = "/fr/farmcraft/loafofbread/assets/gui-millstone.png";

	public static int RENDERMILLSTONE = RenderingRegistry.getNextAvailableRenderId();
	public static int RENDERHAYCARPET = RenderingRegistry.getNextAvailableRenderId();
	 
	public void registerRenderers() {}
	
}