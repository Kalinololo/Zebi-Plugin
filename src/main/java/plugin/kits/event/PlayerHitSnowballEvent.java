package plugin.kits.event;

import com.google.common.base.Function;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerHitSnowballEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player shooter, shooted;
    private Snowball snowball;
    private boolean isCancelled;

    public PlayerHitSnowballEvent(Player shooter, Player shooted, Snowball snowball){
        this.shooter = shooter;
        this.shooted = shooted;
        this.snowball = snowball;
        this.isCancelled = false;
    }


    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getShooted() {
        return shooted;
    }

    public Player getShooter() {
        return shooter;
    }

    public Snowball getSnowball() {
        return snowball;
    }
}
