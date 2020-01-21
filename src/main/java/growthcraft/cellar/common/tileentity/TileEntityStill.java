package growthcraft.cellar.common.tileentity;

import growthcraft.core.shared.tileentity.device.DeviceBase;
import growthcraft.core.shared.tileentity.feature.ITileHeatedDevice;
import growthcraft.core.shared.tileentity.feature.ITileProgressiveDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ITickable;

public class TileEntityStill extends TileEntityCellarDevice implements ITickable, ITileHeatedDevice, ITileProgressiveDevice {



    @Override
    public DeviceBase[] getDevices() {
        return new DeviceBase[0];
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return null;
    }

    @Override
    public String getGuiID() {
        return null;
    }



    @Override
    public boolean isHeated() {
        return false;
    }

    @Override
    public float getHeatMultiplier() {
        return 0;
    }

    @Override
    public int getHeatScaled(int scale) {
        return 0;
    }

    @Override
    public float getDeviceProgress() {
        return 0;
    }

    @Override
    public int getDeviceProgressScaled(int scale) {
        return 0;
    }

    @Override
    public void update() {

    }
}
