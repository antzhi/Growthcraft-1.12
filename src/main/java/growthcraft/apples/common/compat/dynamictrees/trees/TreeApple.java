package growthcraft.apples.common.compat.dynamictrees.trees;

import java.util.ArrayList;

import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

import growthcraft.apples.client.colors.ColorHandlers;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.shared.init.GrowthcraftApplesBlocks;
import growthcraft.apples.shared.init.GrowthcraftApplesItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

// NOTE: Move a lot of stuff to utils section or to a more generic base class from here if creating new tree families!

public class TreeApple extends TreeFamily {
	public static final int SEQUENCE_NUM = 0;
	
	// TODO: Move to some kind of TreeInfo class, because for now leavesProperties are not known if a species is initialized.
	private ILeavesProperties leavesProperties;
	private BlockDynamicLeaves leaves; 
	
	public class SpeciesAppleStandard extends Species {

		public SpeciesAppleStandard(TreeApple treeFamily) {
			super(treeFamily.getName(), treeFamily);

			setBasicGrowingParameters(0.3f, 12.0f, upProbability, lowestBranchHeight, 1.0f);
			
			envFactor(Type.COLD, 0.50f);
			envFactor(Type.SNOWY, 0.25f);
			envFactor(Type.HOT, 0.50f);
			envFactor(Type.PLAINS, 1.05f);
			envFactor(Type.FOREST, 0.8f);
			
			generateSeed();
			
			setupStandardSeedDropping();
		}
		
		@Override
		public boolean isBiomePerfect(Biome biome) {
			return BiomeDictionary.hasType(biome, Type.PLAINS);
		}
	}
	
	public TreeApple(String name) {
		super(new ResourceLocation(Reference.MODID, name));
		
		IBlockState log = getLogStateForModel();
		int meta = log.getBlock().getMetaFromState(log);
		setPrimitiveLog(log, new ItemStack(log.getBlock(), 1, meta));
		
		addConnectableVanillaLeaves((state) -> state.getBlock() == GrowthcraftApplesBlocks.blockAppleLeaves.getBlock());
	}
	
	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesAppleStandard(this));
	}
	
	@Override
	public ILeavesProperties getCommonLeaves() {
		return leavesProperties != null ? leavesProperties : LeavesProperties.NULLPROPERTIES;
	}
	
	//////////
	// Standard
	//////////

	public Item getSeeds() {
		// TODO: Handle more species here if there are any
		return getCommonSpecies().getSeed();
	}
	
	//////////
	// Initialization (COMMON)
	//////////
	
	private IBlockState getLogStateForModel() {
		return GrowthcraftApplesBlocks.blockAppleLog.getDefaultState();
	}
	
	private IBlockState getLeavesStateForModel() {
		return GrowthcraftApplesBlocks.blockAppleLeaves.getDefaultState();	// TODO: Add more properties, if needed
	}
	
	private Item getLeavesItem() {
		return GrowthcraftApplesItems.itemAppleLeaves.getItem();
	}
	
	public void onPreInit() {
		IBlockState originalLeavesState = getLeavesStateForModel();

		// TODO: Resolve item damage from meta
//		Block originalLeavesBlock = originalLeavesState.getBlock();
//		int meta = originalLeavesBlock.getMetaFromState(originalLeavesState); 
		Item leavesItem = getLeavesItem(); 
		
		leavesProperties = new LeavesProperties(
				originalLeavesState,
				new ItemStack(leavesItem, 1, 0));
		leavesProperties.setTree(this);
		leaves = LeavesPaging.getLeavesBlockForSequence(Reference.MODID, SEQUENCE_NUM, leavesProperties);
		
		registerSpecies(Species.REGISTRY);
		// TODO: Handle more species here, if there exist any
		getCommonSpecies().setLeavesProperties(leavesProperties);
	}
	
	public void onRegisterBlocks(IForgeRegistry<Block> registry) {
		ArrayList<Block> treeBlocks = new ArrayList<>();
		getRegisterableBlocks(treeBlocks);
		treeBlocks.add(leaves);
		registry.registerAll(treeBlocks.toArray(new Block[treeBlocks.size()]));
	}
	
	public void onRegisterBlockItems(IForgeRegistry<Item> registry) {
		ArrayList<Item> treeItems = new ArrayList<>();
		getRegisterableItems(treeItems);
		registry.registerAll(treeItems.toArray(new Item[treeItems.size()]));
	}

	//////////
	// Initialization (CLIENT)
	//////////

	@SideOnly(Side.CLIENT)
	public void onInitColorHandler() {
		ModelHelper.regColorHandler(leaves, ColorHandlers.createAppleLeavesBC());
	}
	
	@SideOnly(Side.CLIENT)
	public void onRegisterModels() {
		ModelHelper.regModel(getDynamicBranch());
		// TODO: Add more species here if there are any
		ModelHelper.regModel(getCommonSpecies().getSeed());
		ModelHelper.regModel(this);
	}
	
	@SideOnly(Side.CLIENT)
	public void onSetStateMappers() {
		ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
	}
}
