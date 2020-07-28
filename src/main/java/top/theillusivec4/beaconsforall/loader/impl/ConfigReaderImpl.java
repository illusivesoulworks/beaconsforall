package top.theillusivec4.beaconsforall.loader.impl;

import java.util.ArrayList;
import java.util.List;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.Tooltip;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import top.theillusivec4.beaconsforall.core.BeaconsForAll;
import top.theillusivec4.beaconsforall.core.base.ConfigReader;
import top.theillusivec4.beaconsforall.core.base.ModConfig.CreatureType;

@Config(name = BeaconsForAll.MODID)
public class ConfigReaderImpl implements ConfigData, ConfigReader {

  @Tooltip(count = 5)
  @Comment("Set which creatures can be affected by beacons")
  public CreatureType creatureType = CreatureType.TAMED;

  @Tooltip
  @Comment("List of specific additional creatures by registry name that can be affected by beacons")
  public List<String> additionalCreatures = new ArrayList<>();

  @Override
  public CreatureType getCreatureType() {
    return creatureType;
  }

  @Override
  public List<String> getAdditionalCreatures() {
    return additionalCreatures;
  }
}
