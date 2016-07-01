package jp.kotmw.loginbonus;

import jp.kotmw.loginbonus.FileDatas.BonusItem;
import jp.kotmw.loginbonus.FileDatas.PlayerDatas;
import jp.kotmw.loginbonus.FileDatas.PluginFiles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;

public class Events implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		if(!PluginFiles.DirFile("Players", uuid).exists()) {
			System.out.println("初ログイン");
			PlayerDatas.createPlayerFile(uuid, p.getName());
			Bukkit.getScheduler().runTaskLater(Main.main, new Runnable() {
				@Override
				public void run() {
					Inventory inv = Bukkit.createInventory(null, 9, "LoginBonus");
					inv.addItem(BonusItem.getBonusItem(1));
					p.openInventory(inv);
				}
			}, 20L);
			return;
		}
		System.out.println("login");
		if(PlayerDatas.isToday(uuid))
			return;
		PlayerDatas.updatePlayerFile(uuid);
		int i = PlayerDatas.getLoginCount(uuid);
		Bukkit.getScheduler().runTaskLater(Main.main, new Runnable() {
			@Override
			public void run() {
				Inventory inv = Bukkit.createInventory(null, 9, "LoginBonus");
				inv.addItem(BonusItem.getBonusItem(i));
				p.openInventory(inv);
			}
		}, 20L);
	}

}
