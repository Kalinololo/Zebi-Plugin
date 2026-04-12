package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityAirChangeEvent;
import org.bukkit.event.entity.EntityEnterBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import plugin.kits.KitListener;

public class Poseidon extends KitListener {
    
    @EventHandler
    public void onWater(EntityAirChangeEvent e) {
        try {
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                if ((player.isInWater() || player.getLocation().getBlock().isLiquid()) && hasAbility(player)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 30, 1));
                } else {
                    player.removePotionEffect(PotionEffectType.STRENGTH);
                }
            } 
        } catch (Exception ignored) {}
    }
}
