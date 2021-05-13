package plugin.kits;

import plugin.kits.event.Swap;

import java.util.ArrayList;

public enum ListKitAbilities {

    SWAP("SWAP", new Swap());


    private String name;
    private Swap ability;

    ListKitAbilities(String name, Swap ability){
        this.name = name;
        this.ability = ability;
    }

    static ListKitAbilities[] getKitAbilities(){
        ArrayList<ListKitAbilities> abilitiesArray = new ArrayList<>();
        for (ListKitAbilities item: ListKitAbilities.values()) {
                abilitiesArray.add(item);
        }
        return (ListKitAbilities[]) abilitiesArray.toArray();
    }
}
