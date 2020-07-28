package top.theillusivec4.beaconsforall.core.base;

import java.util.List;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BeaconBlockEntity.BeamSegment;
import net.minecraft.entity.effect.StatusEffect;

public interface Accessor {

  List<BeamSegment> getBeamSegments(BeaconBlockEntity beacon);

  StatusEffect getPrimaryEffect(BeaconBlockEntity beacon);

  StatusEffect getSecondaryEffect(BeaconBlockEntity beacon);
}
