/*
 * Copyright (C) 2018-2019  C4
 *
 * This file is part of Creatures Love Beacons, a mod made for Minecraft.
 *
 * Creatures Love Beacons is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Creatures Love Beacons is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Creatures Love Beacons.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.creatureslovebeacons.common;

import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import top.theillusivec4.creatureslovebeacons.CreaturesLoveBeacons;

public class CreaturesLoveBeaconsConfig {

  public static final ForgeConfigSpec SPEC;
  public static final Server SERVER;
  private static final String CONFIG_PREFIX = "gui." + CreaturesLoveBeacons.MODID + ".config.";

  static {
    final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
        .configure(Server::new);
    SPEC = specPair.getRight();
    SERVER = specPair.getLeft();
  }

  public enum CreatureType {
    ALL_TAMED,
    ALL_PASSIVE,
    ALL
  }

  public static class Server {

    public final ForgeConfigSpec.EnumValue<CreatureType> creatureType;
    public final ForgeConfigSpec.ConfigValue<List<String>> additionalCreatures;

    public Server(ForgeConfigSpec.Builder builder) {
      builder.push("server");

      creatureType = builder
          .comment("Set which creatures can be affected by beacons [ALL_TAMED, ALL_PASSIVE, ALL]")
          .translation(CONFIG_PREFIX + "creatureType")
          .defineEnum("creatureType", CreatureType.ALL_TAMED);

      additionalCreatures = builder
          .comment("Add specific creatures that can be affected by beacons")
          .translation(CONFIG_PREFIX + "additionalCreatures")
          .define("additionalCreatures", new ArrayList<>());
    }
  }
}
