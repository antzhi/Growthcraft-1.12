package growthcraft.cellar.common.tileentity.device;

import growthcraft.cellar.common.tileentity.TileEntityCellarDevice;
import growthcraft.cellar.shared.processing.distilling.IDistillingRecipe;
import growthcraft.core.shared.tileentity.component.TileHeatingComponent;
import growthcraft.core.shared.tileentity.device.DeviceFluidSlot;
import growthcraft.core.shared.tileentity.device.DeviceInventorySlot;
import growthcraft.core.shared.tileentity.device.DeviceProgressive;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class Still extends DeviceProgressive<IDistillingRecipe> {

    private DeviceInventorySlot inputInvSlot;
    private DeviceInventorySlot outputInvSlot;
    private DeviceFluidSlot inputFluidSlot;
    private DeviceFluidSlot outputFluidSlot;
    private TileHeatingComponent heatComponent;

    public Still(TileEntityCellarDevice te, int inputInvSlotId, int outputSlotId, int inputFluidSlotId, int outputFluidSlotId) {
        super(te);
        this.inputInvSlot = new DeviceInventorySlot(te, inputInvSlotId);
        this.outputInvSlot = new DeviceInventorySlot(te, outputSlotId);
        this.inputFluidSlot = new DeviceFluidSlot(te, inputFluidSlotId);
        this.outputFluidSlot = new DeviceFluidSlot(te, outputFluidSlotId);
        this.heatComponent = new TileHeatingComponent(te, 0.5f);
    }

    public Still setHeatMultiplier(float h) {
        heatComponent.setHeatMultiplier(h);
        return this;
    }

    @Override
    protected IDistillingRecipe loadRecipe() {
        //return CellarRegistry.instance().brewing().findRecipe(GrowthcraftFluidUtils.removeStackTags(inputFluidSlot.get()), brewingSlot.get(), hasLid);
        return null;
    }

    @Override
    public boolean canProcess() {
        return false;
    }

    @Override
    protected void process(IDistillingRecipe recipe) {

    }
    @Override
    public void update() {
        heatComponent.update();
        super.update();
    }

    //Data stuff:

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        heatComponent.readFromNBT(data, "heat_component");
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        heatComponent.writeToNBT(data, "heat_component");
    }

    @Override
    public boolean readFromStream(ByteBuf buf) {
        super.readFromStream(buf);
        heatComponent.readFromStream(buf);
        return false;
    }

    @Override
    public boolean writeToStream(ByteBuf buf) {
        super.writeToStream(buf);
        heatComponent.writeToStream(buf);
        return false;
    }
}
