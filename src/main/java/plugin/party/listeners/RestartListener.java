package plugin.party.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import plugin.HungerGames;
import plugin.kits.Kit;
import plugin.party.Lobby;
import plugin.party.events.PlayerCustomDeathEvent;

import static plugin.kits.Kit.getKitSelector;

public class RestartListener implements Listener{

    @EventHandler
    public void onConnectR(PlayerJoinEvent e){
        if(HungerGames.isEnded){
            e.getPlayer().kickPlayer("§8La partie est en cours de redémarrage.");
        }
    }
}