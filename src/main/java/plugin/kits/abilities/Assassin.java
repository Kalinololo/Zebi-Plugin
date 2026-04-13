package plugin.kits.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.kits.KitListener;

public class Assassin extends KitListener {
    @Override
    public void removePlayer(Player p) {
        super.removePlayer(p);
        clearInvisible(p);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        if (!hasAbility(player)) {
            return;
        }

        if (e.isSneaking()) {
            setInvisible(player);
        } else {
            clearInvisible(player);
        }
    }

    private void setInvisible(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false), true);
    }

    private void clearInvisible(Player player) {
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
    }
}