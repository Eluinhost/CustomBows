package com.publicuhc.custombows.bows;

import com.publicuhc.custombows.events.CustomBowShootEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;
import java.util.List;

public class DefaultBowManager implements BowManager {

    private List<BowType> m_types = new ArrayList<BowType>();

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

        BowType type = getBowTypeForItemStack(held);
        //if it's not a special bow let the event continue
        if(null == type) {
            return;
        }

        //cancel the launch to make our own
        ple.setCancelled(true);

        CustomBowShootEvent event = new CustomBowShootEvent(arrow, type);

        Bukkit.getPluginManager().callEvent(event);

        if(event.isCancelled()) {
            return;
        }

        type.triggerShot(arrow, shooter);
    }

    @Override
    public BowType getBowTypeForItemStack(ItemStack item) {
        //TODO
        return null;
    }
}
