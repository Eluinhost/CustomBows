package com.publicuhc.custombows.bows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class MinigunBow extends BowType {

    public MinigunBow(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getType() {
        return "Minigun";
    }

    @Override
    public void triggerShot(Arrow original, LivingEntity shooter) {
        //TODO
    }
}
