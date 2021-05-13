package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.commands.ICommands;

public class CommandGWorld implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            Player player = (Player) sender;
            player.teleport(sender.getServer().getWorld(args[0]).getSpawnLocation());
        }
        return true;
    }

}
