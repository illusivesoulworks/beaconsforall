package top.theillusivec4.beaconsforall.loader.impl;

import java.util.List;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BeaconBlockEntity.BeamSegment;
import net.minecraft.entity.effect.StatusEffect;
import top.theillusivec4.beaconsforall.core.base.Accessor;
import top.theillusivec4.beaconsforall.loader.mixin.BeaconBlockEntityAccessor;

public class AccessorImpl implements Accessor {

  @Override
  public List<BeamSegment> getBeamSegments(BeaconBlockEntity beacon) {
    return ((BeaconBlockEntityAccessor) beacon).getBeamSegments();
  }

  @Override
  public StatusEffect getPrimaryEffect(BeaconBlockEntity beacon) {
    return ((BeaconBlockEntityAccessor) beacon).getPrimary();
  }

  @Override
  public StatusEffect getSecondaryEffect(BeaconBlockEntity beacon) {
    return ((BeaconBlockEntityAccessor) beacon).getSecondary();
  }
}
