package plugin.commands.commandClass;

import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import plugin.commands.ICommands;

public class CommandCWorld implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            sender.getServer().createWorld(new WorldCreator(args[0]));
            sender.sendMessage("§5Le monde §l" + args[0] + " a été §lcréé avec succés.");
        }
        return true;
    }
}
