package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import plugin.kits.KitListener;

public class Beastmaster extends KitListener {

    @EventHandler
    public void onBeastmaster(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            if (item != null && item.getType() == Material.WOLF_SPAWN_EGG
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 60000)) {
                    Wolf wolf = player.getWorld().spawn(player.getLocation(), Wolf.class);
                    wolf.setOwner(player);
                    wolf.setAdult();
                    wolf.setTamed(true);
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}