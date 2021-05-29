package cn.enaium.vac.client.event

import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.network.Packet
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d

/**
 * @author Enaium
 */
class Render2DEvent(val matrices: MatrixStack)
class MotioningEvent(var yaw: Float, var pitch: Float, var ground: Boolean, var vec3d: Vec3d)
class MotionedEvent(val yaw: Float, val pitch: Float, val ground: Boolean, val vec3d: Vec3d)
class Render3DEvent(val tickDelta: Float, val limitTime: Long, val matrixStack: MatrixStack)
class EntityRenderEvent(
    val entity: Entity,
    val yaw: Float,
    val tickDelta: Float,
    val matrices: MatrixStack,
    val vertexConsumers: VertexConsumerProvider,
    val light: Int
)

class RenderTooltipEvent(
    val matrices: MatrixStack,
    val stack: ItemStack,
    val tooltip: ArrayList<Text>,
    val x: Int,
    val y: Int
)

open class Cancel {
    var cancel: Boolean = false
}

class RenderItemEntityEvent(
    val itemEntity: ItemEntity,
    val yaw: Float,
    val delta: Float,
    val matrixStack: MatrixStack,
    val vertexConsumerProvider: VertexConsumerProvider,
    val light: Int
) : Cancel()

class ClientSendPacketEvent(val packet: Packet<*>) : Cancel()
class KeyboardEvent(val key: Int) : Cancel()
class AttackBlockEvent(val pos: BlockPos, val direction: Direction) : Cancel()
class BlockBreakingProgressEvent(val pos: BlockPos, val direction: Direction) : Cancel()
