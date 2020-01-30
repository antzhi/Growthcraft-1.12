package growthcraft.distillery.client;

import growthcraft.distillery.common.CommonProxy;
import growthcraft.grapes.common.Init;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        super.init();
        Init.registerItemColorHandlers();
    }
}
