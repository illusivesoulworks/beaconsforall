package top.theillusivec4.beaconsforall.core.base;

import java.util.List;
import top.theillusivec4.beaconsforall.core.base.ModConfig.CreatureType;

public interface ConfigReader {

  CreatureType getCreatureType();

  List<String> getAdditionalCreatures();
}
