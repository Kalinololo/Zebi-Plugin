package plugin.kits;

import plugin.kits.abilities.FireMan;
import plugin.EventManager;

import java.util.ArrayList;

public enum ListKitAbilities {

    SWAP("SWAP", new Swap());


    private String name;

    ListKitAbilities(String name){
        this.name = name;
    }
}
