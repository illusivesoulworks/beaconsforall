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
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.BeaconTileEntity.BeamSegment;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ReflectionAccessor {

  public static List<BeamSegment> getBeamSegments(BeaconTileEntity beacon) {
    return ObfuscationReflectionHelper
        .getPrivateValue(BeaconTileEntity.class, beacon, "field_174909_f");
  }

  @Nullable
  public static Effect getPrimaryEffect(BeaconTileEntity beacon) {
    return ObfuscationReflectionHelper
        .getPrivateValue(BeaconTileEntity.class, beacon, "field_146013_m");
  }

  @Nullable
  public static Effect getSecondaryEffect(BeaconTileEntity beacon) {
    return ObfuscationReflectionHelper
        .getPrivateValue(BeaconTileEntity.class, beacon, "field_146010_n");
  }
}
