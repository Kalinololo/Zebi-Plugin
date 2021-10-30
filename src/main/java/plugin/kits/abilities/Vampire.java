package plugin.kits.abilities;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import plugin.kits.KitListener;

public class Vampire extends KitListener {
    @EventHandler
    public void onVampire(EntityDamageByEntityEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER) {
            Player damaged = (Player) e.getEntity();
            Player killer;
            try {
                if (e.getDamager() instanceof Player) {
                    killer = (Player) e.getDamager();
                } else {
                    killer = (Player) ((Projectile) e.getDamager()).getShooter();
                }
                if (damaged.getHealth() - e.getDamage() <= 0) {
                    if (hasAbility(killer)) {
                        killer.setHealth(killer.getHealth() + 9);
                    }
                }
            } catch (Exception ignored) {
            }
        }
    }
}
