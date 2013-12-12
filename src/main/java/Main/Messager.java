package Main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Messager implements  Runnable {
	private Main plugin;
	private Player p;
	private List<String> messages;
	
	public Messager(Main plugin, Player p, List<String> m){
		this.plugin = plugin;
		this.p = p;
		this.messages = m;
	}


	private int Counter = 0;
	public void run() {
		if(p != null){
		try {
			String message = messages.get(Counter);
			message = message.replace("%Player%", p.getName());
			message = message.replace("%player%", p.getName());
			StringBuilder Online = new StringBuilder();
			Online.append("");
			Online.append(Bukkit.getOnlinePlayers().length);
			String on = Online.toString();
			message = message.replace("%Online%", on);
			message = message.replace("%online%", on);
			StringBuilder sb = new StringBuilder();
			sb.append("");
			sb.append(Bukkit.getMaxPlayers());
			String slots = sb.toString();
			message = message.replace("%Slots%", slots);
			message = message.replace("%slots%", slots);
		General.displayDragonTextBar(plugin, message, p, 10*20, 20);
		Counter ++;
		if(Counter >= this.messages.size()){
			Counter = 0;
			General.displayDragonTextBar(plugin, message, p, 10*20, 20);
		}
	} catch (ArrayIndexOutOfBoundsException exc) {
		
	}
	}else{
		return;
	}
  }
}

