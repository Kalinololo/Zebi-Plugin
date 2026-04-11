package plugin.party;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
import plugin.HungerGames;
import plugin.kits.Kit;
import plugin.kits.lists.ListKit;

import java.io.File;
import java.util.*;

import static plugin.kits.Kit.getKitSelector;

public class Lobby implements Runnable {

    private int timer;

    private final Set<Player> players;

    private BukkitTask timeTask;

    private BukkitTask restartTask;

    private boolean isStarted;

    private boolean pvpActive;

    private HungerGames plugin;

    public Lobby(HungerGames plugin){
        players = new HashSet<>();
        timer = -61;
        isStarted = false;
        this.plugin = plugin;
    }

    public void start() {
        timeTask = plugin.getServer().getScheduler().runTaskTimer(plugin, this, 20L, 20L);
    }


    @Override
    public void run() {
        if (plugin.getServer().getOnlinePlayers().size() >= 1)
            init_players();

        timer++;
        if(isStarted){
            if(!pvpActive){
                startPVP();
            }
        }else if(canStart()){
            isStarted = true;
            startGame();
        }else if(timer < 0){
            if(timer%15 == 0 || (timer >= -5 && timer <= 0)){
                plugin.getServer().broadcastMessage("§6La partie commence dans " + -timer + " secondes.");
            }
        }else{
            plugin.getServer().broadcastMessage("§6Pas assez de joueur pour commencer la partie.");
            timer = -61;
        }
    }

    public void end(){
        plugin.getServer().broadcastMessage("§6" + players.iterator().next().getName() + " a gagné la partie ce bg !");
        HungerGames.isEnded = true;
        stopTimer();
        timer = 0;
        restartTask = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                timer++;
                if(timer == 10){
                    stop();
                    return;
                }
                plugin.getServer().broadcastMessage("Le serveur va être redémarrer dans " + (10-timer) + " secondes !");
            }
        }, 20L, 20L);

    }

    private void init_players()
    {
        for (Player p : plugin.getServer().getOnlinePlayers())
        {
            if (players.contains(p)){
                continue;
            }
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().addItem(getKitSelector());
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(true);
            p.setHealth(p.getMaxHealth());
            p.setFoodLevel(20);
            p.setSaturation(1000);
            for (PotionEffect effect : p.getActivePotionEffects())
                p.removePotionEffect(effect.getType());
            Location pos = HungerGames.plugin.getServer().getWorld("useless").getSpawnLocation();
            pos.setY(pos.getY() + 100);
            p.teleport(pos);
            addPlayer(p);
        }
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }

    public boolean isPvpActive() {
        return pvpActive;
    }

    public void startPVP(){
        if(timer == 60){
            plugin.getServer().broadcastMessage("§6Que le meilleur gagne !");
            pvpActive = true;
        }else if(timer%15 == 0 || (timer >= 55 && timer < 60)){
            plugin.getServer().broadcastMessage("§6Il reste " + (60-timer) + " secondes avant que le PVP s'active.");
        }
    }

    public boolean setStarted(boolean started) {
        if(isStarted){
            return false;
        }else{
            isStarted = started;
            startGame();
            return true;
        }
    }

    public boolean canStart(){
        return players.size() >= 2 && timer == 0;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void startGame(){
        for (Player p:players) {
            Location loc = p.getWorld().getSpawnLocation();
            loc.setY(loc.getY() + 100);
            p.setFoodLevel(20);
            p.teleport(loc);
            p.setSaturation(5);
            p.setGameMode(GameMode.SURVIVAL);
            p.setAllowFlight(false);
            if(Kit.getKit(p) == null)
            {
                Kit.setKit(p, ListKit.values()[(int) (Math.random() * (ListKit.values().length - 1))]);
            }
            Kit.fillInventory(p);
        }
    }

    public void stopTimer(){
        if (timeTask != null) {
            timeTask.cancel();
            timeTask = null;
        }
    }

    private void stopRestartTimer() {
        if (restartTask != null) {
            restartTask.cancel();
            restartTask = null;
        }
    }

    public void stop(){
        stopRestartTimer();
        stopTimer();
        plugin.stopServer();
    }
}
