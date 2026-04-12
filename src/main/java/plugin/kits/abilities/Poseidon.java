package plugin.kits.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import plugin.HungerGames;
import plugin.kits.KitListener;

public class Poseidon extends KitListener {

    public Poseidon() {
        Bukkit.getScheduler().runTaskTimer(HungerGames.plugin, () -> {
            for (Player player : getMyPlayers()) {
                updateStrengthState(player);
            }
        }, 0L, 10L);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (!hasAbility(player)) {
            player.removePotionEffect(PotionEffectType.STRENGTH);
            return;
        }

        updateStrengthState(player);
    }

    private void updateStrengthState(Player player) {
        if (isInOrTouchingWater(player)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 1, false, false), true);
            return;
        }

        player.removePotionEffect(PotionEffectType.STRENGTH);
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
