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

package com.illusivesoulworks.beaconsforall.platform;

import com.illusivesoulworks.beaconsforall.platform.services.IPlatform;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class QuiltPlatform implements IPlatform {

  @Override
  public EntityType<?> getEntityType(ResourceLocation resourceLocation) {
    return BuiltInRegistries.ENTITY_TYPE.get(resourceLocation);
  }

  @Override
  public List<EntityType<?>> getEntityTypes(ResourceLocation resourceLocation) {
    List<EntityType<?>> result = new ArrayList<>();
    BuiltInRegistries.ENTITY_TYPE.getTag(TagKey.create(Registries.ENTITY_TYPE, resourceLocation))
        .ifPresent(holders -> {
          for (Holder<EntityType<?>> holder : holders) {
            result.add(holder.value());
          }
        });
    return result;
  }
}
