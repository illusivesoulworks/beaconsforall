package top.theillusivec4.beaconsforall.loader.mixin;

import java.util.List;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BeaconBlockEntity.BeamSegment;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BeaconBlockEntity.class)
public interface BeaconBlockEntityAccessor {

  @Accessor
  List<BeamSegment> getBeamSegments();

  @Accessor
  StatusEffect getPrimary();

  @Accessor
  StatusEffect getSecondary();
}
