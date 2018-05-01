package com.github.planethouki.mcmmolevel2pexpromote;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.util.commands.CommandUtils;
import com.gmail.nossr50.util.player.UserManager;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.exceptions.RankingException;

public class McmmoCommand implements CommandExecutor {

	Mcmmo2pexrank plugin;

	public McmmoCommand(Mcmmo2pexrank plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		plugin.getLogger().info("McmmoCommand");
		if (args.length == 0) {
			sender.sendMessage("args needed");
			return false;
		}
		switch (args[0]) {
			case "pex":
				executePex(sender, command, label, args);
				break;
			default:
				execute1(sender, command, label, args);
				execute2(sender, command, label, args);
				break;
		}
		return true;
	}


	private boolean executePex(CommandSender sender, Command command, String label, String[] args) {

		PermissionUser user = PermissionsEx.getUser(args[0]);
		if (user == null) {
			plugin.getLogger().info("PermissionUser null");
			return false;
		}
		try {
			user.promote(null, null);
		} catch (RankingException e) {
			e.printStackTrace();
			plugin.getLogger().severe("RankingException Occured. Check Console.");
			return false;
		}
		return true;
	}

	private boolean execute1(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			return false;
		}
		plugin.getLogger().info("args: " + args[0]);
		String playerName = CommandUtils.getMatchedPlayerName(args[0]);
		McMMOPlayer mcPlayer = UserManager.getOfflinePlayer(playerName);
		if (mcPlayer == null) {
			sender.sendMessage("McMMOPlayer null");
		} else {
			plugin.getLogger().info("PowerLevel: " + Integer.toString(mcPlayer.getPowerLevel()));

			Player target = mcPlayer.getPlayer();
			CommandUtils.printGatheringSkills(target, sender);
			CommandUtils.printCombatSkills(target, sender);
			CommandUtils.printMiscSkills(target, sender);
		}

		return true;
	}


	private boolean execute2(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			return false;
		}
		Player player = Bukkit.getOfflinePlayer(args[0]).getPlayer();
		if (player == null) {
			sender.sendMessage("Player null");
		} else {
			if (player.hasPlayedBefore()) {
			    UUID uuid = player.getUniqueId();
			    sender.sendMessage("UUID: " + uuid.toString());
			}
			int toReturn = 0;
			for (SkillType skill : SkillType.values()) {
				toReturn += ExperienceAPI.getLevel(player, skill.name());
			}
			plugin.getLogger().info("TotalLevel: " + toReturn);
		}

		return true;
	}

}
