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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

public class ForgePlatform implements IPlatform {

  @Override
  public EntityType<?> getEntityType(ResourceLocation resourceLocation) {
    return ForgeRegistries.ENTITY_TYPES.getValue(resourceLocation);
  }

  @Override
  public List<EntityType<?>> getEntityTypes(ResourceLocation resourceLocation) {
    List<EntityType<?>> types = new ArrayList<>();
    ITagManager<EntityType<?>> tagManager = ForgeRegistries.ENTITY_TYPES.tags();

    if (tagManager != null) {
      TagKey<EntityType<?>> tag = tagManager.createTagKey(resourceLocation);
      tagManager.getTag(tag).forEach(types::add);
    }
    return types;
  }
}
