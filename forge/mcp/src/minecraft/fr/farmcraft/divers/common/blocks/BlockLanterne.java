package fr.farmcraft.divers.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class BlockLanterne extends Block {
	
    public BlockLanterne (int id) { super(id, 212, Material.glass); }
    
    public int quantityDroppedWithBonus(int i, Random random) {
        return MathHelper.clamp_int(quantityDropped(random) + random.nextInt(i + 1), 1, 4);
    }
    
    public int quantityDropped(Random random) { return 2 + random.nextInt(3); }
    public int idDropped(int par1, Random random, int par3) { return Item.lightStoneDust.itemID; }
    
}

 
