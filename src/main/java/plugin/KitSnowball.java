package plugin;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KitSnowball extends Kit implements Listener {

    public KitSnowball(Player p) {
        super("SnowballMan", p);
    }

    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball) {
            if (((Snowball) e.getDamager()).getShooter() instanceof Player) {
                if(this.getClass() == KitSnowball.class){
                    Snowball bouboule = (Snowball) e.getDamager();

                    Entity shooter = (Entity) bouboule.getShooter();
                    Entity shooted = e.getEntity();

                    Location posShooted = shooted.getLocation();

                    shooted.teleport(shooter.getLocation());
                    shooter.teleport(posShooted);
                }
            }
        }
    }
}
