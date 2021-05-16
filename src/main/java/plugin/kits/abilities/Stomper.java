package plugin.kits.abilities;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import plugin.kits.Kit;
import plugin.kits.KitListener;

public class Stomper extends KitListener {

    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (hasAbility((Player) e.getEntity())) {
                    double damage = e.getDamage();
                    e.setDamage(0);
                    for (Entity enemy : e.getEntity().getNearbyEntities(4, 4, 4)) {
                        if (enemy instanceof LivingEntity) {
                            ((LivingEntity) enemy).damage(damage, e.getEntity());
                        }
                    }
                }
            }
        }
    }


}
