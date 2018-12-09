/*
 * Copyright (C) 2018  C4
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

package c4.creaturebeacons.core;

import c4.creaturebeacons.CreatureBeacons;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = CreatureBeacons.MODID)
public class ConfigHandler {

    @Config.Name("Creature Type")
    @Config.Comment("Set which creatures can be affected by beacons")
    public static CreatureType creatureType = CreatureType.ALL_TAMED;

    @Config.Name("Additional Creatures")
    @Config.Comment("Add specific creatures that can be affected by beacons")
    public static String[] creatureList = new String[]{};

    enum CreatureType {
        ALL_TAMED,
        ALL_PASSIVE,
        ALL
    }

    @Mod.EventBusSubscriber(modid = CreatureBeacons.MODID)
    private static class ConfigEventHandler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent evt) {
            if (evt.getModID().equals(CreatureBeacons.MODID)) {
                ConfigManager.sync(CreatureBeacons.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
