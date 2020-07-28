package top.theillusivec4.beaconsforall.loader.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.beaconsforall.core.BeaconHooks;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {

  @SuppressWarnings("ConstantConditions")
  @Inject(at = @At("TAIL"), method = "tick")
  public void _beaconsforall_tick(CallbackInfo cb) {
    BeaconHooks.addBeaconEffectsToCreatures((BeaconBlockEntity) (Object) this);
  }
}
