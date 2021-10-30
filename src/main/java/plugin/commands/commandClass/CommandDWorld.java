package plugin.commands.commandClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import plugin.HungerGames;
import plugin.commands.ICommands;

import java.io.File;

public class CommandDWorld implements ICommands {

    @Override
    public boolean exec(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            try{
                File world = new File(sender.getServer().getWorldContainer().getPath() + "/" + args[0]);

                if(!world.exists()){
                    sender.sendMessage("§5Le monde spécifié n'existe pas.");
                    return false;
                }

                HungerGames.plugin.getServer().unloadWorld(args[0], false);
                deleteFile(world);

                sender.sendMessage("§5Le monde a été §lsupprimé avec succés.");
            } catch (Exception e) {
                sender.sendMessage("§5Échec lors de la suppression du monde.");
            }
        }
        return true;
    }


    private void deleteFile(File file){
        if(file.isFile()) {
            file.delete();
        }else{
            for (File f : file.listFiles()){
                deleteFile(f);
            }
            file.delete();
        }
    }
}

