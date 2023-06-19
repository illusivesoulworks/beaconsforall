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

package com.illusivesoulworks.beaconsforall;

import com.illusivesoulworks.beaconsforall.config.BeaconsForAllConfig;
import com.illusivesoulworks.spectrelib.config.SpectreConfig;
import com.illusivesoulworks.spectrelib.config.SpectreConfigLoader;
import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;

public class BeaconsForAllMod {

  private static final Predicate<LivingEntity> VALID_CREATURE = living ->
      !(living instanceof Player) && isValidCreature(living);

  public static void init() {
    SpectreConfig config =
        SpectreConfigLoader.add(SpectreConfig.Type.SERVER, BeaconsForAllConfig.CONFIG_SPEC,
            BeaconsForAllConstants.MOD_ID);
    config.addLoadListener((cfg, flag) -> BeaconsForAllConfig.reload());
  }

  public static boolean canApplyEffects(LivingEntity livingEntity) {
    return VALID_CREATURE.test(livingEntity);
  }

  private static boolean isValidCreature(LivingEntity livingEntity) {
    boolean validType = switch (BeaconsForAllConfig.CONFIG.creatureType.get()) {
      case TAMED -> isTamed(livingEntity);
      case PASSIVE -> (livingEntity instanceof Animal || livingEntity instanceof Npc) &&
          !(livingEntity instanceof Enemy);
      case ALL -> true;
      default -> false;
    };

    boolean validConfig = BeaconsForAllConfig.ADDITIONAL_CREATURES.contains(livingEntity.getType());
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
}