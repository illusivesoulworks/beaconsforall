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

package top.theillusivec4.beaconsforall.common.config;

import java.util.ArrayList;
import java.util.List;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import top.theillusivec4.beaconsforall.common.BeaconsForAllMod;

@Config(name = BeaconsForAllMod.MOD_ID)
public class BfaConfigData implements ConfigData {

  @ConfigEntry.Gui.Tooltip(count = 5)
  @Comment("Set which creatures can be affected by beacons")
  public BfaConfig.CreatureType creatureType = BfaConfig.CreatureType.TAMED;

  @ConfigEntry.Gui.Tooltip
  @Comment("List of specific additional creatures by registry name that can be affected by beacons")
  public List<String> additionalCreatures = new ArrayList<>();
}
