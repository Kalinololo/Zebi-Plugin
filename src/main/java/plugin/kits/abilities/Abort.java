package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import plugin.kits.KitListener;
import org.bukkit.event.EventHandler;

public class Abort extends KitListener {
    @EventHandler
    public void onAbort(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            if (e.getItem().getType() == Material.ENDER_PEARL && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && hasAbility(player)) {
                if (isCooldowned(player, 3000)) {
                    Vector dir = player.getEyeLocation().getDirection(); // TODO : Change to player direction
                    player.setVelocity(new Vector(dir.getX() * 1.8, dir.getY() * 1.8, dir.getZ() * 1.8));
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}
