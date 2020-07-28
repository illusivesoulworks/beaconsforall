package top.theillusivec4.beaconsforall.core.base;

import java.util.List;
import net.minecraft.entity.EntityType;

public interface ModConfig {

  void setCreatureType(CreatureType type);

  CreatureType getCreatureType();

  void setAdditionalCreatures(List<String> list);

  List<EntityType<?>> getAdditionalCreatures();

  void bake(ConfigReader reader);

  enum CreatureType {
    NONE, TAMED, PASSIVE, ALL
  }
}
