package fr.farmcraft.divers;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fr.farmcraft.divers.common.CommonProxy;
import fr.farmcraft.divers.common.blocks.BlockInfiniteDispenser;
import fr.farmcraft.divers.common.blocks.BlockLanterne;
import fr.farmcraft.divers.common.blocks.BlockSpeedy;

@Mod(modid = "farmcraftdivers", name = "Farmcraft Divers", version = "1.0.65")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class FarmcraftDivers {

	public static BlockSpeedy blockSpeedy = new BlockSpeedy(151);
	public static BlockLanterne blockLanterne = new BlockLanterne(154);
	public static BlockInfiniteDispenser blockInfiniteDispenser = new BlockInfiniteDispenser(155);

	@Init
	public void load(FMLInitializationEvent event) {
		
		blockInfiniteDispenser.setResistance(6000000);
		blockInfiniteDispenser.setBlockUnbreakable();
		blockInfiniteDispenser.setStepSound(Block.soundStoneFootstep);
		blockInfiniteDispenser.setCreativeTab(CreativeTabs.tabRedstone);
		blockInfiniteDispenser.setRequiresSelfNotify();
		
		blockLanterne.setHardness(.3F);
		blockLanterne.setResistance(56.34F);
		blockLanterne.setLightValue(1);
		blockLanterne.setCreativeTab(CreativeTabs.tabBlock);
		blockLanterne.setStepSound(Block.soundGlassFootstep);
		
		blockSpeedy.setResistance(6000000);
		blockSpeedy.setLightValue(1);
		blockSpeedy.setBlockUnbreakable();
		blockSpeedy.setStepSound(Block.soundStoneFootstep);
		blockSpeedy.setCreativeTab(CreativeTabs.tabBlock);
		
		setName( blockInfiniteDispenser, "Infinite Dispenser");
		setName( blockLanterne, "Lanterne");
		setName( blockSpeedy, "Auto Way");
		
		GameRegistry.registerBlock( blockSpeedy, "blockSpeedy" );
		GameRegistry.registerBlock( blockLanterne, "blockLanterne" );
		GameRegistry.registerBlock( blockInfiniteDispenser, "blockInfiniteDispenser" );
		
		GameRegistry.addShapelessRecipe(new ItemStack(blockLanterne, 1), 
			new Object[] { Block.torchRedstoneActive, Block.redstoneLampIdle });
	}
    
    public static void setName( Block block, String name) {
    	block.setBlockName(name);
		LanguageRegistry.addName(block, name);
    }

	@Instance("FarmcraftServer")
	public static FarmcraftDivers instance;
	
	@SidedProxy(clientSide="fr.farmcraft.divers.client.ClientProxy", serverSide="fr.farmcraft.divers.common.CommonProxy")
	public static CommonProxy proxy;
	
}