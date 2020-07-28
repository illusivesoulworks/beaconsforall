package top.theillusivec4.beaconsforall.loader;

import top.theillusivec4.beaconsforall.core.BeaconsForAll;
import top.theillusivec4.beaconsforall.core.base.Accessor;
import top.theillusivec4.beaconsforall.core.base.ModConfig;
import top.theillusivec4.beaconsforall.core.base.ConfigReader;
import top.theillusivec4.beaconsforall.loader.impl.AccessorImpl;
import top.theillusivec4.beaconsforall.loader.impl.ModConfigImpl;

public class BeaconsForAllImpl implements BeaconsForAll {

  public static final BeaconsForAll INSTANCE = new BeaconsForAllImpl();

  private final Accessor accessor = new AccessorImpl();
  private final ModConfig config = new ModConfigImpl();

  private ConfigReader configReader;

  @Override
  public Accessor getAccessor() {
    return accessor;
  }

  @Override
  public ModConfig getConfig() {
    return config;
  }

  @Override
  public void setConfigReader(ConfigReader reader) {
    configReader = reader;
  }

  @Override
  public ConfigReader getConfigReader() {
    return configReader;
  }
}
