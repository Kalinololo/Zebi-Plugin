import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Swap(), this);
        this.getCommand("kit").setExecutor(new Commands());
        this.getCommand("surfacerandom").setExecutor(new Commands());
        this.getCommand("ez").setExecutor(new Commands());
        this.getCommand("fiou").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        getLogger().info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

}