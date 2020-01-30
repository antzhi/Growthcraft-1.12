package growthcraft.distillery.common;

import growthcraft.apples.shared.init.GrowthcraftApplesFluids;
import growthcraft.apples.shared.init.GrowthcraftApplesItems;
import growthcraft.cellar.shared.CellarRegistry;
import growthcraft.cellar.shared.booze.BoozeUtils;
import growthcraft.cellar.shared.config.GrowthcraftCellarConfig;
import growthcraft.core.shared.client.render.utils.ItemRenderUtils;
import growthcraft.core.shared.item.OreItemStacks;
import growthcraft.distillery.GrowthcraftDistillery;
import growthcraft.distillery.shared.Reference;
import growthcraft.cellar.shared.GrowthcraftCellarApis;
import growthcraft.cellar.shared.booze.BoozeRegistryHelper;
import growthcraft.cellar.shared.booze.BoozeTag;
import growthcraft.cellar.shared.definition.BoozeDefinition;
import growthcraft.cellar.shared.item.ItemBoozeBottle;
import growthcraft.cellar.shared.processing.common.Residue;
import growthcraft.core.shared.GrowthcraftCoreApis;
import growthcraft.core.shared.definition.BlockDefinition;
import growthcraft.core.shared.definition.ItemDefinition;
import growthcraft.core.shared.definition.ItemTypeDefinition;
import growthcraft.core.shared.utils.TickUtils;
import growthcraft.distillery.common.items.Bagasse;
import growthcraft.distillery.common.blocks.BlockBagasse;
import growthcraft.distillery.shared.init.GrowthcraftDistilleryBlocks;
import growthcraft.distillery.shared.init.GrowthcraftDistilleryItems;
import growthcraft.grapes.shared.init.GrowthcraftGrapesFluids;
import growthcraft.grapes.shared.init.GrowthcraftGrapesItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import static growthcraft.distillery.shared.init.GrowthcraftDistilleryFluids.cactusBooze;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryFluids.fruitBooze;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryFluids.sugarCaneBooze;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryFluids.spiritBooze;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryItems.SpiritTypes;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryItems.CaneWineTypes;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryItems.FruitWineTypes;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryItems.CactusWineTypes;

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
        GrowthcraftDistilleryItems.sugarCaneWine = new ItemTypeDefinition<>(new ItemBoozeBottle());
        GrowthcraftDistilleryItems.fruitWine = new ItemTypeDefinition<>(new ItemBoozeBottle());
        GrowthcraftDistilleryItems.cactusWine = new ItemTypeDefinition<>(new ItemBoozeBottle());
        GrowthcraftDistilleryItems.spirit = new ItemTypeDefinition<>(new ItemBoozeBottle());
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        GrowthcraftDistilleryItems.bagasse.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.bagasse.registerItem(registry);

        GrowthcraftDistilleryItems.sugarCaneWine.registerItem(registry, new ResourceLocation(Reference.MODID, "canewine"));
        GrowthcraftDistilleryItems.sugarCaneWine.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.sugarCaneWine.getItem().setBoozes(sugarCaneBooze);

        GrowthcraftDistilleryItems.fruitWine.registerItem(registry, new ResourceLocation(Reference.MODID, "fruitwine"));
        GrowthcraftDistilleryItems.fruitWine.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.fruitWine.getItem().setBoozes(fruitBooze);

        GrowthcraftDistilleryItems.cactusWine.registerItem(registry, new ResourceLocation(Reference.MODID, "cactuswine"));
        GrowthcraftDistilleryItems.cactusWine.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.cactusWine.getItem().setBoozes(cactusBooze);

        GrowthcraftDistilleryItems.spirit.registerItem(registry, new ResourceLocation(Reference.MODID, "spirit"));
        GrowthcraftDistilleryItems.spirit.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.spirit.getItem().setBoozes(spiritBooze);
    }

    public static void registerItemOres() {
        OreDictionary.registerOre("listAllfruit", GrowthcraftGrapesItems.grape.getItem());
    }
    @SideOnly(Side.CLIENT)
    public static void registerItemRenders() {
        GrowthcraftDistilleryItems.bagasse.registerRender();
        GrowthcraftDistilleryItems.sugarCaneWine.registerRenders(GrowthcraftDistilleryItems.CaneWineTypes.class);
        GrowthcraftDistilleryItems.cactusWine.registerRenders(GrowthcraftDistilleryItems.CactusWineTypes.class);
        GrowthcraftDistilleryItems.fruitWine.registerRenders(GrowthcraftDistilleryItems.FruitWineTypes.class);
        GrowthcraftDistilleryItems.spirit.registerRenders(GrowthcraftDistilleryItems.SpiritTypes.class);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemColorHandlers() {
        ItemRenderUtils.registerItemColorHandler(GrowthcraftDistilleryItems.spirit.getItem());
        ItemRenderUtils.registerItemColorHandler(GrowthcraftDistilleryItems.sugarCaneWine.getItem());
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemVariants() {
        GrowthcraftDistilleryItems.sugarCaneWine.registerModelBakeryVariants(GrowthcraftDistilleryItems.CaneWineTypes.class);
    }

    //Fluids

    public static void preInitFluids() {

        spiritBooze = new BoozeDefinition[GrowthcraftDistilleryItems.SpiritTypes.values().length];
        BoozeRegistryHelper.initializeAndRegisterBoozeFluids(spiritBooze, GrowthcraftDistilleryItems.SpiritTypes.class, "");
        for (SpiritTypes type:SpiritTypes.values()){
            spiritBooze[type.ordinal()].getFluid().setColor(type.getColor()).setDensity(900);
        }

        sugarCaneBooze = new BoozeDefinition[GrowthcraftDistilleryItems.CaneWineTypes.values().length];
        BoozeRegistryHelper.initializeAndRegisterBoozeFluids(sugarCaneBooze, GrowthcraftDistilleryItems.CaneWineTypes.class, "");
        for (CaneWineTypes type:CaneWineTypes.values()){
            sugarCaneBooze[type.ordinal()].getFluid().setColor(type.getColor()).setDensity(type.getDensity());
        }

        cactusBooze = new BoozeDefinition[GrowthcraftDistilleryItems.CactusWineTypes.values().length];
        BoozeRegistryHelper.initializeAndRegisterBoozeFluids(cactusBooze, GrowthcraftDistilleryItems.CactusWineTypes.class, "");
        for (CactusWineTypes type:CactusWineTypes.values()){
            cactusBooze[type.ordinal()].getFluid().setColor(0xffcd93).setDensity(1010);
        }

        fruitBooze = new BoozeDefinition[GrowthcraftDistilleryItems.FruitWineTypes.values().length];
        BoozeRegistryHelper.initializeAndRegisterBoozeFluids(fruitBooze, GrowthcraftDistilleryItems.FruitWineTypes.class, "");
        for (FruitWineTypes type:FruitWineTypes.values()){
            fruitBooze[type.ordinal()].getFluid().setColor(0xff8e8e).setDensity(1010);
        }

    }


    public static void initBoozes() {
        BoozeRegistryHelper.initBoozeContainers(sugarCaneBooze, GrowthcraftDistilleryItems.sugarCaneWine, Reference.MODID, "canewine", GrowthcraftDistilleryItems.CaneWineTypes.class);
        BoozeRegistryHelper.initBoozeContainers(fruitBooze, GrowthcraftDistilleryItems.fruitWine, Reference.MODID, "fruitwine", GrowthcraftDistilleryItems.FruitWineTypes.class);
        BoozeRegistryHelper.initBoozeContainers(cactusBooze, GrowthcraftDistilleryItems.cactusWine, Reference.MODID, "cactuswine", GrowthcraftDistilleryItems.CactusWineTypes.class);
        registerFermentations();
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidRenders() {
        BoozeRegistryHelper.registerBoozeRenderers(sugarCaneBooze, GrowthcraftDistilleryBlocks.caneWineFluidBlocks);
    }

    private static void registerFermentations() {

        final int fermentTime = GrowthcraftCellarConfig.fermentTime;

        //distilling recipe
        CellarRegistry.instance().distilling().addRecipe(sugarCaneBooze[CaneWineTypes.CANE_WINE_FERMENTED.ordinal()].asFluidStack(100),
                ItemStack.EMPTY,spiritBooze[SpiritTypes.RUM.ordinal()].asFluidStack(10),ItemStack.EMPTY,1,200);
        CellarRegistry.instance().distilling().addRecipe(fruitBooze[FruitWineTypes.FRUIT_WINE_FERMENTED.ordinal()].asFluidStack(100),
                ItemStack.EMPTY,spiritBooze[SpiritTypes.SCHNAPPS.ordinal()].asFluidStack(10),ItemStack.EMPTY,1,200);
        CellarRegistry.instance().distilling().addRecipe(cactusBooze[CactusWineTypes.CACTUS_WINE_FERMENTED.ordinal()].asFluidStack(100),
                ItemStack.EMPTY,spiritBooze[SpiritTypes.TEQUILA.ordinal()].asFluidStack(10),ItemStack.EMPTY,1,200);
        CellarRegistry.instance().distilling().addRecipe(GrowthcraftApplesFluids.appleCiderBooze[GrowthcraftApplesItems.AppleCiderTypes.APPLE_CIDER_FERMENTED.ordinal()].asFluidStack(100),
                ItemStack.EMPTY,spiritBooze[SpiritTypes.CALVADOS.ordinal()].asFluidStack(10),ItemStack.EMPTY,1,200);
        CellarRegistry.instance().distilling().addRecipe(GrowthcraftGrapesFluids.grapeWineBooze[GrowthcraftGrapesItems.WineTypes.PURPLE_WINE.ordinal()].asFluidStack(100),
                ItemStack.EMPTY,spiritBooze[SpiritTypes.BRANDY.ordinal()].asFluidStack(10),ItemStack.EMPTY,1,200);
        CellarRegistry.instance().distilling().addRecipe(GrowthcraftGrapesFluids.grapeWineBooze[GrowthcraftGrapesItems.WineTypes.RED_WINE.ordinal()].asFluidStack(100),
                ItemStack.EMPTY,spiritBooze[SpiritTypes.BRANDY.ordinal()].asFluidStack(10),ItemStack.EMPTY,1,200);


        for(SpiritTypes type: SpiritTypes.values()){
            GrowthcraftCellarApis.boozeBuilderFactory.create(spiritBooze[type.ordinal()].getFluid())
                    .getEffect().setTipsy(BoozeUtils.alcoholToTipsy(0.45f), TickUtils.seconds(45));
        }

        //Fermentation recipes
        GrowthcraftCellarApis.boozeBuilderFactory.create(sugarCaneBooze[GrowthcraftDistilleryItems.CaneWineTypes.CANE_WINE_FERMENTED.ordinal()].getFluid())
                .tags(BoozeTag.FERMENTED)
                .fermentsFrom(sugarCaneBooze[GrowthcraftDistilleryItems.CaneWineTypes.CANE_JUICE.ordinal()].asFluidStack(), new OreItemStacks("yeastBrewers"), fermentTime)
                .fermentsFrom(sugarCaneBooze[GrowthcraftDistilleryItems.CaneWineTypes.CANE_JUICE.ordinal()].asFluidStack(), new ItemStack(Items.NETHER_WART), (int) (fermentTime * 0.66))
                .getEffect()
                .setTipsy(BoozeUtils.alcoholToTipsy(0.045f), TickUtils.seconds(45))
                .addPotionEntry(MobEffects.ABSORPTION, TickUtils.seconds(90), 0);

        GrowthcraftCellarApis.boozeBuilderFactory.create(fruitBooze[GrowthcraftDistilleryItems.FruitWineTypes.FRUIT_WINE_FERMENTED.ordinal()].getFluid())
                .tags(BoozeTag.FERMENTED)
                .fermentsFrom(fruitBooze[GrowthcraftDistilleryItems.FruitWineTypes.FRUIT_JUICE.ordinal()].asFluidStack(), new OreItemStacks("yeastBrewers"), fermentTime)
                .fermentsFrom(fruitBooze[GrowthcraftDistilleryItems.FruitWineTypes.FRUIT_JUICE.ordinal()].asFluidStack(), new ItemStack(Items.NETHER_WART), (int) (fermentTime * 0.66))
                .getEffect()
                .setTipsy(BoozeUtils.alcoholToTipsy(0.045f), TickUtils.seconds(45))
                .addPotionEntry(MobEffects.ABSORPTION, TickUtils.seconds(90), 0);

        GrowthcraftCellarApis.boozeBuilderFactory.create(cactusBooze[GrowthcraftDistilleryItems.CactusWineTypes.CACTUS_WINE_FERMENTED.ordinal()].getFluid())
                .tags(BoozeTag.FERMENTED)
                .fermentsFrom(cactusBooze[GrowthcraftDistilleryItems.CactusWineTypes.CACTUS_JUICE.ordinal()].asFluidStack(), new OreItemStacks("yeastBrewers"), fermentTime)
                .fermentsFrom(cactusBooze[GrowthcraftDistilleryItems.CactusWineTypes.CACTUS_JUICE.ordinal()].asFluidStack(), new ItemStack(Items.NETHER_WART), (int) (fermentTime * 0.66))
                .getEffect()
                .setTipsy(BoozeUtils.alcoholToTipsy(0.045f), TickUtils.seconds(45))
                .addPotionEntry(MobEffects.ABSORPTION, TickUtils.seconds(90), 0);

        //pressing recipes
        GrowthcraftCellarApis.boozeBuilderFactory.create(sugarCaneBooze[CaneWineTypes.CANE_JUICE.ordinal()].getFluid())
                .tags(BoozeTag.YOUNG)
                .pressesFrom(
                        new ItemStack(Items.REEDS,1),
                        TickUtils.seconds(2),
                        40,
                        new Residue(new ItemStack(GrowthcraftDistilleryItems.bagasse.getItem()),1)
                );

        GrowthcraftCellarApis.boozeBuilderFactory.create(fruitBooze[GrowthcraftDistilleryItems.FruitWineTypes.FRUIT_JUICE.ordinal()].getFluid())
                .tags(BoozeTag.YOUNG)
                .pressesFrom(
                        new OreItemStacks("listAllfruit"),
                        TickUtils.seconds(2),
                        40,
                        Residue.newDefault(0.5f)
                );

        GrowthcraftCellarApis.boozeBuilderFactory.create(cactusBooze[GrowthcraftDistilleryItems.CactusWineTypes.CACTUS_JUICE.ordinal()].getFluid())
                .tags(BoozeTag.YOUNG)
                .pressesFrom(
                        new ItemStack(Blocks.CACTUS),
                        TickUtils.seconds(2),
                        40,
                        new Residue(new ItemStack(Items.DYE,1, 2),1)
                );
    }
}
