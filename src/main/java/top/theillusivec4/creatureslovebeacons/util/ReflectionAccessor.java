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

package top.theillusivec4.creatureslovebeacons.util;

import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;

public class ReflectionAccessor {

    public static boolean isComplete(TileEntityBeacon beacon) {
        return ObfuscationReflectionHelper.getPrivateValue(TileEntityBeacon.class, beacon, "field_146015_k");
    }

    @Nullable
    public static Potion getPrimaryEffect(TileEntityBeacon beacon) {
        return ObfuscationReflectionHelper.getPrivateValue(TileEntityBeacon.class, beacon, "field_146013_m");
    }

    @Nullable
    public static Potion getSecondaryEffect(TileEntityBeacon beacon) {
        return ObfuscationReflectionHelper.getPrivateValue(TileEntityBeacon.class, beacon, "field_146010_n");
    }
}
