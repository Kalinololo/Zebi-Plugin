package plugin.kits.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import plugin.HungerGames;
import plugin.kits.Kit;
import plugin.kits.lists.ListKit;

import static plugin.kits.Kit.getKitSelector;

public class KitMenuListener implements Listener {

    @EventHandler
    public void onKitMenuClick(PlayerInteractEvent e){
        if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem().getType() == getKitSelector().getType()) {
                e.getPlayer().openInventory(Kit.getKitMenu());
            }
        }
    }

    @EventHandler
    public void onDrop(InventoryClickEvent e){
        if(HungerGames.kitMenu.contains(e.getCurrentItem())){
            String kitName = e.getCurrentItem().getItemMeta().getDisplayName();
            Kit.setKit((Player) e.getWhoClicked(), ListKit.valueOf(kitName.toUpperCase()));
            e.getWhoClicked().sendMessage("§6Vous avez sélectionné le kit " + kitName + ".");
            e.setCancelled(true);
        }
    }

}
