package com.publicuhc.custombows.events;

import com.publicuhc.custombows.bows.BowType;
import org.bukkit.entity.Arrow;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomBowShootEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Arrow m_arrow;
    private boolean m_cancelled = false;
    private final BowType m_type;

    public CustomBowShootEvent(Arrow arrow, BowType type){
        m_arrow = arrow;
        m_type = type;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    /**
     * @return all the handlers
     */
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return m_cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        m_cancelled = cancelled;
    }

    /**
     * @return the arrow that started the event
     */
    public Arrow getArrow() {
        return m_arrow;
    }

    /**
     * @return the bow type that was matched for this event
     */
    public BowType getType() {
        return m_type;
    }
}
