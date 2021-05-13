package plugin.kits;

public enum ListKit {


    SWAPPER("Swapper", "Tu pratiques la bataille de boule de neige à haut niveau ? Ce kit est fait pour toi ! Touche ta cible afin d'échanger de place avec celle-ci.", ListKitContent.COMPASS.getKitContent("SWAPPER"), ListKitAbilities.SWAP.getKitAbilities());




    private String name, description;
    private ListKitContent[] items;
    private ListKitAbilities[] abilities;

    ListKit(String name, String description, ListKitContent[] items, ListKitAbilities[] abilities){
        this.name = name;
        this.description = description;
        this.abilities = abilities;
        this.items = items;
    }

    public ListKitAbilities[] getAbilities() {
        return abilities;
    }

    public ListKitContent[] getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
