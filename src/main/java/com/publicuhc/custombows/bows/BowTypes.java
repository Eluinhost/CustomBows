package com.publicuhc.custombows.bows;

import com.publicuhc.custombows.arrows.ArrowBehaviour;

import java.util.ArrayList;
import java.util.List;

public abstract class BowTypes {

    private List<ArrowBehaviour> m_behaviours = new ArrayList<ArrowBehaviour>();

    /**
     * @param behaviour the behaviour to add to this bow type
     */
    public void addBehaviour(ArrowBehaviour behaviour) {
        m_behaviours.add(behaviour);
    }

    public abstract String getType();
}
