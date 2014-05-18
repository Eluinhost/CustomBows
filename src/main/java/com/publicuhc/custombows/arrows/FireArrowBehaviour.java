package com.publicuhc.custombows.arrows;

import org.bukkit.entity.Arrow;

/**
 * Set the arrow on fire forever
 */
public class FireArrowBehaviour implements ArrowBehaviour {

    @Override
    public void processArrow(Arrow arrow) {
        arrow.setFireTicks(Integer.MAX_VALUE);
    }
}
