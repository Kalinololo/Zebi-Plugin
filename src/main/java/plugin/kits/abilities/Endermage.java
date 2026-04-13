package plugin.kits.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import plugin.kits.KitListener;

public class Endermage extends KitListener {

    @EventHandler
    public void onEndermage(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            if (e.getItem() != null && e.getItem().getType() == Material.ENDER_EYE
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 30000)) {
                    Block targetBlock = player.getTargetBlockExact(50);
                    if (targetBlock != null) {
                        Location targetLocation = targetBlock.getLocation().add(0.5, 1.0, 0.5);
                        for (Player target : player.getWorld().getPlayers()) {
                            if (target.equals(player)) continue;
                            Location targetPlayerLocation = target.getLocation();
                            double deltaX = Math.abs(targetPlayerLocation.getX() - player.getLocation().getX());
                            double deltaZ = Math.abs(targetPlayerLocation.getZ() - player.getLocation().getZ());
                            if (deltaX <= 10 && deltaZ <= 10) {
                                target.teleport(targetLocation);
                            }
                        }
                    }
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}