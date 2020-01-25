package growthcraft.cellar.common.block;

import growthcraft.cellar.common.tileentity.TileEntityStill;
import growthcraft.cellar.shared.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class BlockStill extends BlockRotatableCellarContainer {
    public BlockStill(String unlocalizedName) {
        super(Material.IRON);
        setTileEntityType(TileEntityStill.class);
        this.setHardness(2.0F);
        this.setUnlocalizedName(unlocalizedName);
        //this.setDefaultState(this.getBlockState().getBaseState().withProperty(TYPE_LID, false));
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
    }


    //Render stuff
    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }
}
