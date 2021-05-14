package plugin.party.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerCustomDeathEvent extends Event implements Cancellable {

    private Player victim;
    private Player killer;
    private double damage;

    private boolean isCancelled;

    private static final HandlerList handlers = new HandlerList();

    public PlayerCustomDeathEvent(Player victim, Player killer, double damage){
        this.killer = killer;
        this.victim = victim;
        this.damage = damage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }

    public Player getKiller() {
        return killer;
    }

    public Player getVictim() {
        return victim;
    }

    public double getDamage() {
        return damage;
    }
}
