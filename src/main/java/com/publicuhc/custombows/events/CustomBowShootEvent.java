package com.publicuhc.custombows.events;

import com.publicuhc.custombows.bows.BowType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomBowShootEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Player m_player;
    private boolean m_cancelled = false;
    private final BowType m_type;

    public CustomBowShootEvent(Player player, BowType type){
        m_player = player;
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

    public Player getPlayer() {
        return m_player;
    }

    public BowType getType() {
        return m_type;
    }
}
