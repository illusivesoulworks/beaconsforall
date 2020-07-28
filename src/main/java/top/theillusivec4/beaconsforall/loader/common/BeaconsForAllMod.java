package top.theillusivec4.beaconsforall.loader.common;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import top.theillusivec4.beaconsforall.core.BeaconsForAll;
import top.theillusivec4.beaconsforall.loader.impl.ConfigReaderImpl;

public class BeaconsForAllMod implements ModInitializer {

  @Override
  public void onInitialize() {
    BeaconsForAll.getInstance().setConfigReader(
        AutoConfig.register(ConfigReaderImpl.class, JanksonConfigSerializer::new).getConfig());
    ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> BeaconsForAll.bakeConfigs());
  }
}
