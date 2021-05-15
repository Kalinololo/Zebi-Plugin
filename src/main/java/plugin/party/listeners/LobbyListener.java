package plugin.party.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.HungerGames;
import plugin.kits.Kit;
import plugin.party.Lobby;
import plugin.party.events.PlayerCustomDeathEvent;

import static plugin.kits.Kit.getKitSelector;

public class LobbyListener implements Listener{

    private Lobby party = HungerGames.party;

    @EventHandler
    public void onConnect(PlayerJoinEvent e){
        if(!party.isStarted()){
            Player p = e.getPlayer();
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().addItem(getKitSelector());

            party.addPlayer(e.getPlayer());
        }else{
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
        }

    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e){
        if(party.isStarted()){
            HungerGames.plugin.getServer().getPluginManager().callEvent(new PlayerCustomDeathEvent(e.getPlayer(), e.getPlayer(), 0));
        }
        if(Kit.getKit(e.getPlayer()) != null){
            Kit.removeSelectedKit(e.getPlayer());
        }
    }
}