package growthcraft.distillery.common.items;

import growthcraft.distillery.shared.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class Bagasse extends Item {
    public Bagasse(String unlocalizedName){
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 100;
    }
}
