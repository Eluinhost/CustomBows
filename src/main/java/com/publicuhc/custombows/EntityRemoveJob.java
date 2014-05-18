package com.publicuhc.custombows;

import org.bukkit.entity.Entity;

import java.lang.ref.WeakReference;

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
