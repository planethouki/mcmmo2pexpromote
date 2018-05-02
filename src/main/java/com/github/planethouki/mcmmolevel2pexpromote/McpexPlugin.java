package com.github.planethouki.mcmmolevel2pexpromote;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class McpexPlugin extends JavaPlugin {

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		super.onDisable();
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new McpexListener(this), this);
		getCommand("mcpex").setExecutor(new McpexCommand(this));
		super.onEnable();
	}

}
