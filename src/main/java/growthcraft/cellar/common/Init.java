package growthcraft.cellar.common;

import growthcraft.cellar.client.render.RenderBrewKettle;
import growthcraft.cellar.client.render.RenderCultureJar;
import growthcraft.cellar.client.render.RenderFruitPress;
import growthcraft.cellar.common.block.*;
import growthcraft.cellar.common.item.*;
import growthcraft.cellar.common.potion.PotionCellar;
import growthcraft.cellar.common.stats.CellarAchievement;
import growthcraft.cellar.common.tileentity.*;
import growthcraft.cellar.shared.CellarRegistry;
import growthcraft.cellar.shared.GrowthcraftCellarApis;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.booze.BoozeEffect;
import growthcraft.cellar.shared.booze.effect.EffectTipsy;
import growthcraft.cellar.shared.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.shared.init.GrowthcraftCellarItems;
import growthcraft.cellar.shared.init.GrowthcraftCellarItems.EnumYeast;
import growthcraft.cellar.shared.init.GrowthcraftCellarPotions;
import growthcraft.cellar.shared.processing.heatsource.user.UserHeatSourceEntry;
import growthcraft.core.shared.CoreRegistry;
import growthcraft.core.shared.definition.BlockDefinition;
import growthcraft.core.shared.definition.BlockTypeDefinition;
import growthcraft.core.shared.definition.ItemDefinition;
import growthcraft.core.shared.effect.EffectRegistry;
import growthcraft.core.shared.handlers.BlockColorHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import static growthcraft.core.shared.GrowthcraftCoreApis.tabGrowthcraft;

public class Init {
    private Init() {
    }

    ///////////
    // Blocks
    ///////////

    public static void preInitBlocks() {
        GrowthcraftCellarBlocks.brewKettle = new BlockDefinition(new BlockBrewKettle("brew_kettle"));
        GrowthcraftCellarBlocks.fermentBarrel = new BlockDefinition(new BlockFermentBarrel("ferment_barrel"));
        GrowthcraftCellarBlocks.cultureJar = new BlockDefinition(new BlockCultureJar("culture_jar"));
        GrowthcraftCellarBlocks.fruitPress = new BlockDefinition(new BlockFruitPress("fruit_press"));
        GrowthcraftCellarBlocks.fruitPresser = new BlockDefinition(new BlockFruitPresser("fruit_presser"));
        GrowthcraftCellarBlocks.blockCorkLog = new BlockDefinition(new BlockCorkLog("cork_log"));
        GrowthcraftCellarBlocks.blockCorkLogStripped = new BlockDefinition(new BlockCorkLogStripped("cork_log_stripped"));
        GrowthcraftCellarBlocks.blockCorkSapling = new BlockDefinition(new BlockCorkSapling("cork_sapling"));
        GrowthcraftCellarBlocks.still = new BlockDefinition(new BlockStill("still"));
        GrowthcraftCellarBlocks.blockCorkLeaves = new BlockTypeDefinition<BlockCorkLeaves>( new BlockCorkLeaves("cork_leaves"));

    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        GrowthcraftCellarBlocks.brewKettle.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.brewKettle.registerBlock(registry);

        GrowthcraftCellarBlocks.fermentBarrel.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.fermentBarrel.registerBlock(registry);

        GrowthcraftCellarBlocks.cultureJar.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.cultureJar.registerBlock(registry);

        GrowthcraftCellarBlocks.fruitPress.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.fruitPress.registerBlock(registry);

        GrowthcraftCellarBlocks.fruitPresser.registerBlock(registry);

        GrowthcraftCellarBlocks.blockCorkLog.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.blockCorkLog.registerBlock(registry);

        GrowthcraftCellarBlocks.blockCorkLogStripped.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.blockCorkLogStripped.registerBlock(registry);

        GrowthcraftCellarBlocks.blockCorkSapling.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.blockCorkSapling.registerBlock(registry);

        GrowthcraftCellarBlocks.blockCorkLeaves.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.blockCorkLeaves.registerBlock(registry);

        GrowthcraftCellarBlocks.still.getBlock().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarBlocks.still.registerBlock(registry);
    }

    public static void registerBlockItems(IForgeRegistry<Item> registry) {
        GrowthcraftCellarBlocks.brewKettle.registerBlockItem(registry);
        GrowthcraftCellarBlocks.fermentBarrel.registerBlockItem(registry);
        GrowthcraftCellarBlocks.cultureJar.registerBlockItem(registry);
        GrowthcraftCellarBlocks.fruitPress.registerBlockItem(registry);
        GrowthcraftCellarBlocks.blockCorkLog.registerBlockItem(registry);
        GrowthcraftCellarBlocks.blockCorkLogStripped.registerBlockItem(registry);
        GrowthcraftCellarBlocks.blockCorkSapling.registerBlockItem(registry);
        GrowthcraftCellarBlocks.blockCorkLeaves.registerBlockItem(registry);
        GrowthcraftCellarBlocks.still.registerBlockItem(registry);
    }

    public static void registerBlockRenders() {
        GrowthcraftCellarBlocks.brewKettle.registerItemRender();
        GrowthcraftCellarBlocks.fermentBarrel.registerItemRender();
        GrowthcraftCellarBlocks.cultureJar.registerItemRender();
        GrowthcraftCellarBlocks.fruitPress.registerItemRender();
        GrowthcraftCellarBlocks.blockCorkLog.registerItemRender();
        GrowthcraftCellarBlocks.blockCorkLogStripped.registerItemRender();
        GrowthcraftCellarBlocks.blockCorkSapling.registerItemRender();
        GrowthcraftCellarBlocks.blockCorkLeaves.registerItemRender();
        GrowthcraftCellarBlocks.still.registerItemRender();
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockColorHandlers() {
        BlockColorHandler.registerBlockColorHandler(
                GrowthcraftCellarBlocks.blockCorkLeaves.getBlock(),
                BlockCorkLeaves.LEAVES_COLOR
        );
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockSpecialRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewKettle.class, new RenderBrewKettle());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCultureJar.class, new RenderCultureJar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFruitPress.class, new RenderFruitPress());
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityBrewKettle.class, Reference.MODID + ":brew_kettle");
        GameRegistry.registerTileEntity(TileEntityFermentBarrel.class, Reference.MODID + ":ferment_barrel");
        GameRegistry.registerTileEntity(TileEntityCultureJar.class, Reference.MODID + ":culture_jar");
        GameRegistry.registerTileEntity(TileEntityFruitPress.class, Reference.MODID + ":fruit_press");
        GameRegistry.registerTileEntity(TileEntityFruitPresser.class, Reference.MODID + ":fruit_presser");
        GameRegistry.registerTileEntity(TileEntityStill.class, new ResourceLocation(Reference.MODID+"still"));
    }

    @SideOnly(Side.CLIENT)
    public static void setCustomBlockStateMappers() {
        ModelLoader.setCustomStateMapper(GrowthcraftCellarBlocks.blockCorkLeaves.getBlock(), (new StateMap.Builder().ignore(BlockCorkLeaves.DECAYABLE, BlockCorkLeaves.CHECK_DECAY)).build());
    }
    ///////////
    // Items
    ///////////

    public static void preInitItems() {
        GrowthcraftCellarItems.chievItemDummy = new ItemDefinition(new ItemChievDummy("achievement_dummy"));
        GrowthcraftCellarItems.yeast = new ItemDefinition(new ItemYeast("yeast"));
        GrowthcraftCellarItems.brewKettleLid = new ItemDefinition(new ItemBrewKettleLid("brew_kettle_lid"));
        GrowthcraftCellarItems.barrelTap = new ItemDefinition(new ItemBarrelTap("barrel_tap"));
        GrowthcraftCellarItems.itemCorkBark = new ItemDefinition(new ItemCorkBark("cork_bark"));
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        GrowthcraftCellarItems.chievItemDummy.registerItem(registry);
        GrowthcraftCellarItems.yeast.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarItems.yeast.registerItem(registry);
        GrowthcraftCellarItems.brewKettleLid.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarItems.brewKettleLid.registerItem(registry);
        GrowthcraftCellarItems.barrelTap.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarItems.barrelTap.registerItem(registry);
        GrowthcraftCellarItems.itemCorkBark.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftCellarItems.itemCorkBark.registerItem(registry);
    }

    public static void registerItemRenders() {
        GrowthcraftCellarItems.chievItemDummy.registerRender();
        GrowthcraftCellarItems.yeast.registerRenders(EnumYeast.class);
        GrowthcraftCellarItems.brewKettleLid.registerRender();
        GrowthcraftCellarItems.barrelTap.registerRender();
        GrowthcraftCellarItems.itemCorkBark.registerRender();

    }

    ///////////
    // Effects
    ///////////

    public static void preInitEffects() {
        final EffectRegistry reg = CoreRegistry.instance().getEffectsRegistry();
        reg.register("booze_effect", BoozeEffect.class);
        reg.register("booze_effect_list", BoozeEffect.BoozeEffectList.class);
        reg.register("tipsy", EffectTipsy.class);
    }

    ///////////
    // Heat sources
    ///////////

    public static void preInitHeatSources() {
    }

    public static void registerHeatSources() {
        GrowthcraftCellarApis.userApis.getUserHeatSources().addDefault("minecraft", "fire", UserHeatSourceEntry.newWildcardHeat(1.0f))
                .setComment("Fire!");
        GrowthcraftCellarApis.userApis.getUserHeatSources().addDefault("minecraft", "flowing_lava", UserHeatSourceEntry.newWildcardHeat(0.7f))
                .setComment("We need to register both states of lava, this when its flowing");
        GrowthcraftCellarApis.userApis.getUserHeatSources().addDefault("minecraft", "lava", UserHeatSourceEntry.newWildcardHeat(0.7f))
                .setComment("And when its a still pool.");
        GrowthcraftCellarApis.userApis.getUserHeatSources().addDefault(Blocks.MAGMA.getRegistryName(), UserHeatSourceEntry.newWildcardHeat(0.7f))
                .setComment("Magma Block. Something like lava.");
    }

    ///////////
    // Yeasts
    ///////////

    public static void initYeasts() {
    }

    public static void registerYeasts() {
        OreDictionary.registerOre("materialYeast", GrowthcraftCellarItems.yeast.getItem());
        for (EnumYeast type : EnumYeast.values()) {
            ItemStack yeastItemStack = type.asStack();
            OreDictionary.registerOre(type.toOreName(), yeastItemStack);
            CellarRegistry.instance().yeast().addYeast(yeastItemStack);
        }
    }

    ///////////
    // Potions
    ///////////

    public static void registerPotions(IForgeRegistry<Potion> registry) {
        // TODO: Add some class PotionDefinition like BlockDefinition or ItemDefinition
        GrowthcraftCellarPotions.potionTipsy = new PotionCellar(false, 0, 1, 0).setPotionName("potion.tipsy");
        registerPotion(registry, GrowthcraftCellarPotions.potionTipsy);

        EffectTipsy.achievement = CellarAchievement.GET_DRUNK;
    }

    private static void registerPotion(IForgeRegistry<Potion> registry, Potion potion) {
        potion.setRegistryName(new ResourceLocation(Reference.MODID, potion.getName()));
        registry.register(potion);
    }

    ///////////
    // Recipes
    ///////////

    public static void registerRecipes() {
        registerCraftingRecipes();
    }

    private static void registerCraftingRecipes() {
        // TODO: RECIPE_REGISTER!
/*
        GameRegistry.addRecipe(GrowthcraftCellarBlocks.cultureJar.asStack(1),
                "BAB", "B B", "BBB",
                'A', Blocks.PLANKS,
                'B', Blocks.GLASS_PANE
        );

        GameRegistry.addShapelessRecipe(
        		GrowthcraftCellarBlocks.brewKettle.asStack(1),
                Items.CAULDRON
        );
        
		GameRegistry.addRecipe(new ShapedOreRecipe(GrowthcraftCellarBlocks.fruitPress.asStack(), "ABA", "CCC", "AAA", 'A', "plankWood", 'B', Blocks.PISTON,'C', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(GrowthcraftCellarBlocks.brewKettle.asStack(), "A", 'A', Items.CAULDRON));
		GameRegistry.addRecipe(new ShapedOreRecipe(GrowthcraftCellarBlocks.fermentBarrel.asStack(), "AAA", "BBB", "AAA", 'B', "plankWood", 'A', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(GrowthcraftCellarBlocks.cultureJar.asStack(), "GAG", "G G", "GGG", 'A', "plankWood", 'G', "paneGlass"));
*/
    }

}
