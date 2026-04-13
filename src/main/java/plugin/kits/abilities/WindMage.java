package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import plugin.kits.KitListener;

public class WindMage extends KitListener {

    @EventHandler
    public void onWindMage(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            if (item != null && item.getType() == Material.FEATHER
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 10000)) {
                    Vector direction = player.getLocation().getDirection().normalize();
                    player.setVelocity(new Vector(direction.getX() * 1.2, 0.65, direction.getZ() * 1.2));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 0, false, false), true);
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}