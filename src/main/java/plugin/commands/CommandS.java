package plugin.commands;

import org.bukkit.command.*;
import plugin.HungerGames;

public class Commands implements CommandExecutor, ICommands{

    public void start(){
        for (ListeCommands l: ListeCommands.values()) {
            HungerGames.plugin.getCommand(l.getName()).setExecutor(this);
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return ListeCommands.valueOf(command.getName().toUpperCase()).getCommands().exec(sender, command, label, args);
    }

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args){
        return true;
    }




}