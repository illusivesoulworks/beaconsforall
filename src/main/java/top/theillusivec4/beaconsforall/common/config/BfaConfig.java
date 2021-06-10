package top.theillusivec4.beaconsforall.common.config;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityType;

public class BfaConfig {

  public static CreatureType creatureType = CreatureType.TAMED;
  public static List<EntityType<?>> additionalCreatures = new ArrayList<>();

  public enum CreatureType {
    NONE, TAMED, PASSIVE, ALL
  }
}
