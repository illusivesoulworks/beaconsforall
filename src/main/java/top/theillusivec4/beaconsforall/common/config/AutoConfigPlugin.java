package top.theillusivec4.beaconsforall.common.config;

import java.util.ArrayList;
import java.util.List;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AutoConfigPlugin {

  private static BfaConfigData configData;

  public static void init() {
    configData =
        AutoConfig.register(BfaConfigData.class, JanksonConfigSerializer::new).getConfig();
  }

  public static void bake() {
    BfaConfig.creatureType = configData.creatureType;
    List<EntityType<?>> types = new ArrayList<>();

    for (String additionalCreature : configData.additionalCreatures) {
      Registry.ENTITY_TYPE.getOrEmpty(Identifier.tryParse(additionalCreature))
          .ifPresent(types::add);
    }
    BfaConfig.additionalCreatures = types;
  }

  public static Screen getConfigScreen(Screen screen) {
    return AutoConfig.getConfigScreen(BfaConfigData.class, screen).get();
  }
}
