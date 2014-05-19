package com.publicuhc.custombows.bows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class SniperBow extends BowType {
    public SniperBow(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getType() {
        return "Sniper";
    }

    @Override
    public void triggerShot(Arrow original, LivingEntity shooter) {
        //TODO
    }
}
