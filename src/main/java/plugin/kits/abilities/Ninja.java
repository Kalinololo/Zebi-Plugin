package plugin.kits.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import plugin.kits.KitListener;

public class Ninja extends KitListener {

    @EventHandler
    public void onNinja(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            if (item != null && item.getType() == Material.BLACK_DYE
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 30000)) {
                    Player target = getNearestPlayer(player);
                    if (target != null) {
                        teleportToTargetPosition(player, target);
                    }
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }

    private Player getNearestPlayer(Player player) {
        Player nearest = null;
        double nearestDistanceSquared = Double.MAX_VALUE;
        Location playerLocation = player.getLocation();

        if (playerLocation == null) {
            return null;
        }

        for (Player target : player.getWorld().getPlayers()) {
            if (target.equals(player)) {
                continue;
            }

            Location targetLocation = target.getLocation();
            if (targetLocation == null) {
                continue;
            }

            double distanceSquared = targetLocation.distanceSquared(playerLocation);
            if (distanceSquared < nearestDistanceSquared) {
                nearestDistanceSquared = distanceSquared;
                nearest = target;
            }
        }

        return nearest;
    }

    private void teleportToTargetPosition(Player player, Player target) {
        Location targetLocation = target.getLocation();
        if (targetLocation == null) {
            return;
        }
        player.teleport(targetLocation);
    }
}