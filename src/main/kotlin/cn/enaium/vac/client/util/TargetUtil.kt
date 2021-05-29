package cn.enaium.vac.client.util

import cn.enaium.vac.client.mc
import net.minecraft.entity.Entity
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * @author Enaium
 */
object TargetUtil {
    fun isTarget(e: Entity): Boolean {
        if (!(e is ItemEntity || e is ExperienceOrbEntity || e is PersistentProjectileEntity || e == mc.player)) {
            return true
        }

        if (e is LivingEntity) {
            return !e.isDead && e.health > 0
        }

        return false
    }

    fun getLiving(): Stream<LivingEntity> {
        return StreamSupport.stream(mc.world!!.entities.spliterator(), true).filter {
            it is LivingEntity
        }.map { it as LivingEntity }
    }

    fun getEntities(): MutableIterable<Entity> {
        return mc.world!!.entities;
    }
}