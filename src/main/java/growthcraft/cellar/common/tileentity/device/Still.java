package growthcraft.cellar.common.tileentity.device;

import growthcraft.cellar.common.tileentity.TileEntityCellarDevice;
import growthcraft.cellar.shared.CellarRegistry;
import growthcraft.cellar.shared.processing.distilling.IDistillingRecipe;
import growthcraft.core.shared.fluids.GrowthcraftFluidUtils;
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

    //heat stuff
    public Still setHeatMultiplier(float h) {
        heatComponent.setHeatMultiplier(h);
        return this;
    }

    public float getHeatMultiplier() {
        return heatComponent.getHeatMultiplier();
    }

    public boolean isHeated() {
        return getHeatMultiplier() > 0;
    }

    //processing stuff
    @Override
    protected IDistillingRecipe loadRecipe() {
        return CellarRegistry.instance().distilling().findRecipe(GrowthcraftFluidUtils.removeStackTags(inputFluidSlot.get()), inputInvSlot.get());
    }

    @Override
    public boolean canProcess() {
        IDistillingRecipe recipe = getWorkingRecipe();
        if(recipe == null) return false;
        //Checks for input fluids
        if(inputFluidSlot.get() == null){
            return false;
        }
        if(!inputFluidSlot.hasEnough(recipe.getInputFluidStack())) return false;
        //Checks for input items
        if(!inputInvSlot.hasEnough(recipe.getInputItemStack())) return false;
        //Checks for output fluids
        if(!outputFluidSlot.hasCapacityFor(recipe.getOutputFluidStack())) return false;
        //Checks for output items
        if(!outputInvSlot.hasCapacityFor(recipe.getOutputItemStack())) return false;

        return true;
    }

    @Override
    protected void process(IDistillingRecipe recipe) {
        inputFluidSlot.consume(GrowthcraftFluidUtils.replaceFluidStackTags(recipe.getInputFluidStack(), inputFluidSlot.get()), true);
        outputFluidSlot.fill(recipe.getOutputFluidStack(), true);
        //TODO:consume item
        markForUpdate(true);
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
