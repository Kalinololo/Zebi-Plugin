package plugin.kits.abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import plugin.kits.KitListener;

public class Swapper extends KitListener {

    @EventHandler
    public void onSnowball(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Snowball){
            Player shooter = (Player) ((Snowball)e.getDamager()).getShooter();
            if(hasAbility(shooter)){
                swap(shooter, (Player) e.getEntity());
            }else{
                Entity k = e.getEntity();
                k.setVelocity(e.getDamager().getVelocity());
            }
        }
    }


    public void swap(Player shooter, Player shooted){
        Location posShooted = shooted.getLocation();

        shooted.teleport(shooter.getLocation());
        shooter.teleport(posShooted);
    }

}
