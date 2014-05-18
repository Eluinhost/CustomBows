package com.publicuhc.custombows;

import org.bukkit.entity.Entity;

import java.lang.ref.WeakReference;

/**
 * Creates a runnable that will remove the entity when it runs, will do nothing if the entity no longer exists
 */
public class EntityRemoveJob implements Runnable {

    private final WeakReference<Entity> m_entity;

    public EntityRemoveJob(Entity entity) {
        m_entity = new WeakReference<Entity>(entity);
    }

    @Override
    public void run() {
        Entity entity = m_entity.get();
        if(entity != null){
            entity.remove();
        }
    }
}
