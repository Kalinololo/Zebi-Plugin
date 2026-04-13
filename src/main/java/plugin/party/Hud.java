package plugin.party;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import io.papermc.paper.scoreboard.numbers.NumberFormat;
import plugin.HungerGames;

public class Hud {

	private static final int PVP_DELAY_SECONDS = 90;
	private static final int BORDER_SHRINK_SECONDS = 9000;

	private final Scoreboard scoreboard;
	private final Objective objective;

	public Hud() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		if (manager == null) {
			throw new IllegalStateException("Unable to initialize scoreboard manager");
		}

		this.scoreboard = manager.getNewScoreboard();
		this.objective = this.scoreboard.registerNewObjective("zebi_hud", Criteria.DUMMY, "§6§lHUNGER GAMES");
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.numberFormat(NumberFormat.blank());
	}

	public void updateAll() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			updatePlayer(player);
		}
	}

	public void updatePlayer(Player player) {
		clearScores();

		Lobby party = HungerGames.party;
		int alivePlayers = party == null ? 0 : party.getPlayers().size();
		int timer = party == null ? 0 : party.getTimer();

		String phaseLabel;
		int phaseSeconds;

		if (party == null || !party.isStarted()) {
			phaseLabel = "Start in";
			phaseSeconds = Math.max(0, -timer);
		} else if (!party.isPvpActive()) {
			phaseLabel = "PvP enables in";
			phaseSeconds = Math.max(0, PVP_DELAY_SECONDS - timer);
		} else {
			phaseLabel = "Border stops in";
			int elapsedSincePvp = Math.max(0, timer - PVP_DELAY_SECONDS);
			phaseSeconds = Math.max(0, BORDER_SHRINK_SECONDS - elapsedSincePvp);
		}

		objective.getScore("§8§m----------------" + ChatColor.BLACK).setScore(5);
		objective.getScore("§fPlayers : §e" + alivePlayers + ChatColor.DARK_BLUE).setScore(4);
		objective.getScore(" " + ChatColor.DARK_GRAY).setScore(3);
		objective.getScore("§f" + phaseLabel + " §e" + formatSeconds(phaseSeconds) + ChatColor.DARK_GREEN).setScore(2);
		objective.getScore("§8§m----------------" + ChatColor.DARK_RED).setScore(1);

		if (player.getScoreboard() != scoreboard) {
			player.setScoreboard(scoreboard);
		}
	}

	public void clearAll() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		if (manager == null) {
			return;
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(manager.getMainScoreboard());
		}
	}

	private void clearScores() {
		for (String entry : scoreboard.getEntries()) {
			scoreboard.resetScores(entry);
		}
	}

	private String formatSeconds(int totalSeconds) {
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}
}
