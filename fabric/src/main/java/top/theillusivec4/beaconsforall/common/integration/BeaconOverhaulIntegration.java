package top.theillusivec4.beaconsforall.common.integration;

import com.google.common.base.Predicate;
import dev.sapphic.beacons.PotencyTier;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import top.theillusivec4.beaconsforall.mixin.AccessorBeaconBlockEntity;

public class BeaconOverhaulIntegration {

  public static void tick(BeaconBlockEntity blockEntity, Predicate<LivingEntity> validCreature) {
    World world = blockEntity.getWorld();

    if (world == null || world.isClient() || world.getTime() % 80L != 0L) {
      return;
    }
    AccessorBeaconBlockEntity accessor = ((AccessorBeaconBlockEntity) blockEntity);
    StatusEffect primary = accessor.getPrimary();

    if (primary == null) {
      return;
    }
    StatusEffect secondary = accessor.getSecondary();
    int levels = accessor.getLevel();
    final int tier = PotencyTier.get(blockEntity).ordinal();
    int primaryAmplifier = tier;
    int secondaryAmplifier = tier;

    if (primary == StatusEffects.NIGHT_VISION) {
      primaryAmplifier = 0;
    }

    if ((secondary == StatusEffects.SLOW_FALLING) || (secondary == StatusEffects.FIRE_RESISTANCE)) {
      secondaryAmplifier = 0;
    }

    if ((levels >= 4) && (primary == secondary)) {
      primaryAmplifier = Math.min(primaryAmplifier, secondaryAmplifier);
      primaryAmplifier++;
    }
    final double radius = (levels * 10.0) + (10.0 * (tier + 1));
    final int duration = ((9 * (tier + 1)) + (levels * 2)) * 20;
    final Box box =
        new Box(blockEntity.getPos()).expand(radius).stretch(0.0, world.getHeight(), 0.0);
    final boolean uniqueSecondary = (levels >= 4) && (primary != secondary) && (secondary != null);

    for (final LivingEntity livingEntity : world.getEntitiesByClass(LivingEntity.class, box,
        validCreature)) {
      livingEntity.addStatusEffect(
          new StatusEffectInstance(primary, duration, primaryAmplifier, true, true));

      if (uniqueSecondary) {
        livingEntity.addStatusEffect(
            new StatusEffectInstance(secondary, duration, secondaryAmplifier, true, true));
      }
    }
  }
}
