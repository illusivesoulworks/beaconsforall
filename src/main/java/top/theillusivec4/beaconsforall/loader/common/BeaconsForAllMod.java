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

package top.theillusivec4.beaconsforall.loader.common;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import top.theillusivec4.beaconsforall.core.BeaconsForAll;
import top.theillusivec4.beaconsforall.loader.impl.ConfigReaderImpl;

public class BeaconsForAllMod implements ModInitializer {

  @Override
  public void onInitialize() {
    BeaconsForAll.getInstance().setConfigReader(
        AutoConfig.register(ConfigReaderImpl.class, JanksonConfigSerializer::new).getConfig());
    ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> BeaconsForAll.bakeConfigs());
  }
}
