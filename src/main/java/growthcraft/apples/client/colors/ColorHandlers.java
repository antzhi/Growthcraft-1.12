package growthcraft.apples.client.colors;

import growthcraft.apples.common.block.BlockAppleLeaves;
import growthcraft.core.shared.utils.ColorUtils;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorHandlers {
	private ColorHandlers() {}
	
	public static IBlockColor createAppleLeavesBC() {
		return (state, worldIn, pos, tintindex) -> {
			float[] color = ColorUtils.getFloat4ARGB(BlockAppleLeaves.LEAVES_COLOR);
			if( worldIn != null && pos != null ) {
				float[] baseColor = ColorUtils.getFloat4ARGB(ColorizerFoliage.getFoliageColorBasic());
				float[] curColor = ColorUtils.getFloat4ARGB(BiomeColorHelper.getFoliageColorAtPos(worldIn, pos));
				
				float[] colorRatio = new float[3];
				for( int i = 1; i < 3; i ++ )
					colorRatio[i - 1] = curColor[i] / baseColor[i]; // (curColor[i] - baseColor[i]) / baseColor[i] + 1.0f;
				
				for( int i = 1; i < 3; i ++ )
					color[i] *= colorRatio[i - 1];
			}
			
			return ColorUtils.getIntARGB(color);
		};
	}
}
