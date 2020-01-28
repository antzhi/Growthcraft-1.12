package growthcraft.distillery.common;

import growthcraft.bees.common.items.ItemHoneyCombEmpty;
import growthcraft.core.shared.GrowthcraftCoreApis;
import growthcraft.core.shared.definition.BlockDefinition;
import growthcraft.core.shared.definition.ItemDefinition;
import growthcraft.distillery.common.Items.Bagasse;
import growthcraft.distillery.common.blocks.BlockBagasse;
import growthcraft.distillery.shared.Init.GrowthcraftDistilleryBlocks;
import growthcraft.distillery.shared.Init.GrowthcraftDistilleryItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import static growthcraft.core.shared.GrowthcraftCoreApis.tabGrowthcraft;

public class Init {
    private Init() {
    }

    public static void preInitBlocks() {
        GrowthcraftDistilleryBlocks.bagasse = new BlockDefinition(new BlockBagasse("blockbagasse"));
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        GrowthcraftDistilleryBlocks.bagasse.getBlock().setCreativeTab(GrowthcraftCoreApis.tabGrowthcraft);
        GrowthcraftDistilleryBlocks.bagasse.registerBlock(registry);
    }

    public static void registerBlockItems(IForgeRegistry<Item> registry) {
        GrowthcraftDistilleryBlocks.bagasse.registerBlockItem(registry);
    }

    public static void registerBlockRender() {
        GrowthcraftDistilleryBlocks.bagasse.registerItemRender();
    }

    public static void preInitItems() {
        GrowthcraftDistilleryItems.bagasse = new ItemDefinition(new Bagasse("bagasse"));
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        GrowthcraftDistilleryItems.bagasse.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.bagasse.registerItem(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemRenders() {
        GrowthcraftDistilleryItems.bagasse.registerRender();
    }
}
