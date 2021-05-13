package plugin.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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

    public ListKitContent[] getKitContent(String kit){
        ArrayList<ListKitContent> itemsArray = new ArrayList<>();
        for (ListKitContent item: ListKitContent.values()) {
            if(item.name().equals("COMPASS")){
                itemsArray.add(item);
            }else if(item.getKit().getName().equals(kit)){
                itemsArray.add(item);
            }
        }
        return (ListKitContent[]) itemsArray.toArray();
    }

}
