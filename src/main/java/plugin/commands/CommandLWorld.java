package plugin.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLWorld extends Commands implements ICommands{

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            Player player = (Player) sender;
            StringBuilder list = new StringBuilder("§5Worlds : §l");
            for (World w : player.getServer().getWorlds()) {
                list.append(w.getName()).append(" - §l");
            }

            player.sendMessage(list.toString());
        }

        return true;
    }

}
