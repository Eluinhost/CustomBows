package com.publicuhc.custombows.bows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class RocketLauncherBow extends BowType {

    public RocketLauncherBow(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getType() {
        return "Rocket Launcher";
    }

    @Override
    public void triggerShot(Arrow original, LivingEntity shooter) {
        //TODO
    }
}
