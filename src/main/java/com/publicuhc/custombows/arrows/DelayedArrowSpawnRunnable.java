package com.publicuhc.custombows.arrows;

import com.publicuhc.custombows.bows.BowType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.ref.WeakReference;

public class DelayedArrowSpawnRunnable implements Runnable {

    private final WeakReference<LivingEntity> m_entity;
    private final BowType m_type;

    public DelayedArrowSpawnRunnable(LivingEntity entity, BowType type) {
        m_entity = new WeakReference<LivingEntity>(entity);
        m_type = type;
    }

    @Override
    public void run() {
        LivingEntity entity = m_entity.get();
        if(null == entity || entity.isDead()) {
            return;
        }
        Arrow arrow = (Arrow) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARROW);
        arrow.setShooter(entity);
        arrow.setMetadata("CB:BowType", m_type.getBowTypeMetadata());
        m_type.applyBehaviours(arrow);
    }
}
