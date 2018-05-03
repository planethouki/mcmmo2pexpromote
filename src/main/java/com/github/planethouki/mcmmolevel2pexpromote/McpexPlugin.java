package com.github.planethouki.mcmmolevel2pexpromote;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class McpexPlugin extends JavaPlugin {

	private List<Integer> targetPowers;
	private String version;

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		this.targetPowers = null;
		super.onDisable();
	}

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "config.yml"));
		this.targetPowers = new ArrayList<>();
		try {
			this.targetPowers = config.getIntegerList("mcMMOPower");
			this.getLogger().info("mcMMOPower: " + this.targetPowers.toString());
		} catch (Exception e) {
			this.getLogger().severe("configuration load failed");
			e.printStackTrace();
		}

		// Listener
		this.getServer().getPluginManager().registerEvents(new McpexListener(this), this);

		// Command
		this.getCommand("mcpex").setExecutor(new McpexCommand(this));

		super.onEnable();
	}

	public List<Integer> getTargetPowers() {
		return targetPowers;
	}

}
