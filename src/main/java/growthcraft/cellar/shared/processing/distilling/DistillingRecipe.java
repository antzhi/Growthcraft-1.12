package growthcraft.cellar.shared.processing.distilling;

import growthcraft.core.shared.fluids.FluidTest;
import growthcraft.core.shared.item.ItemTest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DistillingRecipe extends DistillingFallbackRecipe {

    public DistillingRecipe(@Nullable FluidStack pInputFluid, @Nonnull ItemStack pInputItem, @Nullable FluidStack pOutputFluid, @Nullable ItemStack pOutputItem, float heat,int pTime) {
        super(pInputFluid, pInputItem, pOutputFluid, pOutputItem, heat, pTime);
    }

    @Override
    public boolean matchesRecipe(FluidStack fluidStack, ItemStack itemStack) {
        return FluidTest.fluidMatches(fluidStack, this.getInputFluidStack()) && ItemTest.itemMatches(itemStack, this.getInputItemStack());
    }
}
