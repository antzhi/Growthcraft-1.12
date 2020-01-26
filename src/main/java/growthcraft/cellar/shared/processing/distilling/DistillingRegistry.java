package growthcraft.cellar.shared.processing.distilling;

import growthcraft.cellar.shared.Reference;
import growthcraft.core.shared.GrowthcraftLogger;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DistillingRegistry {
    private List<IDistillingRecipe> recipes = new ArrayList<>();

    public void addRecipe(@Nonnull IDistillingRecipe recipe) {
        recipes.add(recipe);
        GrowthcraftLogger.getLogger(Reference.MODID).debug("Adding new distilling Recipe, {%s}.", recipe);
    }

    public void addRecipe(@Nullable FluidStack fluidStack, @Nullable ItemStack itemStack,@Nullable FluidStack oFluidStack, @Nullable ItemStack oItemStack, float requiredHeat, int time) {
        addRecipe(new DistillingRecipe(fluidStack, itemStack, oFluidStack, oItemStack, requiredHeat, time));
    }

    public IDistillingRecipe findRecipe(@Nullable FluidStack fluid, @Nullable ItemStack itemStack) {
        for (IDistillingRecipe recipe : recipes) {
            if (recipe.matchesRecipe(fluid, itemStack)) return recipe;
        }
        return null;
    }
}
