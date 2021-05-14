package plugin.kits.abilities;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugin.kits.event.PlayerHitSnowballEvent;

public class SnowballSwap implements Listener {

    @EventHandler
    public void onSnowball(PlayerHitSnowballEvent e){
        swap(e.getShooter(), e.getShooted());
    }


    public void swap(Player shooter, Player shooted){
        Location posShooted = shooted.getLocation();

        shooted.teleport(shooter.getLocation());
        shooter.teleport(posShooted);
    }

}
