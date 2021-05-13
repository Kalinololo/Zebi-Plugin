package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommands {

    void exec(CommandSender sender, Command command, String label, String[] args);

}
