package growthcraft.cellar.shared.processing.distilling;

import growthcraft.cellar.shared.processing.common.IProcessingRecipeBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public interface IDistillingRecipe extends IProcessingRecipeBase {

    FluidStack getInputFluidStack();

    ItemStack getInputItemStack();

    FluidStack getOutputFluidStack();

    ItemStack getOutputItemStack();

    boolean matchesRecipe(FluidStack fluidStack, ItemStack itemStack);

    boolean matchesIngredient(FluidStack fluidStack);

    boolean matchesIngredient(ItemStack stack);

    boolean isItemIngredient(@Nullable ItemStack stack);
}
