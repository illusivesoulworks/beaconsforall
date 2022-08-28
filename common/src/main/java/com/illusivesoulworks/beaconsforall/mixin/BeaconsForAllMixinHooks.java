/*
 * Copyright (C) 2018-2022 Illusive Soulworks
 *
 * Beacons For All is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Beacons For All is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Beacons For All. If not, see <https://www.gnu.org/licenses/>.
 */

package com.illusivesoulworks.beaconsforall.mixin;

import com.illusivesoulworks.beaconsforall.BeaconsForAllMod;
import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class BeaconsForAllMixinHooks {

  public static void applyMobEffects(Level level, AABB box, int beaconLevel,
                                     MobEffect primaryEffect, MobEffect secondaryEffect, int power,
                                     int duration) {
    List<LivingEntity> list = level
        .getEntitiesOfClass(LivingEntity.class, box, BeaconsForAllMod::canApplyEffects);

    for (LivingEntity entity : list) {
      entity.addEffect(new MobEffectInstance(primaryEffect, duration, power, true, true));
    }

    if (beaconLevel >= 4 && primaryEffect != secondaryEffect && secondaryEffect != null) {

      for (LivingEntity entity : list) {
        entity.addEffect(new MobEffectInstance(secondaryEffect, duration, 0, true, true));
      }
    }
  }
}
