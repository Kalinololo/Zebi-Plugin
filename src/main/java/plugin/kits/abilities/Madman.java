package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.kits.KitListener;

public class Madman extends KitListener {

    @EventHandler
    public void onMadman(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            if (e.getItem() != null && e.getItem().getType() == Material.BLAZE_ROD
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 20000)) {
                    for (Player target : player.getWorld().getPlayers()) {
                        if (target.equals(player)) continue;
                        if (target.getLocation().distanceSquared(player.getLocation()) <= 100) {
                            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200, 0, false, false), true);
                            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0, false, false), true);
                        }
                    }
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}