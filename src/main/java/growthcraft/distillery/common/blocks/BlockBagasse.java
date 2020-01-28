package growthcraft.distillery.common.blocks;

import growthcraft.distillery.shared.Reference;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockBagasse extends BlockRotatedPillar {
    public BlockBagasse(String unlocalizedName) {
        super(Material.LEAVES, MapColor.GRASS);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setUnlocalizedName(unlocalizedName);
        this.setSoundType(SoundType.PLANT);
    }
}
