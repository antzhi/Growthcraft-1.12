package growthcraft.apples.common.compat.dynamictrees.systems.featuregen;

import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFruit;

import growthcraft.apples.common.block.BlockApple;

public class FeatureGenAppleFruit extends FeatureGenFruit {

	public FeatureGenAppleFruit(BlockApple fruit) {
		super(fruit.getDefaultState().withProperty(BlockApple.AGE, BlockApple.MIN_AGE),
				fruit.getDefaultState().withProperty(BlockApple.AGE, BlockApple.MAX_AGE) );
	}

}
