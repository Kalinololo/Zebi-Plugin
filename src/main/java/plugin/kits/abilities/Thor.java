package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import plugin.kits.KitListener;


public class Thor extends KitListener {

    @EventHandler
    public void onThor(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            if (e.getItem().getType() == Material.MACE && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && hasAbility(player)) {
                if (isCooldowned(player, 15000)) {
                    player.getWorld().strikeLightning(player.getTargetBlock(null, 100).getLocation());
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
    
}
