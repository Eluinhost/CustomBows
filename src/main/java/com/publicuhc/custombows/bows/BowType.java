package com.publicuhc.custombows.bows;

import com.publicuhc.custombows.arrows.ArrowBehaviour;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class BowType {

    private List<ArrowBehaviour> m_behaviours = new ArrayList<ArrowBehaviour>();

    /**
     * @param behaviour the behaviour to add to this bow type
     */
    public void addBehaviour(ArrowBehaviour behaviour) {
        m_behaviours.add(behaviour);
    }

    public abstract String getType();

    @Override
    public final boolean equals(Object object) {
        if(!(object instanceof BowType)) {
            return false;
        }

        BowType type = (BowType) object;

        return type.getType().equals(getType());
    }

    @Override
    public final int hashCode(){
        return new HashCodeBuilder(17, 31).append(getType()).toHashCode();
    }
}
