package com.publicuhc.custombows.bows;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public interface BowManager extends Listener {

    /**
     * Add the bow type to the list, overrides the existing bow type if they are the same
     * @param type the bow type
     */
    void addBowType(BowType type);

    /**
     * Do we have a bow type with the same type
     * @param type the bow type
     * @return true if found, false if not
     */
    boolean doesBowTypeExist(BowType type);

    /**
     * Do we have a bow type with the given id
     * @param id the bow type id
     * @return true if found, false if not
     */
    boolean doesBowTypeExist(String id);

    /**
     * Called when a projectile is launched, will detect if special bow and will do the rest
     * @param ple the event
     */
    void onProjectileLaunchEvent(ProjectileLaunchEvent ple);

    /**
     * Get the bow type applicable to the item
     * @param item the item to check
     * @return the bow type if exists, null otherwise
     */
    BowType getBowTypeForItemStack(ItemStack item);
}
