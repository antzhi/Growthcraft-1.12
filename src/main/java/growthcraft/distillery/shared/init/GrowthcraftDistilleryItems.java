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
import growthcraft.distillery.GrowthcraftDistillery;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class GrowthcraftDistilleryItems {
    private GrowthcraftDistilleryItems() {
    }

    public static ItemDefinition bagasse;

    public static ItemTypeDefinition<ItemBoozeBottle> sugarCaneWine;

    public enum CaneWineTypes implements IObjectBooze, IStringSerializable, IItemStackFactory, IObjectVariant {
        CANE_JUICE(0, "cane_juice"),
        CANE_WINE_FERMENTED(1, "cane_wine_fermented"),
        CANE_WINE_POTENT(2, "cane_wine_potent"),
        CANE_WINE_EXTENDED(3, "cane_wine_extended"),
        CANE_WINE_ETHEREAL(4, "cane_wine_ethereal"),
        CANE_WINE_INTOXICATED(5, "cane_wine_intoxicated"),
        CANE_WINE_POISONED(6, "cane_wine_poisoned");

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
}
