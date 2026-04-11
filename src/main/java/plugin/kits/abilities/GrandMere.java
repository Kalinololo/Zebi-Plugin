package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import plugin.kits.KitListener;



public class GrandMere extends KitListener {
    @EventHandler
    public void onSoupe(PlayerInteractEvent e){
        if(e.getItem() != null && e.getItem().getType() == Material.MUSHROOM_STEW && hasAbility(e.getPlayer())){
            if(isCooldowned(e.getPlayer(), 7500)){
                double miam = e.getPlayer().getHealth();
                e.getPlayer().setHealth(miam + 6);
            }
            e.setCancelled(true);
        }
    }
}


