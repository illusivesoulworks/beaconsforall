package top.theillusivec4.beaconsforall.loader.mixin;

import net.minecraft.server.command.ReloadCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.beaconsforall.core.BeaconsForAll;

@Mixin(ReloadCommand.class)
public class ReloadCommandMixin {

  @Inject(at = @At("HEAD"), method = "method_29480")
  private static void _beaconsforall_reload(CallbackInfo cb) {
    BeaconsForAll.bakeConfigs();
  }
}
