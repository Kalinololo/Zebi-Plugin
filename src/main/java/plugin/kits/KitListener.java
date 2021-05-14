package plugin.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import plugin.HungerGames;
import plugin.kits.listeners.KitMenuListener;

import java.io.File;
import java.util.*;

public class KitListener implements Listener {

    protected transient Set<Player> myPlayers = new HashSet<Player>();

    private static HashMap<Player, Date> cooldownManager = new HashMap<>();

    public boolean hasAbility(Player p) {
        return myPlayers.contains(p);
    }

    public List<Player> getMyPlayers() {
        List<Player> playerList = new ArrayList<Player>();
        for (Player p : myPlayers) {
            if (p != null)
                playerList.add(p);
        }
        return playerList;
    }

    public void addPlayer(Player p){
        myPlayers.add(p);
    }

    public void removePlayer(Player p){
        myPlayers.remove(p);
    }

    public boolean isCooldowned(Player p, int cooldown) {
        Date now = new Date(System.currentTimeMillis());
        if (cooldownManager.containsKey(p)) {
            if (now.getTime() - cooldownManager.get(p).getTime() >= cooldown) {
                cooldownManager.replace(p, now);
                return true;
            } else {
                double timeRemain = (cooldown * 0.001) - ((now.getTime() - cooldownManager.get(p).getTime()) * 0.001);
                p.sendMessage("§6 Vous devez encore attendre " + (double) Math.round(timeRemain * 100) / 100 + " secondes.");
                return false;
            }
        } else {
            cooldownManager.put(p, now);
            return true;
        }
    }


}
