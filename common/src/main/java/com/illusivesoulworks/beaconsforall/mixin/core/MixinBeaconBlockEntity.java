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

package com.illusivesoulworks.beaconsforall.mixin.core;

import com.illusivesoulworks.beaconsforall.mixin.BeaconsForAllMixinHooks;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity {

  @Inject(
      at = @At(
          value = "INVOKE",
          target = "net/minecraft/world/level/Level.getEntitiesOfClass(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;"),
      method = "applyEffects",
      locals = LocalCapture.CAPTURE_FAILSOFT)
  private static void beaconsforall$applyMobEffects(Level level, BlockPos pos, int beaconLevel,
                                                    @Nullable MobEffect primaryEffect,
                                                    @Nullable MobEffect secondaryEffect,
                                                    CallbackInfo cb, double range, int power,
                                                    int duration, AABB box) {
    BeaconsForAllMixinHooks.applyMobEffects(level, box, beaconLevel, primaryEffect, secondaryEffect,
        power, duration);
  }
}
