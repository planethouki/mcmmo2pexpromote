package com.github.planethouki.mcmmolevel2pexpromote;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Mcmmo2pexrank extends JavaPlugin {

	@Override
	public void onDisable() {
		// TODO 自動生成されたメソッド・スタブ
		HandlerList.unregisterAll(this);
		super.onDisable();
	}

	@Override
	public void onEnable() {
		// TODO 自動生成されたメソッド・スタブ
		getServer().getPluginManager().registerEvents(new McmmoListener(this), this);
		getCommand("mcpex").setExecutor(new McmmoCommand(this));
		super.onEnable();
	}

}
