package plugin.commands;

import plugin.commands.commandClass.*;

public enum ListCommands {

    EZ("ez", new CommandEz()),
    FIOU("fiou", new CommandFiou()),
    LWORLD("lworld", new CommandLWorld()),
    DWORLD("dworld", new CommandDWorld()),
    CWORLD("cworld", new CommandCWorld()),
    GWORLD("gworld", new CommandGWorld()),
    KITS("kits", new CommandKits()),
    KIT("kit", new CommandKit()),
    SP("sp", new CommandSpectator()),
    S("s", new CommandSurvival()),
    C("c", new CommandCreative()),
    HG("hg", new CommandHGParty()),
    FLY("fly", new CommandFly());

    private final String name;
    private final ICommands commands;

    ListCommands(String name, ICommands commands){
        this.name = name;
        this.commands = commands;
    }

    public ICommands getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }
}
