package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import plugin.kits.KitListener;

public class Jumper extends KitListener {

    @EventHandler
    public void onJumper(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            if (e.getItem().getType() == Material.FIREWORK_ROCKET && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && hasAbility(player)) {
                if (isCooldowned(player, 10000)) {
                    Vector dir = player.getEyeLocation().getDirection();
                    player.setVelocity(new Vector(dir.getX() * 0.4, 1.6, dir.getZ() * 0.4));
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}
