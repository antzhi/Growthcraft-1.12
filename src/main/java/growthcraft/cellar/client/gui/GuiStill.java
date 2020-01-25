package growthcraft.cellar.client.gui;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.common.inventory.ContainerStill;
import growthcraft.cellar.common.lib.network.PacketClearTankButtonWByte;
import growthcraft.cellar.common.tileentity.TileEntityStill;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.config.GrowthcraftCellarConfig;
import growthcraft.core.shared.client.gui.widget.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiStill extends GuiCellar<ContainerStill, TileEntityStill>{

    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MODID, "textures/guis/gui_still.png");
    //TODO:Implements buttons
    private GuiButtonDiscard button0;
    private GuiButtonDiscard button1;
    //private GuiButtonSwitch button2;

    public GuiStill(InventoryPlayer inv, TileEntityStill still) {
        super(GUI_TEXTURE, new ContainerStill(inv, still), still);
    }

    @Override
    public void initGui() {
        super.initGui();
        widgets.add(new WidgetHeatIcon(widgets, 67, 53, 14, 14).setTextureRect(176, 28, 14, 14));
        widgets.add(new WidgetFluidTank(widgets, 0, 46, 17, 16, 52));
        widgets.add(new WidgetFluidTank(widgets, 1, 114, 17, 16, 52));
        widgets.add(new WidgetDeviceProgressIcon(widgets, 98, 30, 9, 28)
                .setProgressDirection(WidgetDeviceProgressIcon.ProgressDirection.TOP_TO_BOTTOM)
                .setTextureRect(176, 0, 9, 28));

        if (GrowthcraftCellarConfig.enableDiscardButton) {
            this.button0 = new GuiButtonDiscard(guiResource, 1, guiLeft + 52, guiTop + 54);
            button0.enabled = false;

            this.button1 = new GuiButtonDiscard(guiResource, 1, guiLeft + 106, guiTop + 54);
            button1.enabled = false;
        }

        //this.button2 = new GuiButtonSwitch(guiResource, 1, this.guiLeft + 133, this.guiTop + 37);
        //this.button2.enabled = false;

        if (button0 != null) this.buttonList.add(this.button0);
        if (button1 != null) this.buttonList.add(this.button1);
        //buttonList.add(this.button2);

        addTooltipIndex("fluidtank.input", 25, 14, 16, 52);
        addTooltipIndex("fluidtank.output", 133, 14, 16, 52);
        if (button0 != null) addTooltipIndex("discard.fluidtank.input", 52, 54, 16, 16);
        if (button1 != null) addTooltipIndex("discard.fluidtank.output", 106, 54, 16, 16);
        //addTooltipIndex("switch.fluidtanks", 133, 37, 16, 16);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (button0 != null) {
            button0.enabled = tileEntity.isFluidTankFilled(0);
        }

        if (button1 != null) {
            button1.enabled = tileEntity.isFluidTankFilled(1);
        }

    }

    protected void actionPerformed(GuiButton button) {
        if (button0 != null && button == button0) {
            GrowthcraftCellar.packetPipeline.sendToServer(new PacketClearTankButtonWByte(tileEntity.getPos(), (byte) 0));
        } else if (button1 != null && button == button1) {
            GrowthcraftCellar.packetPipeline.sendToServer(new PacketClearTankButtonWByte(tileEntity.getPos(), (byte) 1));
        }
    }

    @Override
    public void addTooltips(String handle, List<String> tooltip) {
        switch (handle) {
            case "fluidtank.input":
                addFluidTooltips(tileEntity.getFluidStack(0), tooltip);
                break;
            case "fluidtank.output":
                addFluidTooltips(tileEntity.getFluidStack(1), tooltip);
                break;
            case "discard.fluidtank.input":
                tooltip.add(I18n.format("gui.grc.discard"));
                break;
            case "discard.fluidtank.output":
                tooltip.add(I18n.format("gui.grc.discard"));
                break;
            case "switch.fluidtanks":
                tooltip.add(I18n.format("gui.grc.switch"));
                break;
            default:
                break;
        }
    }
}
