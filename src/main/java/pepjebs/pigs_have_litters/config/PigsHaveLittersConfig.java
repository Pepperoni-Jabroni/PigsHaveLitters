package pepjebs.pigs_have_litters.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import pepjebs.pigs_have_litters.PigsHaveLittersMod;

@Config(name = "pigs_have_litters")
public class PigsHaveLittersConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip()
    @Comment("Comma-separated list of Probability:Count - probabilities should sum to 100.")
    public String litterSizingChances = PigsHaveLittersMod.DEFAULT_LITTER_CHANCES;
}