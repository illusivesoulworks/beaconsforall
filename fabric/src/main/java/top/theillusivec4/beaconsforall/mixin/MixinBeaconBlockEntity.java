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

package top.theillusivec4.beaconsforall.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.beaconsforall.common.BfaMixinHooks;

@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity {

  @Inject(at = @At("TAIL"), method = "tick")
  private static void beaconsforall$tick(World world, BlockPos pos, BlockState state,
                                         BeaconBlockEntity blockEntity, CallbackInfo cb) {
    BfaMixinHooks.tick(blockEntity);
  }

  @Inject(
      at = @At(
          value = "INVOKE",
          target = "net/minecraft/world/World.getNonSpectatingEntities(Ljava/lang/Class;Lnet/minecraft/util/math/Box;)Ljava/util/List;"),
      method = "applyPlayerEffects",
      locals = LocalCapture.CAPTURE_FAILSOFT)
  private static void beaconsforall$applyMobEffects(World world, BlockPos pos, int beaconLevel,
                                                    @Nullable StatusEffect primaryEffect,
                                                    @Nullable StatusEffect secondaryEffect,
                                                    CallbackInfo cb, double range, int power,
                                                    int duration, Box box) {
    BfaMixinHooks.applyMobEffects(world, box, beaconLevel, primaryEffect, secondaryEffect, power,
        duration);
  }
}
