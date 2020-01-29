package growthcraft.distillery.shared.init;

import growthcraft.apples.shared.init.GrowthcraftApplesBlocks;
import growthcraft.apples.shared.init.GrowthcraftApplesFluids;
import growthcraft.apples.shared.init.GrowthcraftApplesItems;
import growthcraft.cellar.shared.definition.BlockBoozeDefinition;
import growthcraft.cellar.shared.definition.BoozeDefinition;
import growthcraft.cellar.shared.definition.IObjectBooze;
import growthcraft.cellar.shared.item.ItemBoozeBottle;
import growthcraft.core.shared.definition.IItemStackFactory;
import growthcraft.core.shared.definition.IObjectVariant;
import growthcraft.core.shared.definition.ItemDefinition;
import growthcraft.core.shared.definition.ItemTypeDefinition;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class GrowthcraftDistilleryItems {
    private GrowthcraftDistilleryItems() {
    }

    public static ItemDefinition bagasse;

    public static ItemTypeDefinition<ItemBoozeBottle> sugarCaneWine;
    public static ItemTypeDefinition<ItemBoozeBottle> fruitWine;
    public static ItemTypeDefinition<ItemBoozeBottle> cactusWine;

    public enum CaneWineTypes implements IObjectBooze, IStringSerializable, IItemStackFactory, IObjectVariant {
        CANE_JUICE(0, "cane_juice"),
        CANE_WINE_FERMENTED(1, "cane_wine_fermented"),
        CANE_WINE_POTENT(2, "cane_wine_potent"),
        CANE_WINE_EXTENDED(3, "cane_wine_extended");

        private int ID;
        private String NAME;

        CaneWineTypes(int id, String name) {
            this.ID = id;
            this.NAME = name;
        }

        @Override
        public String toString() {
            return getName();
        }

        @Override
        public String getName() {
            return this.NAME;
        }

        @Override
        public int getVariantID() {
            return this.ID;
        }

        @Override
        public ItemStack asStack(int amount) {
            return GrowthcraftDistilleryItems.sugarCaneWine.asStack(amount, getVariantID());
        }

        @Override
        public ItemStack asStack() {
            return asStack(1);
        }

        @Override
        public BoozeDefinition getFluidDefinition() {
            return GrowthcraftDistilleryFluids.sugarCaneBooze[ordinal()];
        }

        @Override
        public BlockBoozeDefinition getBoozeBlockDefinition() {
            return GrowthcraftDistilleryBlocks.caneWineFluidBlocks[ordinal()];
        }
    }

    public enum FruitWineTypes implements IObjectBooze, IStringSerializable, IItemStackFactory, IObjectVariant {
        FRUIT_JUICE(0, "fruit_juice"),
        FRUIT_WINE_FERMENTED(1, "fruit_wine_fermented"),
        FRUIT_WINE_POTENT(2, "fruit_wine_potent"),
        FRUIT_WINE_EXTENDED(3, "fruit_wine_extended");

        private int ID;
        private String NAME;

        FruitWineTypes(int id, String name) {
            this.ID = id;
            this.NAME = name;
        }

        @Override
        public String toString() {
            return getName();
        }

        @Override
        public String getName() {
            return this.NAME;
        }

        @Override
        public int getVariantID() {
            return this.ID;
        }

        @Override
        public ItemStack asStack(int amount) {
            return GrowthcraftDistilleryItems.fruitWine.asStack(amount, getVariantID());
        }

        @Override
        public ItemStack asStack() {
            return asStack(1);
        }

        @Override
        public BoozeDefinition getFluidDefinition() {
            return GrowthcraftDistilleryFluids.fruitBooze[ordinal()];
        }

        @Override
        public BlockBoozeDefinition getBoozeBlockDefinition() {
            return GrowthcraftDistilleryBlocks.fruitWineFluidBlocks[ordinal()];
        }
    }

    public enum CactusWineTypes implements IObjectBooze, IStringSerializable, IItemStackFactory, IObjectVariant {
        CACTUS_JUICE(0, "cactus_juice"),
        CACTUS_WINE_FERMENTED(1, "cactus_wine_fermented"),
        CACTUS_WINE_POTENT(2, "cactus_wine_potent"),
        CACTUS_WINE_EXTENDED(3, "cactus_wine_extended");

        private int ID;
        private String NAME;

        CactusWineTypes(int id, String name) {
            this.ID = id;
            this.NAME = name;
        }

        @Override
        public String toString() {
            return getName();
        }

        @Override
        public String getName() {
            return this.NAME;
        }

        @Override
        public int getVariantID() {
            return this.ID;
        }

        @Override
        public ItemStack asStack(int amount) {
            return GrowthcraftDistilleryItems.cactusWine.asStack(amount, getVariantID());
        }

        @Override
        public ItemStack asStack() {
            return asStack(1);
        }

        @Override
        public BoozeDefinition getFluidDefinition() {
            return GrowthcraftDistilleryFluids.cactusBooze[ordinal()];
        }

        @Override
        public BlockBoozeDefinition getBoozeBlockDefinition() {
            return GrowthcraftDistilleryBlocks.cactusWineFluidBlocks[ordinal()];
        }
    }
}
