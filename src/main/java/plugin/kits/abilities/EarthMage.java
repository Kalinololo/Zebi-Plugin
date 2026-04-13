package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import plugin.kits.KitListener;

public class EarthMage extends KitListener {

    @EventHandler
    public void onEarthMage(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            if (item != null && item.getType() == Material.DIAMOND
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 12000)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 140, 0, false, false), true);
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}