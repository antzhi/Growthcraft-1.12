package growthcraft.apples.client;

import growthcraft.apples.common.CommonProxy;
import growthcraft.apples.common.Init;
import growthcraft.apples.common.compat.dynamictrees.DynamicTreesInit;
import growthcraft.apples.shared.init.GrowthcraftApplesBlocks;
import growthcraft.apples.shared.init.GrowthcraftApplesItems;
import growthcraft.core.shared.compat.Compat;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
    	super.preInit();
    }

	@Override
	public void init() {
		super.init();
		Init.registerBlockColorHandlers();
		Init.registerItemColorHandlers();
        if( Compat.isModAvailable_DynamicTrees() )
        	DynamicTreesInit.onInitColorHandler();
        registerSpecialRenders();
	}
	
	@Override
    public void postRegisterItems() {
    	super.postRegisterItems();
    	registerModelBakeryVariants();
    }
	
    public void registerModelBakeryVariants() {
    	Init.registerItemVariants();
    }

    public void registerSpecialRenders() {
    }
    
    @Override
    public void registerStateMappers() {
    	Init.setCustomBlockStateMappers();
        if( Compat.isModAvailable_DynamicTrees() )
        	DynamicTreesInit.onSetStateMappers();
    }

}
