package growthcraft.cellar.shared.config;

import growthcraft.cellar.shared.Reference;
import growthcraft.core.shared.GrowthcraftLogger;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

import java.io.File;

public class GrowthcraftCellarConfig {
    // OPEN

    public static Configuration configuration;

    // TODO: Bind them!
    public static final boolean enableDiscardButton = true;
    public static boolean boozeEffectsEnabled = true;
    public static boolean boozeIsWater = false;
    public static int fermentTime = 24000;
    public static int brewKettleMaxCap = 1000;
    public static boolean brewKettleFillsWithRain = true;
    public static int brewKettleRainFillPerUnit = 10;
    public static boolean dropItemsInBrewKettle = true;
    public static boolean setFireToFallenLivingEntities = true;
    public static boolean fermentBarrelUseCachedRecipe = true;
    public static int fermentBarrelMaxCap = 3000;
    public static int cultureJarTimeMax = 1200;
    public static int cultureJarConsumption = 1000 / 4;
    public static int cultureJarMaxCap = 1000;
    public static int fruitPressMaxCap = 1000;
    public static int stillMaxCap = 10000;

    // Configurable settings.
    public static boolean allowFallbackRecipes = true;

    // Cork Tree Settings
    public static int corkBarkGrowthChance = 50;

    /* CATEGORIES */
    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_PRODUCTION = "production";
    private static final String CATEGORY_CORKTREE = "cork_tree";

    public static void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        configuration = new Configuration(new File(directory.getPath(), "growthcraft/growthcraft-cellar.cfg"));
        readConfig();
    }

    public static void readConfig() {
        try {
            configuration.load();
            initGeneralConfig();
        } catch (Exception e) {
            GrowthcraftLogger.getLogger(Reference.MODID).log(Level.ERROR, "Unable to load configuration files for Growthcraft Cellar!", e);
        } finally {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    private static void initGeneralConfig() {
        // OPEN_ADHOC

        // Production Category
        allowFallbackRecipes = configuration.getBoolean(
                "allowFallbackRecipes",
                CATEGORY_PRODUCTION, true,
                "If true then fallback recipes are activated. E.g. booze spoiling when using invalid ingredient.");

        // Cork Tree Category Settings
        corkBarkGrowthChance = configuration.getInt(
                "corkBarkGrowthChance",
                CATEGORY_CORKTREE,
                corkBarkGrowthChance,
                0,
                100,
                "Chance per ticket that stripped cork log will regrow cork bark."
        );

    }
}
