package com.publicuhc.custombows.bows;

import com.publicuhc.custombows.arrows.DelayedArrowSpawnRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
        if(shooter instanceof Player) {
            Player player = (Player) shooter;
            //only allow full pull back
            if(original.getVelocity().length() < 2.8){
                player.sendMessage(ChatColor.RED + "Pull the bow all the way back to use the shotgun!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK,1,0);
                //dont fire anything
                original.remove();
                return;
            }
        }


        //get a copy of the original arrow velocity
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
