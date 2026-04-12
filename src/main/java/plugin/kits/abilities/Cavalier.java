package plugin.kits.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import plugin.kits.KitListener;

import java.util.HashMap;
import java.util.UUID;

public class Cavalier extends KitListener {
    private static final HashMap<UUID, UUID> HORSE_BY_PLAYER = new HashMap<>();

    @EventHandler
    public void onCavalier(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            if (e.getItem().getType() == Material.SADDLE && (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && hasAbility(player)) {
                Horse horse;

                UUID horseId = HORSE_BY_PLAYER.get(player.getUniqueId());
                horse = player.getWorld().spawn(player.getLocation(), Horse.class);
                HORSE_BY_PLAYER.put(player.getUniqueId(), horse.getUniqueId());

                horse.setTamed(true);
                horse.setOwner(player);
                horse.setAdult();

                horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));

                ItemStack hand = e.getItem();
                if (hand != null) {
                    if (hand.getAmount() > 1) {
                        hand.setAmount(hand.getAmount() - 1);
                    } else {
                        player.getInventory().setItemInMainHand(null);
                    }
                }

                e.setCancelled(true);
            }
            if (e.getItem().getType() == Material.LEAD && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) && hasAbility(e.getPlayer())) {
                UUID horseId = HORSE_BY_PLAYER.get(player.getUniqueId());

                if (horseId != null) {
                    if (isCooldowned(player,10000)) {
                        Entity entity = Bukkit.getEntity(horseId);
                        if (entity instanceof Horse) {
                            Horse horse = (Horse) entity;
                            horse.teleport(player.getLocation());
                        }
                    }
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onLeadUse(PlayerInteractEntityEvent e) {
        try {
            Player player = e.getPlayer();
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.LEAD && hasAbility(player)) {
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}
