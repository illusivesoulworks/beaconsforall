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

package com.illusivesoulworks.beaconsforall.config;

import com.illusivesoulworks.beaconsforall.BeaconsForAllConstants;
import com.illusivesoulworks.beaconsforall.platform.Services;
import com.illusivesoulworks.spectrelib.config.SpectreConfigSpec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import org.apache.commons.lang3.tuple.Pair;

public class BeaconsForAllConfig {

  public static final SpectreConfigSpec CONFIG_SPEC;
  public static final Config CONFIG;
  private static final String CONFIG_PREFIX = "gui." + BeaconsForAllConstants.MOD_ID + ".config.";

  static {
    final Pair<Config, SpectreConfigSpec> specPair = new SpectreConfigSpec.Builder()
        .configure(Config::new);
    CONFIG_SPEC = specPair.getRight();
    CONFIG = specPair.getLeft();
  }

  public static final List<EntityType<?>> ADDITIONAL_CREATURES = new ArrayList<>();

  public static void reload() {
    ADDITIONAL_CREATURES.clear();
    CONFIG.additionalCreatures.get().forEach(creature -> {
      List<EntityType<?>> types = new ArrayList<>();

      if (creature.startsWith("#")) {
        types.addAll(Services.PLATFORM.getEntityTypes(new ResourceLocation(creature.substring(1))));
      } else {
        EntityType<?> type = Services.PLATFORM.getEntityType(new ResourceLocation(creature));

        if (type != null) {
          types.add(type);
        }
      }
      ADDITIONAL_CREATURES.addAll(types);
    });
  }

  public static class Config {

    public final SpectreConfigSpec.EnumValue<CreatureType> creatureType;
    public final SpectreConfigSpec.ConfigValue<List<? extends String>> additionalCreatures;

    public Config(SpectreConfigSpec.Builder builder) {
      creatureType = builder.comment("Set which creatures can be affected by beacons")
          .translation(CONFIG_PREFIX + "creatureType")
          .defineEnum("creatureType", CreatureType.TAMED);

      additionalCreatures = builder
          .comment("Add specific creatures that can be affected by beacons")
          .translation(CONFIG_PREFIX + "additionalCreatures")
          .defineList("additionalCreatures", new ArrayList<>(), s -> s instanceof String);
    }
  }

  public enum CreatureType {
    NONE, TAMED, PASSIVE, ALL
  }
}
