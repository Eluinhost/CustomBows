package com.publicuhc.custombows.arrows;

import org.bukkit.entity.Arrow;

public interface ArrowBehaviour {

    /**
     * Takes the given arrow and applies the effects to it
     */
    void processArrow(Arrow arrow);
}
