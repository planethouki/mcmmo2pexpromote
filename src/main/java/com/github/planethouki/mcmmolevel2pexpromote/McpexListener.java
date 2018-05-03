package com.github.planethouki.mcmmolevel2pexpromote;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.util.player.UserManager;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.exceptions.RankingException;

public class McpexListener implements Listener {

	McpexPlugin plugin;

	public McpexListener(McpexPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerLevelUpEvent(McMMOPlayerLevelUpEvent event) {
		Player player = event.getPlayer();
		McMMOPlayer mcPlayer = UserManager.getPlayer(player);
		int level = mcPlayer.getPowerLevel();

		if (plugin.getTargetPowers().contains(level)) {
			this.pexPromote(player);
		}

//		plugin.getLogger().info("McMMOPlayerLevelUpEvent");
//		plugin.getLogger().info("LevelsGained: " + Integer.toString(event.getLevelsGained()));
//		plugin.getLogger().info("SkillLevel: " + Integer.toString(event.getSkillLevel()));
//		plugin.getLogger().info("Skill: " + event.getSkill().getName());
//		plugin.getLogger().info("PowerLevel: " + Integer.toString(mcPlayer.getPowerLevel()));
	}

	private void pexPromote(Player player) {
		PermissionUser user = PermissionsEx.getUser(player);
		if (user == null) {
			plugin.getLogger().severe("Pex cannot find user. User=" + player.getName());
			return;
		}
		user.getRank(null);
		try {
			user.promote(null, null);
			player.sendMessage("Your role is level up! Now: " + user.getRankLadderGroup(null).getName());
			plugin.getLogger().info("Promote User=" + user.getName() + " Group=" + user.getRankLadderGroup(null).getName());
		} catch (RankingException e) {
			//e.printStackTrace();
			plugin.getLogger().warning(e.getMessage() + " User=" + e.getTarget() + " Group=" + user.getRankLadderGroup(null).getName());
		}
	}

}
