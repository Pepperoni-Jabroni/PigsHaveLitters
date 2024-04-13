package pepjebs.pigs_have_litters;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import pepjebs.pigs_have_litters.config.PigsHaveLittersConfig;

public class PigsHaveLittersMod implements ModInitializer {

    public static PigsHaveLittersConfig CONFIG = null;
    public static String DEFAULT_LITTER_CHANCES = "30:2,50:3,15:4,4:5,1:6";

    @Override
    public void onInitialize() {
        if(FabricLoader.getInstance().isModLoaded("cloth-config")) {
            AutoConfig.register(PigsHaveLittersConfig.class, JanksonConfigSerializer::new);
            CONFIG = AutoConfig.getConfigHolder(PigsHaveLittersConfig.class).getConfig();
        }
    }
}
