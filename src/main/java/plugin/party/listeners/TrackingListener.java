package plugin.party.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import plugin.HungerGames;

public class TrackingListener implements Listener {

    @EventHandler
    public void onTrack(PlayerInteractEvent e){
        if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem().getType() == Material.COMPASS){
                Player player = e.getPlayer();
                double distance = player.getLocation().distance(getNearestPlayer(player).getLocation());
                player.sendMessage("§6Le joueur le plus proche est à §l" + (int) distance + " blocs.");
                if((int) distance == 0){
                    player.sendMessage("§6Il faut ouvrir les yeux, tu fais pas d'effort la.");
                }
            }
        }
    }

    @EventHandler
    public void activateTrackOnConnect(PlayerJoinEvent e){
        Bukkit.getScheduler().runTaskTimer(HungerGames.plugin, () -> compasTrack(e.getPlayer()), 1, 1);
    }

    private void compasTrack(Player p){
        p.setCompassTarget(getNearestPlayer(p).getLocation());
    }

    private Player getNearestPlayer(Player p){
        Location pos = p.getLocation();
        double nearestRange = 0;
        Player nearestPlayer = p;

        for (Player player: p.getWorld().getPlayers())
        {
            if(((player.getLocation().distance(pos) <= nearestRange || nearestRange==0) && player.getUniqueId() != p.getUniqueId()) && HungerGames.party.getPlayers().contains(player))
            {
                nearestRange = player.getLocation().distance(pos);
                nearestPlayer = player;
            }
        }

        return nearestPlayer;
    }

}
