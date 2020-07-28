package top.theillusivec4.beaconsforall.loader.impl;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.theillusivec4.beaconsforall.core.base.ConfigReader;
import top.theillusivec4.beaconsforall.core.base.ModConfig;

public class ModConfigImpl implements ModConfig {

  private List<EntityType<?>> additionalCreatures;
  private CreatureType creatureType;

  @Override
  public void setCreatureType(CreatureType type) {
    creatureType = type;
  }

  @Override
  public CreatureType getCreatureType() {
    return creatureType;
  }

  @Override
  public void setAdditionalCreatures(List<String> list) {
    List<EntityType<?>> types = new ArrayList<>();

    for (String id : list) {
      Registry.ENTITY_TYPE.getOrEmpty(new Identifier(id)).ifPresent(types::add);
    }
    additionalCreatures = types;
  }

  @Override
  public List<EntityType<?>> getAdditionalCreatures() {
    return additionalCreatures;
  }

  @Override
  public void bake(ConfigReader reader) {
    this.setAdditionalCreatures(reader.getAdditionalCreatures());
    this.setCreatureType(reader.getCreatureType());
  }
}
