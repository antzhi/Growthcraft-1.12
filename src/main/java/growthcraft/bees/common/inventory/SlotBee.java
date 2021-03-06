package growthcraft.bees.common.inventory;

import growthcraft.bees.common.lib.config.BeesRegistry;
import growthcraft.core.shared.inventory.slot.SlotInput;
import growthcraft.core.shared.item.ItemUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotBee extends SlotInput {
    final ContainerBeeBox container;

    public SlotBee(ContainerBeeBox cont, IInventory inv, int x, int y, int z) {
        super(inv, x, y, z);
        this.container = cont;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return !ItemUtils.isEmpty(stack) ? BeesRegistry.instance().isItemBee(stack) : false;
    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }
}
