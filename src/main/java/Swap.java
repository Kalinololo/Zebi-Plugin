import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.util.Vector;

public class Swap implements Listener{

    @EventHandler
    public void onZebi(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Snowball){
            if(((Snowball) e.getDamager()).getShooter() instanceof Player){
                Snowball bouboule = (Snowball) e.getDamager();

                Entity shooter = (Entity) bouboule.getShooter();
                Entity shooted = e.getEntity();

                Location posShooted = shooted.getLocation();

                shooted.teleport(shooter.getLocation());
                shooter.teleport(posShooted);
            }else{
                Entity k = e.getEntity();
                k.setVelocity(e.getDamager().getVelocity());
            }

        }
    }

    @EventHandler
    public void onCelianTBourre(PlayerInteractEvent e) throws InterruptedException {
        if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem().getType() == Material.GOLD_SWORD){
                Player player = e.getPlayer();
                Location pos = player.getLocation();
                pos.setX(pos.getX()+pos.getDirection().getX()*1.2);
                pos.setZ(pos.getZ()+pos.getDirection().getZ()*1.2);
                pos.setY(pos.getY()+1);
                Entity bouboule = player.getWorld().spawnEntity(pos, EntityType.SNOWBALL);
                bouboule.setVelocity(new Vector(player.getEyeLocation().getDirection().getX()*1.1, player.getEyeLocation().getDirection().getY()*1.1, player.getEyeLocation().getDirection().getZ()*1.1));
            }else if(e.getItem().getType() == Material.RED_MUSHROOM){
                Vector dir = e.getPlayer().getEyeLocation().getDirection();
                e.getPlayer().setVelocity(new Vector(dir.getX()*0.4, 1, dir.getZ()*0.4));
            }
        }
        if(e.getClickedBlock().getType() == Material.SIGN){
            for(int i = 0; i<5; i++) {
                e.getPlayer().getServer().broadcastMessage("La partie commence dans " + i);
            }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
            double damage = e.getDamage();
            e.setDamage(0);
            for (Entity enemy : e.getEntity().getNearbyEntities(4, 4, 4)) {
                if(enemy instanceof LivingEntity){
                    ((LivingEntity) enemy).damage(damage);
                }
            }
        }
    }

    @EventHandler
    public void onSign(PlayerInteractEvent e){

    }

}