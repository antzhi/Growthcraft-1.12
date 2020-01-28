package growthcraft.distillery.common;

import growthcraft.apples.shared.init.GrowthcraftApplesItems;
import growthcraft.cellar.shared.booze.BoozeUtils;
import growthcraft.cellar.shared.config.GrowthcraftCellarConfig;
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
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import static growthcraft.apples.shared.init.GrowthcraftApplesFluids.appleCiderBooze;
import static growthcraft.distillery.shared.init.GrowthcraftDistilleryFluids.sugarCaneBooze;
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
        GrowthcraftDistilleryItems.sugarCaneWine = new ItemTypeDefinition<ItemBoozeBottle>(new ItemBoozeBottle());
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        GrowthcraftDistilleryItems.bagasse.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.bagasse.registerItem(registry);

        GrowthcraftDistilleryItems.sugarCaneWine.registerItem(registry, new ResourceLocation(Reference.MODID, "canewine"));
        GrowthcraftDistilleryItems.sugarCaneWine.getItem().setCreativeTab(tabGrowthcraft);
        GrowthcraftDistilleryItems.sugarCaneWine.getItem().setBoozes(sugarCaneBooze);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemRenders() {
        GrowthcraftDistilleryItems.bagasse.registerRender();
        GrowthcraftDistilleryItems.sugarCaneWine.registerRenders(GrowthcraftDistilleryItems.CaneWineTypes.class);
    }


    //Fluids

    public static void preInitFluids() {
        sugarCaneBooze = new BoozeDefinition[GrowthcraftDistilleryItems.CaneWineTypes.values().length];
        BoozeRegistryHelper.initializeAndRegisterBoozeFluids(sugarCaneBooze, GrowthcraftDistilleryItems.CaneWineTypes.class, "");
        for (GrowthcraftDistilleryItems.CaneWineTypes type:GrowthcraftDistilleryItems.CaneWineTypes.values()){
            sugarCaneBooze[type.ordinal()].getFluid().setColor(0).setDensity(1010);
        }

    }

    public static void initBoozes() {
        BoozeRegistryHelper.initBoozeContainers(sugarCaneBooze, GrowthcraftDistilleryItems.sugarCaneWine, Reference.MODID, "canewine", GrowthcraftDistilleryItems.CaneWineTypes.class);
        registerFermentations();
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidRenders() {
        BoozeRegistryHelper.registerBoozeRenderers(sugarCaneBooze, GrowthcraftDistilleryBlocks.caneWineFluidBlocks);
    }

    private static void registerFermentations() {

        final int fermentTime = GrowthcraftCellarConfig.fermentTime;
        final FluidStack[] fs = new FluidStack[sugarCaneBooze.length];
        for (int i = 0; i < sugarCaneBooze.length; ++i) {
            fs[i] = sugarCaneBooze[i].asFluidStack();
        }
        GrowthcraftCellarApis.boozeBuilderFactory.create(sugarCaneBooze[GrowthcraftDistilleryItems.CaneWineTypes.CANE_JUICE.ordinal()].getFluid())
                .tags(BoozeTag.YOUNG)
                .pressesFrom(
                        new ItemStack(Items.REEDS,1),
                        TickUtils.seconds(2),
                        40,
                       new Residue(new ItemStack(GrowthcraftDistilleryItems.bagasse.getItem()),1)
                );

        GrowthcraftCellarApis.boozeBuilderFactory.create(sugarCaneBooze[GrowthcraftDistilleryItems.CaneWineTypes.CANE_WINE_FERMENTED.ordinal()].getFluid())
                .tags(BoozeTag.FERMENTED)
                .fermentsFrom(fs[GrowthcraftDistilleryItems.CaneWineTypes.CANE_JUICE.ordinal()], new OreItemStacks("yeastBrewers"), fermentTime)
                .fermentsFrom(fs[GrowthcraftDistilleryItems.CaneWineTypes.CANE_JUICE.ordinal()], new ItemStack(Items.NETHER_WART), (int) (fermentTime * 0.66))
                .getEffect()
                .setTipsy(BoozeUtils.alcoholToTipsy(0.045f), TickUtils.seconds(45))
                .addPotionEntry(MobEffects.ABSORPTION, TickUtils.seconds(90), 0);
    }
}
