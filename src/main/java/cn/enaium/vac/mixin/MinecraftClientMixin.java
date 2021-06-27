package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.VacKt;
import cn.enaium.vac.client.event.AttackEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.util.Session;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Enaium
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    @Final
    private Session session;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void MinecraftClient(RunArgs args, CallbackInfo ci) {
        VacKt.run();
    }

    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;crosshairTarget:Lnet/minecraft/util/hit/HitResult;", ordinal = 0), method = "doAttack")
    private void doAttack(CallbackInfo ci) {
        CF4M.EVENT.post(new AttackEvent());
    }

    @Inject(at = @At("HEAD"), method = "getSession", cancellable = true)
    private void getSession(CallbackInfoReturnable<Session> cir) {
        if (VacKt.getSession() != null) {
            cir.setReturnValue(VacKt.getSession());
        }
    }

    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;session:Lnet/minecraft/client/util/Session;", opcode = Opcodes.GETFIELD, ordinal = 0), method = "getSessionProperties()Lcom/mojang/authlib/properties/PropertyMap;")
    private Session getSessionForSessionProperties(MinecraftClient mc) {
        if (VacKt.getSession() != null)
            return VacKt.getSession();
        else
            return session;
    }
}
