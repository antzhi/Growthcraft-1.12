package growthcraft.cellar.shared.processing.distilling;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import javax.annotation.Nullable;

public class DistillingFallbackRecipe implements IDistillingRecipe {

    private FluidStack inputFluidStack;
    private FluidStack outputFluidStack;
    private ItemStack inputItemStack;
    private ItemStack outputItemStack;
    private int time;


    public DistillingFallbackRecipe(@Nullable FluidStack pInputFluid, @Nullable ItemStack pInputItem ,@Nullable FluidStack pOutputFluid, @Nullable ItemStack pOutputItem, float heat, int pTime) {
        this.inputItemStack = pInputItem;
        this.inputFluidStack = pInputFluid;
        this.outputFluidStack = pOutputFluid;
        this.outputItemStack = pOutputItem;
        this.time = pTime;
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }


    public ItemStack getInputItemStack() {
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
        return this.time;
    }
}
