package plugin.kits.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import plugin.kits.KitListener;

public class Fireman extends KitListener {

    @EventHandler
    public void onFire(PlayerInteractEvent e){
        if(e.getItem() != null){
            if(hasAbility(e.getPlayer()) && e.getItem().getType() == Material.MAGMA_CREAM){
                if(isCooldowned(e.getPlayer(),5000)) {
                    Player player = e.getPlayer();
                    Location pos = player.getLocation();
                    pos.setX(pos.getX() + pos.getDirection().getX() * 1.2);
                    pos.setZ(pos.getZ() + pos.getDirection().getZ() * 1.2);
                    pos.setY(pos.getY() + 1);
                    Entity bouboule = player.getWorld().spawnEntity(pos, EntityType.FIREBALL);
                    bouboule.setVelocity(new Vector(player.getEyeLocation().getDirection().getX() * 1.1, player.getEyeLocation().getDirection().getY() * 1.1, player.getEyeLocation().getDirection().getZ() * 1.1));
                }
            }
        }
    }
}
