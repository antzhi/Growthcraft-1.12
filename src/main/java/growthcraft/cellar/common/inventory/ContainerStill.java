package growthcraft.cellar.common.inventory;

import growthcraft.cellar.common.inventory.slot.SlotFruitPressResidue;
import growthcraft.cellar.common.inventory.slot.SlotInputPressing;
import growthcraft.cellar.common.tileentity.TileEntityStill;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerStill extends CellarContainer {
    public ContainerStill(InventoryPlayer player, TileEntityStill te) {
        super(te);
        // Slot Indexes:
        //0            input
        //1            output
        //2 - 28 (29)  player.inv.backpack
        //29 - 37 (38) player.inv.hotbar

        addSlotToContainer(new SlotInputPressing(te, 0, 53, 33));
        addSlotToContainer(new SlotFruitPressResidue(te, 1, 107, 33));

        bindPlayerInventory(player, 8, 84);
    }
}
