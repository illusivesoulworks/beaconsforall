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

package c4.creaturebeacons;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class CreatureBeacons {

	private static final Predicate<Entity> VALID_CREATURE = (var0) -> !(var0 instanceof PlayerEntity) && !(var0 instanceof HostileEntity);

	public static void updateCreatureEffects(BeaconBlockEntity beacon, StatusEffect primary, StatusEffect secondary, boolean isActive) {

		if (beacon.hasWorld()) {
			int levels = beacon.getLevel();
			World world = beacon.getWorld();

			if (isActive && levels > 0 && !world.isRemote && primary != null) {
				double var1 = (double) (levels * 10 + 10);
				int var3 = 0;
				if (levels >= 4 && primary == secondary) {
					var3 = 1;
				}

				BlockPos pos = beacon.getPos();
				int var4 = (9 + levels * 2) * 20;
				int var5 = pos.getX();
				int var6 = pos.getY();
				int var7 = pos.getZ();
				BoundingBox var8 = (new BoundingBox((double) var5, (double) var6, (double) var7, (double) (var5 + 1), (double) (var6 + 1), (double) (var7 + 1))).expand(var1).stretch(0.0D, (double) world.getHeight(), 0.0D);
				List<Entity> var9 = world.getEntities(LivingEntity.class, var8, VALID_CREATURE);
				Iterator var10 = var9.iterator();

				LivingEntity var11;
				while (var10.hasNext()) {
					var11 = (LivingEntity) var10.next();
					var11.addPotionEffect(new StatusEffectInstance(primary, var4, var3, true, true));
				}

				if (levels >= 4 && primary != secondary && secondary != null) {
					var10 = var9.iterator();

					while (var10.hasNext()) {
						var11 = (LivingEntity) var10.next();
						var11.addPotionEffect(new StatusEffectInstance(secondary, var4, 0, true, true));
					}
				}
			}
		}
	}
}
