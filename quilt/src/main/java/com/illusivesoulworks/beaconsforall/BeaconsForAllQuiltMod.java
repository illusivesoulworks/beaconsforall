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

package com.illusivesoulworks.beaconsforall;

import com.illusivesoulworks.beaconsforall.config.BeaconsForAllConfig;
import com.illusivesoulworks.spectrelib.config.SpectreLibInitializer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;

public class BeaconsForAllQuiltMod implements ModInitializer, SpectreLibInitializer {

  @Override
  public void onInitializeConfig(ModContainer modContainer) {
    BeaconsForAllMod.init();
  }

  @Override
  public void onInitialize(ModContainer mod) {
    ServerLifecycleEvents.READY.register(server -> BeaconsForAllConfig.loadConfigs());
  }
}
