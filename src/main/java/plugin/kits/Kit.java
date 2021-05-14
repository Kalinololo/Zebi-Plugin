package plugin.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.HungerGames;

import java.util.*;


public class Kit{

    private ListKit kit;
    private Player player;

    private static final HashMap<Player, ListKit> playerSelectedKit = new HashMap<>();

    public Kit(ListKit kit, Player p){
        this.kit = kit;
        this.player = p;

        if(playerSelectedKit.containsKey(p)){
            playerSelectedKit.replace(p, kit);
        }else{
            playerSelectedKit.put(p, kit);
        }
    }


    public void fillInventory(){
        ArrayList<ListKitContent> items = this.getKitItem();
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        for (ListKitContent item: items) {
            player.getInventory().addItem(item.getItem());
        }
    }

    public static ArrayList<ListKit> listKits(){
        return new ArrayList<>(Arrays.asList(ListKit.values()));
    }

    public static ListKit getKit(Player p){
        return playerSelectedKit.getOrDefault(p, null);
    }


    public ArrayList<ListKitAbilities> getKitAbilities(){
        return new ArrayList<>(Arrays.asList(kit.getAbilities()));
    }

    public ArrayList<ListKitContent> getKitItem(){
        return kit.getItems();
    }

    public Material getKitImage(){
        return kit.getImage();
    }

    public String getKitDesc(){
        return kit.getDescription();
    }

    public static void removeSelectedKit(Player p){
        playerSelectedKit.remove(p);
    }

    public static Inventory getKitMenu(){
        ArrayList<ListKit> kits = Kit.listKits();
        Inventory menu = Bukkit.createInventory(null, 54, "Sélectionne ton kit !");

        for (ListKit kit : kits) {
            ItemStack item = new ItemStack(kit.getImage(), 1);
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> itemDesc = new ArrayList<>();

            itemDesc.add(kit.getDescription());

            for (ListKitContent kitItem : kit.getItems()) {
                itemDesc.add(" - " + kitItem.getName());
            }

            meta.setDisplayName(kit.getName());
            meta.setLore(itemDesc);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

            item.setItemMeta(meta);

            menu.addItem(item);
        }

        return menu;
    }

    public static void openKitMenu(Player p){
        p.openInventory(HungerGames.kitMenu);
    }

}
