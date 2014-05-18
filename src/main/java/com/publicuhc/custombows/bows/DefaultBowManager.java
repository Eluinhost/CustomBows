package com.publicuhc.custombows.bows;

import java.util.HashSet;
import java.util.Set;

public class DefaultBowManager implements BowManager {

    private Set<BowType> m_types = new HashSet<BowType>();

    @Override
    public void addBowType(BowType type) {
        m_types.add(type);
    }

    @Override
    public boolean doesBowTypeExist(BowType type) {
        return m_types.contains(type);
    }

    @Override
    public boolean doesBowTypeExist(String id) {
        for(BowType type : m_types) {
            if(type.getType().equals(id)) {
                return true;
            }
        }
        return false;
    }


}
