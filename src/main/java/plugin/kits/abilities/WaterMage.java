package plugin.kits.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import plugin.kits.KitListener;

public class WaterMage extends KitListener {

    @EventHandler
    public void onWaterMageUse(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            if (item != null && item.getType() == Material.HEART_OF_THE_SEA
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 10000)) {
                    Block targetBlock = player.getTargetBlockExact(20);
                    if (targetBlock != null) {
                        Block waterBlock = targetBlock.getRelative(BlockFace.UP);
                        if (waterBlock.getType() == Material.AIR) {
                            waterBlock.setType(Material.WATER);
                        }
                    }
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (!hasAbility(player)) {
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
            return;
        }

        if (isInOrTouchingWater(player)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40, 0, false, false), true);
            return;
        }

        player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
    }

    private boolean isInOrTouchingWater(Player player) {
        Location location = player.getLocation();
        Material feetBlock = location.getBlock().getType();
        Material bodyBlock = location.clone().add(0, 1, 0).getBlock().getType();
        Material underFeetBlock = location.clone().add(0, -0.1, 0).getBlock().getType();

        return feetBlock == Material.WATER
                || bodyBlock == Material.WATER
                || underFeetBlock == Material.WATER;
    }
}