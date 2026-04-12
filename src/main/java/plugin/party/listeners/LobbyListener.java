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
import static plugin.kits.Kit.getKitSelector;
import plugin.party.Lobby;
import plugin.party.events.PlayerCustomDeathEvent;

public class LobbyListener implements Listener{

    private final Lobby party = HungerGames.party;

    @EventHandler
    public void onConnect(PlayerJoinEvent e){
        if(HungerGames.isEnded){
            e.getPlayer().kickPlayer("§8La partie est en cours de redémarrage.");
        } else if(!party.isStarted()){
            Player p = e.getPlayer();
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().addItem(getKitSelector());
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(true);
            p.setHealth(p.getMaxHealth());
            p.setFoodLevel(20);
            p.setSaturation(1000);
            for (PotionEffect effect : e.getPlayer().getActivePotionEffects())
                e.getPlayer().removePotionEffect(effect.getType());


            Location pos = HungerGames.plugin.getServer().getWorld("world").getSpawnLocation();
            pos.setY(pos.getY() + 100);

            p.teleport(pos);

            party.addPlayer(e.getPlayer());
        }else{
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            Location pos = HungerGames.plugin.getServer().getWorld("world").getSpawnLocation();
            pos.setY(pos.getY() + 100);
            e.getPlayer().teleport(pos);
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