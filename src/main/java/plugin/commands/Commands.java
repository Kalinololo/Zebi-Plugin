package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import plugin.HungerGames;

public class Commands implements CommandExecutor {

    public void start(){
        for (ListCommands l: ListCommands.values()) {
            HungerGames.plugin.getCommand(l.getName()).setExecutor(this);
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return ListCommands.valueOf(command.getName().toUpperCase()).getCommands().exec(sender, command, label, args);
    }

}