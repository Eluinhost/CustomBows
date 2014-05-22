package com.publicuhc.custombows.bows;

import com.publicuhc.custombows.arrows.ArrowBehaviour;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public abstract class BowType {

    private List<ArrowBehaviour> m_behaviours = new ArrayList<ArrowBehaviour>();
    private final Plugin m_plugin;

    public BowType(Plugin plugin) {
        m_plugin = plugin;
    }

    protected Plugin getPlugin() {
        return m_plugin;
    }

    /**
     * @param behaviour the behaviour to add to this bow type
     */
    public void addBehaviour(ArrowBehaviour behaviour) {
        m_behaviours.add(behaviour);
    }

    public void applyBehaviours(Arrow arrow) {
        for(ArrowBehaviour behaviour : m_behaviours) {
            behaviour.processArrow(arrow);
        }
    }

    /**
     * @return the name for this bow type
     */
    public abstract String getType();

    /**
     * Trigger a shot from the bow
     * @param original the original arrow
     */
    public abstract void triggerShot(Arrow original, LivingEntity shooter);

    public FixedMetadataValue getBowTypeMetadata() {
        return new FixedMetadataValue(m_plugin, getType());
    }

    @Override
    public final boolean equals(Object object) {
        if(null == object || !(object instanceof BowType)) {
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
