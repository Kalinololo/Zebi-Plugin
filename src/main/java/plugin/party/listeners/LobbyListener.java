package plugin.party.listeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static plugin.kits.Kit.getKitSelector;

public class LobbyListener implements Listener{

    @EventHandler
    public void onConnect(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().addItem(getKitSelector());
    }
}