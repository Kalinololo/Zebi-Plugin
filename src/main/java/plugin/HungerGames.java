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

    public static boolean isEnded;

    @Override
    public void onEnable() {

        plugin = this;
        kitMenu = Kit.getKitMenu();
        party = new Lobby(this);
        System.out.println("COucoui");

        ListKitAbilities.loadAbilities();

        loadListeners();

        new Commands().start();

    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelAllTasks();
    }

    private void loadListeners(){
        this.getServer().getPluginManager().registerEvents(new KitMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new CustomDeathListener(), this);
        this.getServer().getPluginManager().registerEvents(new LobbyListener(), this);
        this.getServer().getPluginManager().registerEvents(new TrackingListener(), this);
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

        File world = new File(this.getServer().getWorldContainer().getPath() + "/" + "useless");

        this.getServer().unloadWorld("useless", false);

        deleteFile(world);

        this.getServer().createWorld(new WorldCreator("useless"));
    }

    public void restart(){
        for (Player p: this.getServer().getOnlinePlayers()) {
            p.kickPlayer("§3Le serveur est en cours de redémarrage pour la prochaine partie !");
        }
        this.getServer().setWhitelist(true);
    }

    public void stopServer(){
        restart();
        changeWorld();
    }
}