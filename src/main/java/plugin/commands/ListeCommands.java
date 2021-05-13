package plugin.commands;

public enum ListeCommands {

    EZ("ez", new CommandEz()),
    C("c", new CommandCreative());

    private final String name;
    private final Commands commands;

    ListeCommands(String name, Commands commands){
        this.name = name;
        this.commands = commands;
    }

    public Commands getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }
}
