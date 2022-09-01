package plugin.kits.abilities;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import plugin.kits.KitListener;



public class GrandMere extends KitListener {

    @EventHandler
    public void onSoupe(PlayerItemConsumeEvent e){
        if(e.getItem().getType() == Material.MUSHROOM_SOUP && hasAbility(e.getPlayer())){
            double miam = e.getPlayer().getHealth();
            e.getPlayer().setHealth(miam + 4);
        }
    }
}
