package plugin.kits.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import plugin.kits.KitListener;

public class Berserker extends KitListener {

    @EventHandler
    public void onBerserker(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player) || !hasAbility(player)) {
            return;
        }

        int strengthLevel = (int) Math.max(0, Math.floor((20.0 - player.getHealth()) / 6.0));
        if (strengthLevel <= 0) {
            return;
        }

        e.setDamage(e.getDamage() + (3.0 * strengthLevel));
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 20, strengthLevel - 1, false, false), true);
    }
}