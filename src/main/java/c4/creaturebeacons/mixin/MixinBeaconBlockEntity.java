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

package c4.creaturebeacons.mixin;

import c4.creaturebeacons.CreatureBeacons;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.util.Tickable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntity.class)
public abstract class MixinBeaconBlockEntity extends LockableContainerBlockEntity implements SidedInventory, Tickable {

	@Shadow
	private StatusEffect primary;
	@Shadow
	private StatusEffect secondary;
	@Shadow
	private boolean field_11794;

	public MixinBeaconBlockEntity() {
		super(BlockEntityType.BEACON);
	}

	@Inject(at = @At("HEAD"), method = "method_10941")
	private void addStatusEffectsToCreatures(CallbackInfo info) {
		BeaconBlockEntity beacon = (BeaconBlockEntity)(Object)this;
		CreatureBeacons.updateCreatureEffects(beacon, primary, secondary, field_11794);
	}
}
