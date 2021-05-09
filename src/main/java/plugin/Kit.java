package plugin;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import plugin.HungerGames;

import java.io.File;
import java.util.List;
import java.util.Set;


public class Kit{

    private String kit;
    private Player player;

    private static FileConfiguration config = YamlConfiguration.loadConfiguration(HungerGames.kitFile);

    public Kit(String kit, Player p){
        this.kit = kit;
        this.player = p;
    }


    public void fillInventory(){
        List<String> items = config.getStringList(kit + ".items");
        player.getInventory().clear();

        for (String s: items) {
            String[] div = s.split(" ");
            ItemStack item = new ItemStack(Material.getMaterial(div[0]), Integer.parseInt(div[1]));
            player.getInventory().addItem(item);
        }

    }

    public static Set<String> listKits(){
        return config.getKeys(false);
    }

}
