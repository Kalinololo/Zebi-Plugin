package plugin.kits.abilities;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import plugin.kits.KitListener;

public class Gambler extends KitListener {

    private static final PotionEffectType[] RANDOM_EFFECTS = new PotionEffectType[]{
            PotionEffectType.SPEED,
            PotionEffectType.SLOWNESS,
            PotionEffectType.HASTE,
            PotionEffectType.MINING_FATIGUE,
            PotionEffectType.STRENGTH,
            PotionEffectType.INSTANT_HEALTH,
            PotionEffectType.JUMP_BOOST,
            PotionEffectType.REGENERATION,
            PotionEffectType.RESISTANCE,
            PotionEffectType.FIRE_RESISTANCE,
            PotionEffectType.WATER_BREATHING,
            PotionEffectType.INVISIBILITY,
            PotionEffectType.NIGHT_VISION,
            PotionEffectType.ABSORPTION,
            PotionEffectType.SLOW_FALLING,
            PotionEffectType.NAUSEA,
            PotionEffectType.WEAKNESS,
            PotionEffectType.BLINDNESS
            };

    @EventHandler
    public void onGambler(PlayerInteractEvent e) {
        try {
            Player player = e.getPlayer();
            org.bukkit.inventory.ItemStack item = e.getItem();
            if (item != null && item.getType() == Material.PAPER
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && hasAbility(player)) {
                if (isCooldowned(player, 10000)) {
                        PotionEffectType randomEffect = RANDOM_EFFECTS[ThreadLocalRandom.current().nextInt(RANDOM_EFFECTS.length)];
                    player.addPotionEffect(new PotionEffect(randomEffect, 200, 0, false, false), true);
                }
                e.setCancelled(true);
            }
        } catch (Exception ignored) {}
    }
}