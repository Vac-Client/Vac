package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Enable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.Render3DEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.setting.EnableSetting
import cn.enaium.vac.client.util.BlockUtil
import cn.enaium.vac.client.util.Render3DUtil
import net.minecraft.block.entity.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 * @author Enaium
 */
@Module("StorageESP", type = RENDER)
class StorageESP : Render(BoxType.BLOCK, DrawType.SOLID) {

    @Setting("Chest")
    private val chest = EnableSetting(true)

    @Setting("TrappedChest")
    private val trappedChest = EnableSetting(true)

    @Setting("EnderChest")
    private val enderChest = EnableSetting(true)

    @Setting("ShulkerBoxChest")
    private val shulkerBoxChest = EnableSetting(true)

    @Setting("Hopper")
    private val hopper = EnableSetting(true)

    @Setting("Furnace")
    private val furnace = EnableSetting(true)

    @Setting("Dispenser")
    private val dispenser = EnableSetting(true)

    @Setting("Dropper")
    private val dropper = EnableSetting(true)

    @Setting("Barrel")
    private val barrel = EnableSetting(true)

    @Setting("BlastFurnace")
    private val blastFurnace = EnableSetting(true)

    @Setting("Smoker")
    private val smoker = EnableSetting(true)

    @Event
    fun onRender(render3DEvent: Render3DEvent) {
        getTargets().forEach {
            drawBox(BlockUtil.getBoundingBox(it.pos), Color.GREEN, this)
        }
    }

    private fun getTargets(): ArrayList<BlockEntity> {
        val blocks: ArrayList<BlockEntity> = ArrayList()
        for (blockEntity in mc.world!!.blockEntities) {
            when (blockEntity) {
                is ChestBlockEntity -> if (chest.enable) blocks.add(blockEntity)
                is TrappedChestBlockEntity -> if (trappedChest.enable) blocks.add(blockEntity)
                is EnderChestBlockEntity -> if (enderChest.enable) blocks.add(blockEntity)
                is ShulkerBoxBlockEntity -> if (shulkerBoxChest.enable) blocks.add(blockEntity)
                is HopperBlockEntity -> if (hopper.enable) blocks.add(blockEntity)
                is DispenserBlockEntity -> if (dispenser.enable) blocks.add(blockEntity)
                is DropperBlockEntity -> if (dropper.enable) blocks.add(blockEntity)
                is FurnaceBlockEntity -> if (furnace.enable) blocks.add(blockEntity)
                is BarrelBlockEntity -> if (barrel.enable) blocks.add(blockEntity)
                is BlastFurnaceBlockEntity -> if (blastFurnace.enable) blocks.add(blockEntity)
                is SmokerBlockEntity -> if (smoker.enable) blocks.add(blockEntity)
            }
        }
        return blocks
    }
}