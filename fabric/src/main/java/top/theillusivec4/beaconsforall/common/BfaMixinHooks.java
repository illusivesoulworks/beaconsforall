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
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Npc;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import top.theillusivec4.beaconsforall.common.config.BfaConfig;
import top.theillusivec4.beaconsforall.common.integration.BeaconOverhaulIntegration;
import top.theillusivec4.beaconsforall.mixin.AccessorBeaconBlockEntity;

public class BfaMixinHooks {

  private static final Predicate<LivingEntity> VALID_CREATURE = living ->
      !(living instanceof PlayerEntity) && isValidCreature(living);

  private static boolean isValidCreature(LivingEntity livingEntity) {
    boolean validType = switch (BfaConfig.creatureType) {
      case TAMED -> isTamed(livingEntity);
      case PASSIVE -> (livingEntity instanceof AnimalEntity || livingEntity instanceof Npc) &&
          !(livingEntity instanceof Monster);
      case ALL -> true;
      default -> false;
    };

    boolean validConfig = BfaConfig.additionalCreatures.contains(livingEntity.getType());
    return validType || validConfig;
  }

  private static boolean isTamed(LivingEntity livingEntity) {
    boolean flag =
        livingEntity instanceof TameableEntity && ((TameableEntity) livingEntity).isTamed();

    if (!flag) {
      flag = livingEntity instanceof HorseBaseEntity && ((HorseBaseEntity) livingEntity).isTame();
    }

    if (!flag) {
      flag = livingEntity instanceof Saddleable && ((Saddleable) livingEntity).isSaddled();
    }
    return flag;
  }

  public static void applyMobEffects(World world, Box box, int levels, StatusEffect primaryEffect,
                                     StatusEffect secondaryEffect, int power, int duration) {
    List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class, box, VALID_CREATURE);

    for (LivingEntity entity : list) {
      entity.addStatusEffect(new StatusEffectInstance(primaryEffect, duration, power, true, true));
    }

    if (levels >= 4 && primaryEffect != secondaryEffect && secondaryEffect != null) {

      for (LivingEntity entity : list) {
        entity.addStatusEffect(new StatusEffectInstance(secondaryEffect, duration, 0, true, true));
      }
    }
  }

  public static void tick(BeaconBlockEntity blockEntity) {

    if (BeaconsForAllMod.isBeaconOverhaulLoaded) {
      BeaconOverhaulIntegration.tick(blockEntity, VALID_CREATURE);
    }
  }
}
