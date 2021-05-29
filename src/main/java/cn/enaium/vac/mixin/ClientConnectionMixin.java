package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.event.ClientSendPacketEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Enaium
 */
@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(at = @At("HEAD"), method = "send(Lnet/minecraft/network/Packet;)V", cancellable = true)
    private void send(Packet<?> packet, CallbackInfo ci) {
        ClientSendPacketEvent clientSendPacketEvent = new ClientSendPacketEvent(packet);
        CF4M.EVENT.post(clientSendPacketEvent);
        if (clientSendPacketEvent.getCancel()) {
            ci.cancel();
        }
    }
}
