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

package top.theillusivec4.beaconsforall.common;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import top.theillusivec4.beaconsforall.BeaconsForAll;

public class BeaconsForAllConfig {

  public static final ForgeConfigSpec CONFIG_SPEC;
  public static final Config CONFIG;
  private static final String CONFIG_PREFIX = "gui." + BeaconsForAll.MODID + ".config.";

  static {
    final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
        .configure(Config::new);
    CONFIG_SPEC = specPair.getRight();
    CONFIG = specPair.getLeft();
  }

  public static CreatureType creatureType;
  public static List<EntityType<?>> additionalCreatures;

  public static void bake() {
    creatureType = CONFIG.creatureType.get();
    additionalCreatures = new ArrayList<>();
    CONFIG.additionalCreatures.get().forEach(creature -> {
      EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(creature));

      if (type != null) {
        additionalCreatures.add(type);
      }
    });
  }

  public static class Config {

    public final ForgeConfigSpec.EnumValue<CreatureType> creatureType;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> additionalCreatures;

    public Config(ForgeConfigSpec.Builder builder) {
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
