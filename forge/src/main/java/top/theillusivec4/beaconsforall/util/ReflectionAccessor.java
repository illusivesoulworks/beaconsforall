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

package top.theillusivec4.beaconsforall.util;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BeaconBlockEntity.BeaconBeamSection;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class ReflectionAccessor {

  public static List<TickingBlockEntity> getBlockEntities(Level level) {
    return ObfuscationReflectionHelper.getPrivateValue(Level.class, level, "f_151512_");
  }

  public static Integer getLevels(BeaconBlockEntity beacon) {
    return ObfuscationReflectionHelper.getPrivateValue(BeaconBlockEntity.class, beacon, "f_58650_");
  }

  public static List<BeaconBeamSection> getBeamSegments(BeaconBlockEntity beacon) {
    return ObfuscationReflectionHelper
        .getPrivateValue(BeaconBlockEntity.class, beacon, "f_58648_");
  }

  @Nullable
  public static MobEffect getPrimaryEffect(BeaconBlockEntity beacon) {
    return ObfuscationReflectionHelper
        .getPrivateValue(BeaconBlockEntity.class, beacon, "f_58652_");
  }

  @Nullable
  public static MobEffect getSecondaryEffect(BeaconBlockEntity beacon) {
    return ObfuscationReflectionHelper
        .getPrivateValue(BeaconBlockEntity.class, beacon, "f_58653_");
  }
}
