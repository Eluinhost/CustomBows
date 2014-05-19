package com.publicuhc.custombows.bows;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashSet;
import java.util.Set;

public class DefaultBowManager implements BowManager, Listener {

    private Set<BowType> m_types = new HashSet<BowType>();

    @Override
    public void addBowType(BowType type) {
        m_types.add(type);
    }

    @Override
    public boolean doesBowTypeExist(BowType type) {
        return m_types.contains(type);
    }

    @Override
    public boolean doesBowTypeExist(String id) {
        for(BowType type : m_types) {
            if(type.getType().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent ple) {
        if(!(ple.getEntity() instanceof Arrow)) {
            return;
        }
        Arrow arrow = (Arrow) ple.getEntity();

        ProjectileSource source = arrow.getShooter();
        if(!(source instanceof LivingEntity)) {
            return;
        }
        LivingEntity shooter = (LivingEntity) source;

        ItemStack held = shooter.getEquipment().getItemInHand();

        //Stop a 'quick switch' after shooting from doing anything
        //otherwise you can shoot a normal arrow from any bow type
        if(held.getType() != Material.BOW) {
            ple.setCancelled(true);
            return;
        }

        //TODO find the bow type and apply it's effects
    }

    @Override
    public BowType getBowTypeForItemStack(ItemStack item) {
        return null;
    }
}
