package growthcraft.cellar.common.tileentity;

import growthcraft.cellar.common.inventory.ContainerFermentBarrel;
import growthcraft.cellar.common.tileentity.device.FermentBarrel;
import growthcraft.cellar.common.tileentity.fluids.CellarTank;
import growthcraft.cellar.shared.config.GrowthcraftCellarConfig;
import growthcraft.cellar.shared.init.GrowthcraftCellarItems;
import growthcraft.core.shared.fluids.FluidTest;
import growthcraft.core.shared.inventory.GrowthcraftInternalInventory;
import growthcraft.core.shared.inventory.InventoryProcessor;
import growthcraft.core.shared.io.nbt.INBTItemSerializable;
import growthcraft.core.shared.tileentity.device.DeviceBase;
import growthcraft.core.shared.tileentity.event.TileEventHandler;
import growthcraft.core.shared.tileentity.feature.ITileProgressiveDevice;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.io.IOException;

public class TileEntityFermentBarrel extends TileEntityCellarDevice implements IInventory, ITickable, ITileProgressiveDevice, INBTItemSerializable {

    private static final String NBTNAME = "ferment_barrel";

    public enum FermentBarrelDataID {
        TIME,
        TIME_MAX,
        UNKNOWN;

        public static final FermentBarrelDataID[] VALID = new FermentBarrelDataID[]
                {
                        TIME,
                        TIME_MAX
                };

        public static FermentBarrelDataID getByaOrdinal(int ord) {
            if (ord >= 0 && ord <= VALID.length) return VALID[ord];
            return UNKNOWN;
        }
    }

    // Constants
    private static final int[] accessableSlotIds = new int[]{0};
    private final FermentBarrel fermentBarrel = new FermentBarrel(this, 0,  0);

    @Override
    public DeviceBase[] getDevices(){return new DeviceBase[]{fermentBarrel};}

    @Override
    protected FluidTank[] createTanks() {
        return new FluidTank[]{new CellarTank(GrowthcraftCellarConfig.fermentBarrelMaxCap, this)};
    }

    @Override
    public GrowthcraftInternalInventory createInventory() {
        return new GrowthcraftInternalInventory(this, 2);
    }

    @Override
    public String getDefaultInventoryName() {
        return "container.grc.fermentBarrel";
    }

    @Override
    public String getGuiID() {
        return "growthcraft_cellar:ferment_barrel";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerFermentBarrel(playerInventory, this);
    }

    public int getTime() {
        return (int)fermentBarrel.getTime();
    }

    public int getTimeMax() {
        return fermentBarrel.getTimeMax();
    }

    @Override
    public float getDeviceProgress() {
        return fermentBarrel.getProgress();
    }

    @Override
    public int getDeviceProgressScaled(int scale) {
        return fermentBarrel.getProgressScaled(scale);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            fermentBarrel.update();
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return accessableSlotIds;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemstack) {
        return index == 0;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, EnumFacing side) {
        return InventoryProcessor.instance().canInsertItem(this, stack, slot);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, EnumFacing side) {
        return InventoryProcessor.instance().canExtractItem(this, stack, slot);
    }

    @Override
    protected void readTanksFromNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("Tank")) {
            getFluidTank(0).readFromNBT(nbt.getCompoundTag("Tank"));
        } else {
            super.readTanksFromNBT(nbt);
        }
    }

    @Override
    public void readFromNBTForItem(NBTTagCompound nbt) {
        super.readFromNBTForItem(nbt);
        fermentBarrel.readFromNBT(nbt, NBTNAME);
    }

    @TileEventHandler(event = TileEventHandler.EventType.NBT_READ)
    public void readFromNBT_FermentBarrel(NBTTagCompound nbt) {
        fermentBarrel.readFromNBT(nbt, NBTNAME);
    }

    @Override
    public void writeToNBTForItem(NBTTagCompound nbt) {
        super.writeToNBTForItem(nbt);
        fermentBarrel.writeToNBT(nbt, NBTNAME);
    }

    @TileEventHandler(event = TileEventHandler.EventType.NBT_WRITE)
    public void writeToNBT_FermentBarrel(NBTTagCompound nbt) {
        fermentBarrel.writeToNBT(nbt, NBTNAME);
    }

    @Override
    public void receiveGUINetworkData(int id, int v) {
        super.receiveGUINetworkData(id, v);
        switch (FermentBarrelDataID.getByaOrdinal(id)) {
            case TIME:
                fermentBarrel.setTime(v);
                break;
            case TIME_MAX:
                fermentBarrel.setTimeMax(v);
                break;
            default:
                // should warn about invalid Data ID
                break;
        }
    }

    @Override
    public void sendGUINetworkData(Container container, IContainerListener iCrafting) {
        super.sendGUINetworkData(container, iCrafting);
        iCrafting.sendWindowProperty(container, FermentBarrelDataID.TIME.ordinal(), (int)fermentBarrel.getTime());
        iCrafting.sendWindowProperty(container, FermentBarrelDataID.TIME_MAX.ordinal(), fermentBarrel.getTimeMax());    // Not fermentBarrel.getTimeMaxDefault() !
    }

    @TileEventHandler(event = TileEventHandler.EventType.NETWORK_READ)
    public boolean readFromStream_FermentBarrel(ByteBuf stream) throws IOException {
        fermentBarrel.readFromStream(stream);
        return false;
    }

    @TileEventHandler(event = TileEventHandler.EventType.NETWORK_WRITE)
    public boolean writeToStream_FermentBarrel(ByteBuf stream) throws IOException {
        fermentBarrel.writeToStream(stream);
        return false;
    }

    // Fluid Stuff

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        final FluidStack fluidStack = getFluidStack(0);
        if (fluidStack == null || fluidStack.getFluid() == null) return true;
        return FluidTest.fluidMatches(fluidStack, fluid);
    }

    @Override
    protected int doFill(EnumFacing from, FluidStack resource, boolean shouldFill) {
        return fillFluidTank(0, resource, shouldFill);
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return FluidTest.fluidMatches(getFluidStack(0), fluid);
    }

    @Override
    protected FluidStack doDrain(EnumFacing from, int maxDrain, boolean shouldDrain) {
        return drainFluidTank(0, maxDrain, shouldDrain);
    }

    @Override
    protected FluidStack doDrain(EnumFacing from, FluidStack resource, boolean shouldDrain) {
        if (resource == null || !resource.isFluidEqual(getFluidStack(0))) {
            return null;
        }
        return doDrain(from, resource.amount, shouldDrain);
    }


    @Override
    public void onInventoryChanged(IInventory inv, int index) {
        super.onInventoryChanged(inv, index);
        if (index == 1) {
            // Changing tap has a visual feedback
            this.markDirtyAndUpdate(true);
        }
    }

    public boolean hasTap() {
        return GrowthcraftCellarItems.barrelTap.equals(getStackInSlot(1).getItem());
    }
}