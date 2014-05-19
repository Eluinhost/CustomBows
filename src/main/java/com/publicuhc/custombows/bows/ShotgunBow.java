package com.publicuhc.custombows.bows;

import com.publicuhc.custombows.arrows.DelayedArrowSpawnRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class ShotgunBow extends BowType {

    public ShotgunBow(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getType() {
        return "Shotgun";
    }

    @Override
    public void triggerShot(Arrow original, LivingEntity shooter) {
        //get a copy of the original arrow
        Vector regular = original.getVelocity().clone();

        original.getWorld().playSound(original.getLocation(), Sound.EXPLODE, 4, 0);

        //create 8 arrows in a batch
        for(int j = 0; j<8; j++){
            DelayedArrowSpawnRunnable arrowSpawn = new DelayedArrowSpawnRunnable(shooter, this);
            Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), arrowSpawn, j);
        }

        //remove the original arrow
        original.remove();
    }
}
