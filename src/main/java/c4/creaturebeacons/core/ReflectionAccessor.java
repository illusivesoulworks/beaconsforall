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
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

public class ReflectionAccessor {

    private static final Field IS_COMPLETE = ReflectionHelper.findField(TileEntityBeacon.class, "isComplete",
            "field_146015_k");
    private static final Field GET_PRIMARY = ReflectionHelper.findField(TileEntityBeacon.class, "primaryEffect",
            "field_146013_m");
    private static final Field GET_SECONDARY = ReflectionHelper.findField(TileEntityBeacon.class, "secondaryEffect",
            "field_146010_n");

    public static boolean isComplete(TileEntityBeacon beacon) {

        try {
            return IS_COMPLETE.getBoolean(beacon);
        } catch (IllegalAccessException e) {
            CreatureBeacons.logger.error("Can not access isComplete!");
        }
        return false;
    }

    @Nullable
    public static Potion getPrimaryEffect(TileEntityBeacon beacon) {

        try {
            return (Potion)GET_PRIMARY.get(beacon);
        } catch (IllegalAccessException e) {
            CreatureBeacons.logger.error("Can not access primaryEffect!");
        }
        return null;
    }

    @Nullable
    public static Potion getSecondaryEffect(TileEntityBeacon beacon) {

        try {
            return (Potion)GET_SECONDARY.get(beacon);
        } catch (IllegalAccessException e) {
            CreatureBeacons.logger.error("Can not access secondaryEffect!");
        }
        return null;
    }
}
