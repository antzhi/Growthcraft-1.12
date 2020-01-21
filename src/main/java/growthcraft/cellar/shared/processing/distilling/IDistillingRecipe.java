package growthcraft.cellar.shared.processing.distilling;

import growthcraft.cellar.shared.processing.common.IProcessingRecipeBase;
import growthcraft.core.shared.definition.IMultiFluidStacks;
import growthcraft.core.shared.definition.IMultiItemStacks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public interface IDistillingRecipe extends IProcessingRecipeBase {

    IMultiFluidStacks getInputFluidStack();

    IMultiItemStacks getInputItemStack();

    FluidStack getOutputFluidStack();

    ItemStack getOutputItemStack();

    boolean matchesRecipe(FluidStack fluidStack, ItemStack itemStack);

    boolean matchesIngredient(FluidStack fluidStack);

    boolean matchesIngredient(ItemStack stack);

    boolean isItemIngredient(@Nullable ItemStack stack);
}
