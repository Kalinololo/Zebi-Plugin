package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface ICommands {

    void exec(CommandSender sender, Command command, String label, String[] args);

}
