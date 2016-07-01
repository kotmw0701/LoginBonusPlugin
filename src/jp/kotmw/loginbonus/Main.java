package jp.kotmw.loginbonus;

import java.io.File;

import jp.kotmw.loginbonus.FileDatas.BonusItem;
import jp.kotmw.loginbonus.FileDatas.PlayerDatas;
import jp.kotmw.loginbonus.command.LBCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main main;
	public static String PPrefix = "[LoginBonusPlugin]";
	public String filepath = getDataFolder() + File.separator;

	@Override
	public void onEnable() {
		main = this;
		getServer().getPluginManager().registerEvents(new Events(), this);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.reloadConfig();
		if(!PlayerDatas.playerdatadir.exists())
			PlayerDatas.playerdatadir.mkdir();
		BonusItem.enablefile();
		getCommand("loginbonus").setExecutor(new LBCommand());
	}

	@Override
	public void onDisable() {}
}
