package plugin.party;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import plugin.HungerGames;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Lobby implements Runnable {

    private int timer;

    private Set<Player> players;

    private Timer time;

    public Lobby(){
        players = new HashSet<>();
        timer = -60;
    }


    @Override
    public void run() {
        /*
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                timer++;
                HungerGames.plugin.getServer().broadcastMessage(String.valueOf(timer));
            }
        }, 1000, 1000);*/
    }

    public void end(){

    }


    public Set<Player> getPlayers() {
        return players;
    }

    public boolean isStarted(){
        return timer >= 0;
    }
}
