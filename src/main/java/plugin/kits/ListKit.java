package plugin.kits;

import org.bukkit.Material;

import java.util.ArrayList;

public enum ListKit {


    SWAPPER("Swapper", "Tu pratiques la bataille de boule de neige à haut niveau ? Ce kit est fait pour toi ! Touche ta cible afin d'échanger de place avec celle-ci.", ListKitContent.COMPASS.getKitContent("SWAPPER"), ListKitAbilities.SWAP.getKitAbilities());




    private String name, description;
    private ArrayList<ListKitContent> items;
    private ListKitAbilities[] abilities;
    private Material image;

    ListKit(String name, String description, ArrayList<ListKitContent> items, ListKitAbilities[] abilities, Material image){
        this.name = name;
        this.description = description;
        this.abilities = abilities;
        this.items = items;
        this.image = image;
    }

    public ListKitAbilities[] getAbilities() {
        return abilities;
    }

    public ArrayList<ListKitContent> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Material getImage() {
        return image;
    }
}
