package plugin.commands;

import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;

public class CommandDWorld extends Commands implements ICommands{

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            sender.getServer().unloadWorld(args[0], false);
            new File(sender.getServer().getWorldContainer().getPath() + "/" + args[0]).deleteOnExit();
            sender.sendMessage("§5Le monde a été §lsupprimé avec succés.");
        }
        return true;
    }
}
