package plugin.commands.commandClass;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.commands.ICommands;
import plugin.kits.Kit;

public class CommandEz implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (Kit.getKitAbilities(Kit.getKit(player)).contains("SEECRET") && player.getInventory().getBoots() != null) {
            if (player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                for (Player p : player.getServer().getOnlinePlayers()) {
                    if (player != p) {
                        p.damage(20, player);
                        return true;
                    }
                }
            }
        }
        player.damage(2, player);
        return true;
    }
}
