package growthcraft.distillery.common.fluids;

import growthcraft.bees.shared.Reference;
import growthcraft.core.shared.fluids.GrowthcraftFluid;
import net.minecraft.util.ResourceLocation;

public class FluidCaneJuice extends GrowthcraftFluid {
    public FluidCaneJuice(String unlocalizedName) {
        super(unlocalizedName, new ResourceLocation(Reference.MODID, "blocks/fluids/fluid_Canejuice_still"), new ResourceLocation(Reference.MODID, "blocks/fluids/fluid_canejuice_flow"));
        this.setUnlocalizedName(unlocalizedName);
        this.setColor(0xffac01);
        this.setDensity(1420);
        this.setViscosity(73600);
    }
}
