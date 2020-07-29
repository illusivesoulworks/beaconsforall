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

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.theillusivec4.beaconsforall.core.base.ConfigReader;
import top.theillusivec4.beaconsforall.core.base.ModConfig;

public class ModConfigImpl implements ModConfig {

  private List<EntityType<?>> additionalCreatures;
  private CreatureType creatureType;

  @Override
  public void setCreatureType(CreatureType type) {
    creatureType = type;
  }

  @Override
  public CreatureType getCreatureType() {
    return creatureType;
  }

  @Override
  public void setAdditionalCreatures(List<String> list) {
    List<EntityType<?>> types = new ArrayList<>();

    for (String id : list) {
      Registry.ENTITY_TYPE.getOrEmpty(new Identifier(id)).ifPresent(types::add);
    }
    additionalCreatures = types;
  }

  @Override
  public List<EntityType<?>> getAdditionalCreatures() {
    return additionalCreatures;
  }

  @Override
  public void bake(ConfigReader reader) {
    this.setAdditionalCreatures(reader.getAdditionalCreatures());
    this.setCreatureType(reader.getCreatureType());
  }
}
