package com.github.planethouki.mcmmolevel2pexpromote;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class McpexCommand implements CommandExecutor {

	McpexPlugin plugin;

	public McpexCommand(McpexPlugin plugin) {
		super();
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch (args.length) {
			case 0:
				return false;
			case 1:
				if ( args[0].equalsIgnoreCase("version") ) {
					sender.sendMessage("version: " + plugin.getDescription().getVersion());
				} else if ( args[0].equalsIgnoreCase("reload") ) {
					plugin.onDisable();
					plugin.onEnable();
					sender.sendMessage("reload complete");
				} else if ( args[0].equalsIgnoreCase("show") ) {
					sender.sendMessage("mcMMOPower: " + plugin.getTargetPowers().toString());
				} else {
					return false;
				}
				break;
			default:
				return false;
		}
		return true;
	}

}
