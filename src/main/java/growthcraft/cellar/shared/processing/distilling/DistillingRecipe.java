package growthcraft.cellar.shared.processing.distilling;

import growthcraft.core.shared.definition.IMultiFluidStacks;
import growthcraft.core.shared.definition.IMultiItemStacks;
import growthcraft.core.shared.item.ItemTest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class DistillingRecipe implements IDistillingRecipe {

    private IMultiItemStacks inputItemStack;
    private IMultiFluidStacks inputFluidStack;
    private ItemStack outputItemStack;
    private FluidStack outputFluidStack;
    private int time;

    @Override
    public IMultiFluidStacks getInputFluidStack() {
        return inputFluidStack;
    }

    @Override
    public IMultiItemStacks getInputItemStack() {
        return inputItemStack;
    }

    @Override
    public FluidStack getOutputFluidStack() {
        return outputFluidStack;
    }

    @Override
    public ItemStack getOutputItemStack() {
        return outputItemStack;
    }

    @Override
    public boolean matchesRecipe(FluidStack fluidStack, ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean matchesIngredient(FluidStack fluidStack) {
        return false;
    }

    @Override
    public boolean matchesIngredient(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isItemIngredient(@Nullable ItemStack stack) {
        return false;
    }

    @Override
    public int getTime() {
        return time;
    }
}
