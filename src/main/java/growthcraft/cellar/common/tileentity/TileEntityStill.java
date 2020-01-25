package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.inventory.ContainerFruitPress;
import growthcraft.cellar.common.inventory.ContainerStill;
import growthcraft.cellar.common.tileentity.device.Still;
import growthcraft.cellar.common.tileentity.fluids.CellarTank;
import growthcraft.cellar.shared.config.GrowthcraftCellarConfig;
import growthcraft.core.shared.inventory.GrowthcraftInternalInventory;
import growthcraft.core.shared.tileentity.device.DeviceBase;
import growthcraft.core.shared.tileentity.feature.ITileHeatedDevice;
import growthcraft.core.shared.tileentity.feature.ITileProgressiveDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidTank;

public class TileEntityStill extends TileEntityCellarDevice implements ITickable, ITileHeatedDevice, ITileProgressiveDevice {

    private Still still = new Still(this,0,1,0,1);

    //Processing stuff
    @Override
    public DeviceBase[] getDevices() {
        return new DeviceBase[]{still};
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
        return still.getProgress();
    }

    @Override
    public int getDeviceProgressScaled(int scale) {
        return still.getProgressScaled(scale);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            still.update();
        }
    }

    //Inventory stuff
    @Override
    protected FluidTank[] createTanks() {
        //TODO: add config for this
        final int maxCap = 1000;
        return new FluidTank[]{new CellarTank(maxCap, this), new CellarTank(maxCap, this)};
    }

    @Override
    public GrowthcraftInternalInventory createInventory() {
        return new GrowthcraftInternalInventory(this, 2);
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerStill(playerInventory, this);
    }

    //GUI stuff
    @Override
    public String getDefaultInventoryName() {
        return "container.grc.still";
    }

    @Override
    public String getGuiID() {
        return "growthcraft_cellar:still";
    }

    //Automation
    //TODO: item and fluid transfert automation

}
