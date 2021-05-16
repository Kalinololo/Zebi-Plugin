package plugin;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import plugin.commands.Commands;
import plugin.kits.Kit;
import plugin.kits.listeners.KitMenuListener;
import plugin.kits.lists.ListKitAbilities;
import plugin.party.Lobby;
import plugin.party.listeners.CustomDeathListener;
import plugin.party.listeners.LobbyListener;
import plugin.party.listeners.TrackingListener;

import java.io.File;

public class HungerGames extends JavaPlugin {

    public static JavaPlugin plugin;

    public static Inventory kitMenu;

    public static Lobby party;

    @Override
    public void onEnable() {
        Bukkit.getScheduler().cancelAllTasks();

        plugin = this;
        kitMenu = Kit.getKitMenu();
        party = new Lobby();

        plugin.getServer().createWorld(new WorldCreator("useless"));


        ListKitAbilities.loadAbilities();

        loadListeners();

        new Commands().start();

        HungerGames.plugin.getServer().setWhitelist(false);

    }

    @Override
    public void onDisable() {
        restart();

        party.stopTimer();
    }

    private void loadListeners(){
        plugin.getServer().getPluginManager().registerEvents(new KitMenuListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new CustomDeathListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LobbyListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new TrackingListener(), plugin);
    }

    private void deleteFile(File file){
        if(file.isFile()) {
            file.delete();
        }else{
            for (File f : file.listFiles()){
                deleteFile(f);
            }
            file.delete();
        }
    }

    private void changeWorld(){

        File world = new File(plugin.getServer().getWorldContainer().getPath() + "/" + "useless");

        plugin.getServer().unloadWorld("useless", false);

        deleteFile(world);
    }
    public void restart(){
        for (Player p: plugin.getServer().getOnlinePlayers()) {
            p.kickPlayer("§3Le serveur est en cours de redémarrage pour la prochaine partie !");
        }
        plugin.getServer().setWhitelist(true);
        changeWorld();
    }







}