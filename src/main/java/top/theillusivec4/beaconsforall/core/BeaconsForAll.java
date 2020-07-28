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

package top.theillusivec4.beaconsforall.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.beaconsforall.core.base.Accessor;
import top.theillusivec4.beaconsforall.core.base.ConfigReader;
import top.theillusivec4.beaconsforall.core.base.ModConfig;
import top.theillusivec4.beaconsforall.loader.BeaconsForAllImpl;

public interface BeaconsForAll {

  static BeaconsForAll getInstance() {
    return BeaconsForAllImpl.INSTANCE;
  }

  static void bakeConfigs() {
    BeaconsForAll instance = BeaconsForAll.getInstance();
    instance.getConfig().bake(instance.getConfigReader());
  }

  String MODID = "beaconsforall";
  Logger LOGGER = LogManager.getLogger();

  Accessor getAccessor();

  ModConfig getConfig();

  void setConfigReader(ConfigReader reader);

  ConfigReader getConfigReader();
}
