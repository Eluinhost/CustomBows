package com.publicuhc.custombows.bows;

public interface BowManager {

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
}
