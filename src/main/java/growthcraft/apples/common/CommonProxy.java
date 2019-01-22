package growthcraft.apples.common;

import growthcraft.apples.common.compat.dynamictrees.DynamicTreesCompat;
import growthcraft.core.shared.compat.Compat;

public class CommonProxy {
	// REVISE_TEAM

    public void init() {
    }

    public void preInit() {
        registerTileEntities();
        if( Compat.isModAvailable_DynamicTrees() )
        	DynamicTreesCompat.onPreInit();
    }

	public void postInit() {
		
	}
	
    public void registerTileEntities() {
    }

	public void postRegisterItems() {
	}
	
	public void registerStateMappers() {
	}

}