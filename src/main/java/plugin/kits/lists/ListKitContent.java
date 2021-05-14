package plugin.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public enum ListKitContent {

    //Swapper

    SWAPPER_SNOWBALL(ListKit.SWAPPER, "Boule de neige x24", new ItemStack(Material.SNOW_BALL, 24)),
    SWAPPER_SHOVEL(ListKit.SWAPPER, "Pelle en fer", new ItemStack(Material.IRON_SPADE, 1)),


    COMPASS("Boussole", new ItemStack(Material.COMPASS, 1));

    private String name;
    private ListKit kit;
    private ItemStack item;

    ListKitContent(String name, ItemStack item){
        this.name = name;
        this.item = item;
    }

    ListKitContent(ListKit kit, String name, ItemStack item){
        this.kit = kit;
        this.name = name;
        this.item = item;
    }


    public ItemStack getItem() {
        return item;
    }

    public ListKit getKit() {
        return kit;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ListKitContent> getKitContent(ListKit kit){
        ArrayList<ListKitContent> itemsArray = new ArrayList<>();
        for (ListKitContent item: ListKitContent.values()) {
            if(item == COMPASS){
                itemsArray.add(item);
            }else if(item.getKit() == kit){
                itemsArray.add(item);
            }
        }
        return itemsArray;
    }

    public static ItemStack getPotion(PotionEffect p){
        ItemStack potion = new ItemStack(Material.POTION, 1);

        PotionMeta effect = (PotionMeta) potion.getItemMeta();
        effect.addCustomEffect(p, true);

        potion.setItemMeta(effect);

        return potion;
    }

}
