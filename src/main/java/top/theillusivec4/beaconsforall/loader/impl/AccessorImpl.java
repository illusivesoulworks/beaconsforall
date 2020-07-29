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

package top.theillusivec4.beaconsforall.loader.impl;

import java.util.List;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BeaconBlockEntity.BeamSegment;
import net.minecraft.entity.effect.StatusEffect;
import top.theillusivec4.beaconsforall.core.base.Accessor;
import top.theillusivec4.beaconsforall.loader.mixin.BeaconBlockEntityAccessor;

public class AccessorImpl implements Accessor {

  @Override
  public List<BeamSegment> getBeamSegments(BeaconBlockEntity beacon) {
    return ((BeaconBlockEntityAccessor) beacon).getBeamSegments();
  }

  @Override
  public StatusEffect getPrimaryEffect(BeaconBlockEntity beacon) {
    return ((BeaconBlockEntityAccessor) beacon).getPrimary();
  }

  @Override
  public StatusEffect getSecondaryEffect(BeaconBlockEntity beacon) {
    return ((BeaconBlockEntityAccessor) beacon).getSecondary();
  }
}
