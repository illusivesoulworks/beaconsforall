/*
 * Copyright (c) 2018-2020 C4
 *
 * This file is part of Beacons For All, a mod made for Minecraft.
 *
 * Beacons For All is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Beacons For All is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Beacons For All.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.beaconsforall.common;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.beaconsforall.util.ReflectionAccessor;

public class BeaconsForAllEventHandler {

  private static final Predicate<LivingEntity> VALID_CREATURE = living ->
      !(living instanceof Player) && isValidCreature(living);

  private static boolean isValidCreature(LivingEntity livingEntity) {
    boolean validType = switch (BeaconsForAllConfig.creatureType) {
      case TAMED -> isTamed(livingEntity);
      case PASSIVE -> (livingEntity instanceof Animal || livingEntity instanceof Npc) &&
          !(livingEntity instanceof Enemy);
      case ALL -> true;
      default -> false;
    };

    boolean validConfig = BeaconsForAllConfig.additionalCreatures.contains(livingEntity.getType());
    return validType || validConfig;
  }

  private static boolean isTamed(LivingEntity livingEntity) {
    boolean flag =
        livingEntity instanceof TamableAnimal && ((TamableAnimal) livingEntity).isTame();

    if (!flag) {
      flag = livingEntity instanceof AbstractHorse && ((AbstractHorse) livingEntity).isTamed();
    }

    if (!flag) {
      flag = livingEntity instanceof Saddleable && ((Saddleable) livingEntity).isSaddled();
    }
    return flag;
  }

  private static void addBeaconEffectsToCreatures(BeaconBlockEntity beacon, Level world) {
    int levels = ReflectionAccessor.getLevels(beacon);
    MobEffect primaryEffect = ReflectionAccessor.getPrimaryEffect(beacon);

    if (ReflectionAccessor.getBeamSegments(beacon).isEmpty() || levels <= 0
        || primaryEffect == null) {
      return;
    }
    MobEffect secondaryEffect = ReflectionAccessor.getSecondaryEffect(beacon);
    BlockPos pos = beacon.getBlockPos();
    double d0 = levels * 10 + 10;
    int i = 0;

    if (levels >= 4 && primaryEffect == secondaryEffect) {
      i = 1;
    }

    int j = (9 + levels * 2) * 20;
    AABB axisalignedbb = (new AABB(pos)).inflate(d0)
        .expandTowards(0.0D, world.getMaxBuildHeight(), 0.0D);
    List<LivingEntity> list = world
        .getEntitiesOfClass(LivingEntity.class, axisalignedbb, VALID_CREATURE);

    for (LivingEntity entity : list) {
      entity.addEffect(new MobEffectInstance(primaryEffect, j, i, true, true));
    }

    if (levels >= 4 && primaryEffect != secondaryEffect && secondaryEffect != null) {

      for (LivingEntity entity : list) {
        entity.addEffect(new MobEffectInstance(secondaryEffect, j, 0, true, true));
      }
    }
  }

  @SubscribeEvent
  public void onWorldTick(TickEvent.WorldTickEvent evt) {
    Level world = evt.world;

    if (world.isClientSide || evt.phase != TickEvent.Phase.END || world.getGameTime() % 80L != 0L) {
      return;
    }

    for (TickingBlockEntity tickable : ReflectionAccessor.getBlockEntities(world)) {
      BlockPos pos = tickable.getPos();
      world.getBlockEntity(pos, BlockEntityType.BEACON)
          .ifPresent(beaconBlockEntity -> addBeaconEffectsToCreatures(beaconBlockEntity, world));
    }
  }
}
