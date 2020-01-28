package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.inventory.ContainerStill;
import growthcraft.cellar.common.tileentity.device.Still;
import growthcraft.cellar.common.tileentity.fluids.CellarTank;
import growthcraft.core.shared.inventory.GrowthcraftInternalInventory;
import growthcraft.core.shared.tileentity.device.DeviceBase;
import growthcraft.core.shared.tileentity.feature.ITileHeatedDevice;
import growthcraft.core.shared.tileentity.feature.ITileProgressiveDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class TileEntityStill extends TileEntityCellarDevice implements ITickable, ITileHeatedDevice, ITileProgressiveDevice {

    private Still still = new Still(this,0,1,0,1);

    public enum StillDataID {
        TIME,
        TIME_MAX,
        HEAT_AMOUNT,
        UNKNOWN;

        protected static final TileEntityStill.StillDataID[] VALUES = new TileEntityStill.StillDataID[]
                {
                        TIME,
                        TIME_MAX,
                        HEAT_AMOUNT
                };

        public static TileEntityStill.StillDataID getByOrdinal(int ord) {
            if (ord >= 0 && ord < VALUES.length) return VALUES[ord];
            return UNKNOWN;
        }
    }

    //heat stuff

    @Override
    public boolean isHeated() {
        return still.isHeated();
    }

    @Override
    public float getHeatMultiplier() {
        return still.getHeatMultiplier();
    }

    @Override
    public int getHeatScaled(int scale) {
        return (int) (MathHelper.clamp(still.getHeatMultiplier(), 0.0f, 1.0f) * scale);
    }

    //Processing stuff
    @Override
    public DeviceBase[] getDevices() {
        return new DeviceBase[]{still};
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

    /**
     * @param id - data id
     * @param v  - value
     */
    @Override
    public void receiveGUINetworkData(int id, int v) {
        super.receiveGUINetworkData(id, v);
        final TileEntityStill.StillDataID dataId = TileEntityStill.StillDataID.getByOrdinal(id);
        switch (dataId) {
            case TIME:
                still.setTime(v);
                break;
            case TIME_MAX:
                still.setTimeMax(v);
                break;
            case HEAT_AMOUNT:
                still.setHeatMultiplier((float) v / (float) 0x7FFF);
                break;
            default:
                break;
        }
    }

    @Override
    public void sendGUINetworkData(Container container, IContainerListener iCrafting) {
        super.sendGUINetworkData(container, iCrafting);
        iCrafting.sendWindowProperty(container, TileEntityStill.StillDataID.TIME.ordinal(), (int) still.getTime());
        iCrafting.sendWindowProperty(container, TileEntityStill.StillDataID.TIME_MAX.ordinal(), (int) still.getTimeMax());
        iCrafting.sendWindowProperty(container, TileEntityStill.StillDataID.HEAT_AMOUNT.ordinal(), (int) (still.getHeatMultiplier() * 0x7FFF));
    }

    //Fluid stuff
    @Override
    protected int doFill(EnumFacing from, FluidStack resource, boolean shouldFill) {
        return fillFluidTank(0, resource, shouldFill);
    }

    @Override
    protected FluidStack doDrain(EnumFacing from, int maxDrain, boolean shouldDrain) {
        return drainFluidTank(1, maxDrain, shouldDrain);
    }

    @Override
    protected FluidStack doDrain(EnumFacing from, FluidStack stack, boolean shouldDrain) {
        if (stack == null || !stack.isFluidEqual(getFluidStack(1))) {
            return null;
        }
        return doDrain(from, stack.amount, shouldDrain);
    }

}
