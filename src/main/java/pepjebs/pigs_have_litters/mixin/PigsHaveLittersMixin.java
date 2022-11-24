package pepjebs.pigs_have_litters.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(PigEntity.class)
public class PigsHaveLittersMixin extends AnimalEntity {

    @Override
    public void breed(ServerWorld world, AnimalEntity other) {
        super.breed(world, other);
        for (int i = 0; i < getPigletSpawnCount(); i++) {
            PassiveEntity passiveEntity = this.createChild(world, other);
            if (passiveEntity == null) return;
            passiveEntity.setBaby(true);
            passiveEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            world.spawnEntityAndPassengers(passiveEntity);
        }
    }

    @Shadow @Nullable @Override
    public PigEntity createChild(ServerWorld world, PassiveEntity entity) { return null; }

    private int getPigletSpawnCount() {
        // Formatted "Probability:Count,..."
        String litterSizing = "30:1,50:2,15:3,4:4,1:5";
        var confs = litterSizing.split(",");
        int currentSum = 0;
        Random random = new Random();
        float selection = random.nextFloat();
        for (var c : confs) {
            var entry = c.split(":");
            if (entry.length != 2) continue;
            int chances = Integer.parseInt(entry[0]);
            int pigletCount = Integer.parseInt(entry[1]);
            if (selection <= (chances / 100.0f) + (currentSum / 100.0f)) {
                return pigletCount;
            }
            currentSum += chances;
        }
        return 0;
    }

    // 2 Dummy methods below
    protected PigsHaveLittersMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean cannotBeSilenced() { return super.cannotBeSilenced(); }
}
