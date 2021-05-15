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

    private boolean isStarted;

    public Lobby(){
        players = new HashSet<>();
        timer = -60;
        isStarted = false;
    }


    @Override
    public void run() {
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                timer++;
                if(isStarted){

                }else if(canStar()){
                    isStarted = true;
                }else if(timer < 0){
                    if(timer%15 == 0){
                        HungerGames.plugin.getServer().broadcastMessage("La partie commence dans " + -timer + " secondes.");
                    }
                }else{
                    HungerGames.plugin.getServer().broadcastMessage("Pas assez de joueur pour commencer la partie.");
                    timer = -60;
                }
            }
        }, 1000, 1000);
    }

    public void end(){
        HungerGames.plugin.getServer().broadcastMessage(players.iterator().next().getName() + " a gagné la partie ce bg !");
        time.cancel();
    }


    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }

    public boolean setStarted(boolean started) {
        if(isStarted){
            return false;
        }else{
            isStarted = started;
            timer = 0;
            return true;
        }
    }

    public boolean canStar(){
        return players.size() >= 2 && timer == 0;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
