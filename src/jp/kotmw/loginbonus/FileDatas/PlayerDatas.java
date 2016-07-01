package jp.kotmw.loginbonus.FileDatas;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerDatas extends PluginFiles {

	public static File playerdatadir = new File(filepath + "Players");
	public static String dirpath ="Players";

	public static void createPlayerFile(String uuid, String name) {
		FileConfiguration file = new YamlConfiguration();
		file.set("PlayerName", name);
		file.set("LastLogin", getDate());
		file.set("LoginCount", 1);
		SettingFiles(file, DirFile(dirpath, uuid));
	}

	private static String getDate() {
		Calendar cal = new GregorianCalendar();
		return cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH);
	}

	public static void updatePlayerFile(String uuid) {
		FileConfiguration file = YamlConfiguration.loadConfiguration(DirFile(dirpath, uuid));
		file.set("LastLogin", getDate());
		file.set("LoginCount", file.getInt("LoginCount")+1);
		SettingFiles(file, DirFile(dirpath, uuid));
	}

	public static String lastDate(String uuid) {
		FileConfiguration file = YamlConfiguration.loadConfiguration(DirFile(dirpath, uuid));
		return file.getString("LastLogin");
	}

	public static boolean isToday(String uuid) {
		return lastDate(uuid).equalsIgnoreCase(getDate());
	}

	public static int getLoginCount(String uuid) {
		FileConfiguration file = YamlConfiguration.loadConfiguration(DirFile(dirpath, uuid));
		return file.getInt("LoginCount");
	}
}
