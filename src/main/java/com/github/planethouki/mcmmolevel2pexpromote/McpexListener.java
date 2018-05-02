package com.github.planethouki.mcmmolevel2pexpromote;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.util.player.UserManager;

public class McmmoListener implements Listener {

	Mcmmo2pexrank plugin;

	public McmmoListener(Mcmmo2pexrank plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerLevelUpEvent(McMMOPlayerLevelUpEvent event) {
		plugin.getLogger().info("McMMOPlayerLevelUpEvent");
		plugin.getLogger().info("LevelsGained: " + Integer.toString(event.getLevelsGained()));
		plugin.getLogger().info("SkillLevel: " + Integer.toString(event.getSkillLevel()));
		plugin.getLogger().info("Skill: " + event.getSkill().getName());
		Player player = event.getPlayer();
		McMMOPlayer mcPlayer = UserManager.getPlayer(player);
		plugin.getLogger().info("PowerLevel: " + Integer.toString(mcPlayer.getPowerLevel()));

		//PermissionUser user = PermissionsEx.getUser(player);

	}

}
