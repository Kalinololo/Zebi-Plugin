package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import plugin.HungerGames;
import plugin.commands.ICommands;

import java.io.File;

public class CommandHGParty implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if(args[0].equals("stop")){
                HungerGames.party.end();
            }else if(args[0].equals("start")){
                if(!HungerGames.party.setStarted(true)){
                    sender.sendMessage("La partie a déjà commencé.");
                }
            }
        }
        return true;
    }
}

