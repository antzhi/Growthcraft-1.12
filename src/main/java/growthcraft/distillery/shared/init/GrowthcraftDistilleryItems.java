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

    public static ItemTypeDefinition<ItemBoozeBottle> spirit;

    public static ItemTypeDefinition<ItemBoozeBottle> sugarCaneWine;
    public static ItemTypeDefinition<ItemBoozeBottle> fruitWine;
    public static ItemTypeDefinition<ItemBoozeBottle> cactusWine;

    public enum SpiritTypes implements IObjectBooze, IStringSerializable, IItemStackFactory, IObjectVariant {
        CALVADOS(0, "calvados",0xffe468),
        CALVADOS_AGED(1, "calvados_aged",0xab4305),
        RUM(2, "rum",0xffffff),
        RUM_AGED(3, "rum_aged",0xab4305),
        TEQUILA(4, "tequila",0xffffff),
        TEQUILA_AGED(5, "tequila_aged",0xab4305),
        SCHNAPPS(6, "schnapps",0xffffff),
        SCHNAPPS_AGED(7, "schnapps_aged",0xab4305),
        BRANDY(8, "brandy",0xffffff),
        BRANDY_AGED(9, "brandy_aged",0xab4305),
        VODKA(10, "vodka",0xffffff),
        VODKA_AGED(11, "vodka_aged",0xab4305);

        private int ID;
        private String NAME;
        private int COLOR;


        SpiritTypes(int id, String name, int color) {
            this.ID = id;
            this.NAME = name;
            this.COLOR = color;
        }

        public int getColor(){return this.COLOR;}

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
            return GrowthcraftDistilleryItems.spirit.asStack(amount, getVariantID());
        }

        @Override
        public ItemStack asStack() {
            return asStack(1);
        }

        @Override
        public BoozeDefinition getFluidDefinition() {
            return GrowthcraftDistilleryFluids.spiritBooze[ordinal()];
        }

        @Override
        public BlockBoozeDefinition getBoozeBlockDefinition() {
            return GrowthcraftDistilleryBlocks.spiritFluidBlocks[ordinal()];
        }
    }

    public enum CaneWineTypes implements IObjectBooze, IStringSerializable, IItemStackFactory, IObjectVariant {
        CANE_JUICE(0, "cane_juice",0xb7ff8b,1100),
        CANE_WINE_FERMENTED(1, "cane_wine_fermented",0xa96537,1100);

        private int ID;
        private String NAME;
        private int COLOR;
        private int DENSITY;

        CaneWineTypes(int id, String name,int color, int density) {
            this.ID = id;
            this.NAME = name;
            this.COLOR = color;
            this.DENSITY = density;
        }

        @Override
        public String toString() {
            return getName();
        }

        @Override
        public String getName() {
            return this.NAME;
        }

        public int getColor(){return this.COLOR;}
        public int getDensity(){return this.DENSITY;}

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
        FRUIT_WINE_FERMENTED(1, "fruit_wine_fermented");

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
        CACTUS_WINE_FERMENTED(1, "cactus_wine_fermented");
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
